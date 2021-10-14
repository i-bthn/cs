package androidx.work.impl.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class LiveDataUtils {
    public static <In, Out> LiveData<Out> dedupedMappedLiveDataFor(@NonNull LiveData<In> liveData, @NonNull final Function<In, Out> function, @NonNull final TaskExecutor taskExecutor) {
        final Object obj = new Object();
        final MediatorLiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(liveData, new Observer<In>() {
            /* class androidx.work.impl.utils.LiveDataUtils.AnonymousClass1 */
            Out mCurrentOutput = null;

            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable final In in) {
                TaskExecutor.this.executeOnBackgroundThread(new Runnable() {
                    /* class androidx.work.impl.utils.LiveDataUtils.AnonymousClass1.AnonymousClass1 */

                    public void run() {
                        synchronized (obj) {
                            Out out = (Out) function.apply(in);
                            if (AnonymousClass1.this.mCurrentOutput == null && out != null) {
                                AnonymousClass1.this.mCurrentOutput = out;
                                mediatorLiveData.postValue(out);
                            } else if (AnonymousClass1.this.mCurrentOutput != null && !AnonymousClass1.this.mCurrentOutput.equals(out)) {
                                AnonymousClass1.this.mCurrentOutput = out;
                                mediatorLiveData.postValue(out);
                            }
                        }
                    }
                });
            }
        });
        return mediatorLiveData;
    }

    private LiveDataUtils() {
    }
}
