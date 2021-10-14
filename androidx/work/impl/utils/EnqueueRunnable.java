package androidx.work.impl.utils;

import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.Logger;
import androidx.work.Operation;
import androidx.work.WorkInfo;
import androidx.work.WorkRequest;
import androidx.work.impl.OperationImpl;
import androidx.work.impl.Scheduler;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkContinuationImpl;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.background.systemalarm.RescheduleReceiver;
import androidx.work.impl.model.Dependency;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.WorkName;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkTag;
import androidx.work.impl.workers.ConstraintTrackingWorker;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class EnqueueRunnable implements Runnable {
    private static final String TAG = Logger.tagWithPrefix("EnqueueRunnable");
    private final OperationImpl mOperation = new OperationImpl();
    private final WorkContinuationImpl mWorkContinuation;

    public EnqueueRunnable(@NonNull WorkContinuationImpl workContinuationImpl) {
        this.mWorkContinuation = workContinuationImpl;
    }

    public void run() {
        try {
            if (!this.mWorkContinuation.hasCycles()) {
                if (addToDatabase()) {
                    PackageManagerHelper.setComponentEnabled(this.mWorkContinuation.getWorkManagerImpl().getApplicationContext(), RescheduleReceiver.class, true);
                    scheduleWorkInBackground();
                }
                this.mOperation.setState(Operation.SUCCESS);
                return;
            }
            throw new IllegalStateException(String.format("WorkContinuation has cycles (%s)", this.mWorkContinuation));
        } catch (Throwable th) {
            this.mOperation.setState(new Operation.State.FAILURE(th));
        }
    }

    @NonNull
    public Operation getOperation() {
        return this.mOperation;
    }

    @VisibleForTesting
    public boolean addToDatabase() {
        WorkDatabase workDatabase = this.mWorkContinuation.getWorkManagerImpl().getWorkDatabase();
        workDatabase.beginTransaction();
        try {
            boolean processContinuation = processContinuation(this.mWorkContinuation);
            workDatabase.setTransactionSuccessful();
            return processContinuation;
        } finally {
            workDatabase.endTransaction();
        }
    }

    @VisibleForTesting
    public void scheduleWorkInBackground() {
        WorkManagerImpl workManagerImpl = this.mWorkContinuation.getWorkManagerImpl();
        Schedulers.schedule(workManagerImpl.getConfiguration(), workManagerImpl.getWorkDatabase(), workManagerImpl.getSchedulers());
    }

    private static boolean processContinuation(@NonNull WorkContinuationImpl workContinuationImpl) {
        List<WorkContinuationImpl> parents = workContinuationImpl.getParents();
        boolean z = false;
        if (parents != null) {
            boolean z2 = false;
            for (WorkContinuationImpl workContinuationImpl2 : parents) {
                if (!workContinuationImpl2.isEnqueued()) {
                    z2 |= processContinuation(workContinuationImpl2);
                } else {
                    Logger.get().warning(TAG, String.format("Already enqueued work ids (%s).", TextUtils.join(", ", workContinuationImpl2.getIds())), new Throwable[0]);
                }
            }
            z = z2;
        }
        return enqueueContinuation(workContinuationImpl) | z;
    }

    private static boolean enqueueContinuation(@NonNull WorkContinuationImpl workContinuationImpl) {
        boolean enqueueWorkWithPrerequisites = enqueueWorkWithPrerequisites(workContinuationImpl.getWorkManagerImpl(), workContinuationImpl.getWork(), (String[]) WorkContinuationImpl.prerequisitesFor(workContinuationImpl).toArray(new String[0]), workContinuationImpl.getName(), workContinuationImpl.getExistingWorkPolicy());
        workContinuationImpl.markEnqueued();
        return enqueueWorkWithPrerequisites;
    }

    /* JADX WARNING: Removed duplicated region for block: B:90:0x0160  */
    private static boolean enqueueWorkWithPrerequisites(WorkManagerImpl workManagerImpl, @NonNull List<? extends WorkRequest> list, String[] strArr, String str, ExistingWorkPolicy existingWorkPolicy) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        long j;
        DependencyDao dependencyDao;
        boolean z5;
        WorkManagerImpl workManagerImpl2 = workManagerImpl;
        String[] strArr2 = strArr;
        long currentTimeMillis = System.currentTimeMillis();
        WorkDatabase workDatabase = workManagerImpl.getWorkDatabase();
        boolean z6 = strArr2 != null && strArr2.length > 0;
        if (z6) {
            z3 = true;
            z2 = false;
            z = false;
            for (String str2 : strArr2) {
                WorkSpec workSpec = workDatabase.workSpecDao().getWorkSpec(str2);
                if (workSpec == null) {
                    Logger.get().error(TAG, String.format("Prerequisite %s doesn't exist; not enqueuing", str2), new Throwable[0]);
                    return false;
                }
                WorkInfo.State state = workSpec.state;
                z3 &= state == WorkInfo.State.SUCCEEDED;
                if (state == WorkInfo.State.FAILED) {
                    z = true;
                } else if (state == WorkInfo.State.CANCELLED) {
                    z2 = true;
                }
            }
        } else {
            z3 = true;
            z2 = false;
            z = false;
        }
        boolean z7 = !TextUtils.isEmpty(str);
        if (z7 && !z6) {
            List<WorkSpec.IdAndState> workSpecIdAndStatesForName = workDatabase.workSpecDao().getWorkSpecIdAndStatesForName(str);
            if (!workSpecIdAndStatesForName.isEmpty()) {
                if (existingWorkPolicy == ExistingWorkPolicy.APPEND || existingWorkPolicy == ExistingWorkPolicy.APPEND_OR_REPLACE) {
                    DependencyDao dependencyDao2 = workDatabase.dependencyDao();
                    List arrayList = new ArrayList();
                    for (WorkSpec.IdAndState idAndState : workSpecIdAndStatesForName) {
                        if (!dependencyDao2.hasDependents(idAndState.id)) {
                            dependencyDao = dependencyDao2;
                            boolean z8 = (idAndState.state == WorkInfo.State.SUCCEEDED) & z3;
                            if (idAndState.state == WorkInfo.State.FAILED) {
                                z = true;
                            } else if (idAndState.state == WorkInfo.State.CANCELLED) {
                                z2 = true;
                            }
                            arrayList.add(idAndState.id);
                            z3 = z8;
                        } else {
                            dependencyDao = dependencyDao2;
                        }
                        dependencyDao2 = dependencyDao;
                    }
                    if (existingWorkPolicy == ExistingWorkPolicy.APPEND_OR_REPLACE && (z2 || z)) {
                        WorkSpecDao workSpecDao = workDatabase.workSpecDao();
                        for (WorkSpec.IdAndState idAndState2 : workSpecDao.getWorkSpecIdAndStatesForName(str)) {
                            workSpecDao.delete(idAndState2.id);
                        }
                        arrayList = Collections.emptyList();
                        z2 = false;
                        z = false;
                    }
                    strArr2 = (String[]) arrayList.toArray(strArr2);
                    z6 = strArr2.length > 0;
                    z4 = false;
                    for (WorkRequest workRequest : list) {
                        WorkSpec workSpec2 = workRequest.getWorkSpec();
                        if (!z6 || z3) {
                            if (!workSpec2.isPeriodic()) {
                                workSpec2.periodStartTime = currentTimeMillis;
                                j = currentTimeMillis;
                            } else {
                                j = currentTimeMillis;
                                workSpec2.periodStartTime = 0;
                            }
                        } else if (z) {
                            workSpec2.state = WorkInfo.State.FAILED;
                            j = currentTimeMillis;
                        } else if (z2) {
                            workSpec2.state = WorkInfo.State.CANCELLED;
                            j = currentTimeMillis;
                        } else {
                            workSpec2.state = WorkInfo.State.BLOCKED;
                            j = currentTimeMillis;
                        }
                        if (Build.VERSION.SDK_INT >= 23 && Build.VERSION.SDK_INT <= 25) {
                            tryDelegateConstrainedWorkSpec(workSpec2);
                        } else if (Build.VERSION.SDK_INT <= 22 && usesScheduler(workManagerImpl2, Schedulers.GCM_SCHEDULER)) {
                            tryDelegateConstrainedWorkSpec(workSpec2);
                        }
                        if (workSpec2.state == WorkInfo.State.ENQUEUED) {
                            z4 = true;
                        }
                        workDatabase.workSpecDao().insertWorkSpec(workSpec2);
                        if (z6) {
                            for (String str3 : strArr2) {
                                workDatabase.dependencyDao().insertDependency(new Dependency(workRequest.getStringId(), str3));
                            }
                        }
                        for (String str4 : workRequest.getTags()) {
                            workDatabase.workTagDao().insert(new WorkTag(str4, workRequest.getStringId()));
                        }
                        if (z7) {
                            workDatabase.workNameDao().insert(new WorkName(str, workRequest.getStringId()));
                        }
                        currentTimeMillis = j;
                        workManagerImpl2 = workManagerImpl;
                    }
                    return z4;
                }
                if (existingWorkPolicy == ExistingWorkPolicy.KEEP) {
                    for (WorkSpec.IdAndState idAndState3 : workSpecIdAndStatesForName) {
                        if (idAndState3.state == WorkInfo.State.ENQUEUED || idAndState3.state == WorkInfo.State.RUNNING) {
                            return false;
                        }
                    }
                    z5 = false;
                } else {
                    z5 = false;
                }
                CancelWorkRunnable.forName(str, workManagerImpl2, z5).run();
                WorkSpecDao workSpecDao2 = workDatabase.workSpecDao();
                for (WorkSpec.IdAndState idAndState4 : workSpecIdAndStatesForName) {
                    workSpecDao2.delete(idAndState4.id);
                }
                z4 = true;
                while (r7.hasNext()) {
                }
                return z4;
            }
        }
        z4 = false;
        while (r7.hasNext()) {
        }
        return z4;
    }

    private static void tryDelegateConstrainedWorkSpec(WorkSpec workSpec) {
        Constraints constraints = workSpec.constraints;
        String str = workSpec.workerClassName;
        if (str.equals(ConstraintTrackingWorker.class.getName())) {
            return;
        }
        if (constraints.requiresBatteryNotLow() || constraints.requiresStorageNotLow()) {
            Data.Builder builder = new Data.Builder();
            builder.putAll(workSpec.input).putString(ConstraintTrackingWorker.ARGUMENT_CLASS_NAME, str);
            workSpec.workerClassName = ConstraintTrackingWorker.class.getName();
            workSpec.input = builder.build();
        }
    }

    private static boolean usesScheduler(@NonNull WorkManagerImpl workManagerImpl, @NonNull String str) {
        try {
            Class<?> cls = Class.forName(str);
            for (Scheduler scheduler : workManagerImpl.getSchedulers()) {
                if (cls.isAssignableFrom(scheduler.getClass())) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}
