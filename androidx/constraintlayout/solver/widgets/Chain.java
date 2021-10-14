package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.ArrayRow;
import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;

/* access modifiers changed from: package-private */
public class Chain {
    private static final boolean DEBUG = false;

    Chain() {
    }

    static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, int i) {
        int i2;
        ChainHead[] chainHeadArr;
        int i3;
        if (i == 0) {
            int i4 = constraintWidgetContainer.mHorizontalChainsSize;
            chainHeadArr = constraintWidgetContainer.mHorizontalChainsArray;
            i2 = i4;
            i3 = 0;
        } else {
            i3 = 2;
            i2 = constraintWidgetContainer.mVerticalChainsSize;
            chainHeadArr = constraintWidgetContainer.mVerticalChainsArray;
        }
        for (int i5 = 0; i5 < i2; i5++) {
            ChainHead chainHead = chainHeadArr[i5];
            chainHead.define();
            if (!constraintWidgetContainer.optimizeFor(4)) {
                applyChainConstraints(constraintWidgetContainer, linearSystem, i, i3, chainHead);
            } else if (!Optimizer.applyChainOptimized(constraintWidgetContainer, linearSystem, i, i3, chainHead)) {
                applyChainConstraints(constraintWidgetContainer, linearSystem, i, i3, chainHead);
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v43, resolved type: androidx.constraintlayout.solver.widgets.ConstraintWidget */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x03a4  */
    /* JADX WARNING: Removed duplicated region for block: B:239:0x0462  */
    /* JADX WARNING: Removed duplicated region for block: B:251:0x04b8  */
    /* JADX WARNING: Removed duplicated region for block: B:252:0x04bd  */
    /* JADX WARNING: Removed duplicated region for block: B:255:0x04c3  */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x04c8  */
    /* JADX WARNING: Removed duplicated region for block: B:258:0x04cc  */
    /* JADX WARNING: Removed duplicated region for block: B:262:0x04dd  */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x04e0  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0167  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x01a2  */
    static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, int i, int i2, ChainHead chainHead) {
        boolean z;
        boolean z2;
        boolean z3;
        ConstraintWidget constraintWidget;
        boolean z4;
        ConstraintWidget constraintWidget2;
        SolverVariable solverVariable;
        SolverVariable solverVariable2;
        ConstraintWidget constraintWidget3;
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        ConstraintAnchor constraintAnchor3;
        ConstraintWidget constraintWidget4;
        ConstraintWidget constraintWidget5;
        SolverVariable solverVariable3;
        SolverVariable solverVariable4;
        ConstraintAnchor constraintAnchor4;
        SolverVariable solverVariable5;
        SolverVariable solverVariable6;
        ConstraintAnchor constraintAnchor5;
        float f;
        int size;
        int i3;
        ArrayList<ConstraintWidget> arrayList;
        int i4;
        float f2;
        float f3;
        boolean z5;
        int i5;
        ConstraintWidget constraintWidget6;
        boolean z6;
        int i6;
        ConstraintWidget constraintWidget7 = chainHead.mFirst;
        ConstraintWidget constraintWidget8 = chainHead.mLast;
        ConstraintWidget constraintWidget9 = chainHead.mFirstVisibleWidget;
        ConstraintWidget constraintWidget10 = chainHead.mLastVisibleWidget;
        ConstraintWidget constraintWidget11 = chainHead.mHead;
        float f4 = chainHead.mTotalWeight;
        ConstraintWidget constraintWidget12 = chainHead.mFirstMatchConstraintWidget;
        ConstraintWidget constraintWidget13 = chainHead.mLastMatchConstraintWidget;
        boolean z7 = constraintWidgetContainer.mListDimensionBehaviors[i] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (i == 0) {
            z2 = constraintWidget11.mHorizontalChainStyle == 0;
            constraintWidget = constraintWidget7;
            z = constraintWidget11.mHorizontalChainStyle == 1;
            z3 = constraintWidget11.mHorizontalChainStyle == 2;
            z4 = false;
        } else {
            z2 = constraintWidget11.mVerticalChainStyle == 0;
            constraintWidget = constraintWidget7;
            z = constraintWidget11.mVerticalChainStyle == 1;
            z3 = constraintWidget11.mVerticalChainStyle == 2;
            z4 = false;
        }
        while (true) {
            constraintWidget2 = null;
            if (z4) {
                break;
            }
            ConstraintAnchor constraintAnchor6 = constraintWidget.mListAnchors[i2];
            int i7 = (z7 || z3) ? 1 : 4;
            int margin = constraintAnchor6.getMargin();
            int margin2 = (constraintAnchor6.mTarget == null || constraintWidget == constraintWidget7) ? margin : margin + constraintAnchor6.mTarget.getMargin();
            if (z3 && constraintWidget != constraintWidget7 && constraintWidget != constraintWidget9) {
                f3 = f4;
                z5 = z4;
                i5 = 6;
            } else if (!z2 || !z7) {
                f3 = f4;
                i5 = i7;
                z5 = z4;
            } else {
                f3 = f4;
                z5 = z4;
                i5 = 4;
            }
            if (constraintAnchor6.mTarget != null) {
                if (constraintWidget == constraintWidget9) {
                    z6 = z2;
                    constraintWidget6 = constraintWidget11;
                    linearSystem.addGreaterThan(constraintAnchor6.mSolverVariable, constraintAnchor6.mTarget.mSolverVariable, margin2, 5);
                } else {
                    constraintWidget6 = constraintWidget11;
                    z6 = z2;
                    linearSystem.addGreaterThan(constraintAnchor6.mSolverVariable, constraintAnchor6.mTarget.mSolverVariable, margin2, 6);
                }
                linearSystem.addEquality(constraintAnchor6.mSolverVariable, constraintAnchor6.mTarget.mSolverVariable, margin2, i5);
            } else {
                constraintWidget6 = constraintWidget11;
                z6 = z2;
            }
            if (z7) {
                if (constraintWidget.getVisibility() == 8 || constraintWidget.mListDimensionBehaviors[i] != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i6 = 0;
                } else {
                    i6 = 0;
                    linearSystem.addGreaterThan(constraintWidget.mListAnchors[i2 + 1].mSolverVariable, constraintWidget.mListAnchors[i2].mSolverVariable, 0, 5);
                }
                linearSystem.addGreaterThan(constraintWidget.mListAnchors[i2].mSolverVariable, constraintWidgetContainer.mListAnchors[i2].mSolverVariable, i6, 6);
            }
            ConstraintAnchor constraintAnchor7 = constraintWidget.mListAnchors[i2 + 1].mTarget;
            if (constraintAnchor7 != null) {
                ConstraintWidget constraintWidget14 = constraintAnchor7.mOwner;
                if (constraintWidget14.mListAnchors[i2].mTarget != null && constraintWidget14.mListAnchors[i2].mTarget.mOwner == constraintWidget) {
                    constraintWidget2 = constraintWidget14;
                }
            }
            if (constraintWidget2 != null) {
                constraintWidget = constraintWidget2;
                z4 = z5;
            } else {
                z4 = true;
            }
            f4 = f3;
            z2 = z6;
            constraintWidget11 = constraintWidget6;
        }
        if (constraintWidget10 != null) {
            int i8 = i2 + 1;
            if (constraintWidget8.mListAnchors[i8].mTarget != null) {
                ConstraintAnchor constraintAnchor8 = constraintWidget10.mListAnchors[i8];
                linearSystem.addLowerThan(constraintAnchor8.mSolverVariable, constraintWidget8.mListAnchors[i8].mTarget.mSolverVariable, -constraintAnchor8.getMargin(), 5);
                if (z7) {
                    int i9 = i2 + 1;
                    linearSystem.addGreaterThan(constraintWidgetContainer.mListAnchors[i9].mSolverVariable, constraintWidget8.mListAnchors[i9].mSolverVariable, constraintWidget8.mListAnchors[i9].getMargin(), 6);
                }
                ArrayList<ConstraintWidget> arrayList2 = chainHead.mWeightedMatchConstraintsWidgets;
                if (arrayList2 != null && (size = arrayList2.size()) > 1) {
                    float f5 = (chainHead.mHasUndefinedWeights || chainHead.mHasComplexMatchWeights) ? f4 : (float) chainHead.mWidgetsMatchCount;
                    float f6 = 0.0f;
                    ConstraintWidget constraintWidget15 = null;
                    i3 = 0;
                    float f7 = 0.0f;
                    while (i3 < size) {
                        ConstraintWidget constraintWidget16 = arrayList2.get(i3);
                        float f8 = constraintWidget16.mWeight[i];
                        if (f8 >= f6) {
                            f2 = 0.0f;
                        } else if (chainHead.mHasComplexMatchWeights) {
                            linearSystem.addEquality(constraintWidget16.mListAnchors[i2 + 1].mSolverVariable, constraintWidget16.mListAnchors[i2].mSolverVariable, 0, 4);
                            arrayList = arrayList2;
                            i4 = size;
                            i3++;
                            size = i4;
                            arrayList2 = arrayList;
                            f6 = 0.0f;
                        } else {
                            f8 = 1.0f;
                            f2 = 0.0f;
                        }
                        if (f8 == f2) {
                            linearSystem.addEquality(constraintWidget16.mListAnchors[i2 + 1].mSolverVariable, constraintWidget16.mListAnchors[i2].mSolverVariable, 0, 6);
                            arrayList = arrayList2;
                            i4 = size;
                            i3++;
                            size = i4;
                            arrayList2 = arrayList;
                            f6 = 0.0f;
                        } else {
                            if (constraintWidget15 != null) {
                                SolverVariable solverVariable7 = constraintWidget15.mListAnchors[i2].mSolverVariable;
                                int i10 = i2 + 1;
                                SolverVariable solverVariable8 = constraintWidget15.mListAnchors[i10].mSolverVariable;
                                SolverVariable solverVariable9 = constraintWidget16.mListAnchors[i2].mSolverVariable;
                                arrayList = arrayList2;
                                SolverVariable solverVariable10 = constraintWidget16.mListAnchors[i10].mSolverVariable;
                                i4 = size;
                                ArrayRow createRow = linearSystem.createRow();
                                createRow.createRowEqualMatchDimensions(f7, f5, f8, solverVariable7, solverVariable8, solverVariable9, solverVariable10);
                                linearSystem.addConstraint(createRow);
                            } else {
                                arrayList = arrayList2;
                                i4 = size;
                            }
                            f7 = f8;
                            constraintWidget15 = constraintWidget16;
                            i3++;
                            size = i4;
                            arrayList2 = arrayList;
                            f6 = 0.0f;
                        }
                    }
                }
                if (constraintWidget9 == null && (constraintWidget9 == constraintWidget10 || z3)) {
                    ConstraintAnchor constraintAnchor9 = constraintWidget7.mListAnchors[i2];
                    int i11 = i2 + 1;
                    ConstraintAnchor constraintAnchor10 = constraintWidget8.mListAnchors[i11];
                    SolverVariable solverVariable11 = constraintWidget7.mListAnchors[i2].mTarget != null ? constraintWidget7.mListAnchors[i2].mTarget.mSolverVariable : null;
                    SolverVariable solverVariable12 = constraintWidget8.mListAnchors[i11].mTarget != null ? constraintWidget8.mListAnchors[i11].mTarget.mSolverVariable : null;
                    if (constraintWidget9 == constraintWidget10) {
                        constraintAnchor9 = constraintWidget9.mListAnchors[i2];
                        constraintAnchor10 = constraintWidget9.mListAnchors[i11];
                    }
                    if (!(solverVariable11 == null || solverVariable12 == null)) {
                        if (i == 0) {
                            f = constraintWidget11.mHorizontalBiasPercent;
                        } else {
                            f = constraintWidget11.mVerticalBiasPercent;
                        }
                        linearSystem.addCentering(constraintAnchor9.mSolverVariable, solverVariable11, constraintAnchor9.getMargin(), f, solverVariable12, constraintAnchor10.mSolverVariable, constraintAnchor10.getMargin(), 5);
                    }
                } else if (!z2 && constraintWidget9 != null) {
                    boolean z8 = chainHead.mWidgetsMatchCount > 0 && chainHead.mWidgetsCount == chainHead.mWidgetsMatchCount;
                    ConstraintWidget constraintWidget17 = constraintWidget9;
                    ConstraintWidget constraintWidget18 = constraintWidget17;
                    while (constraintWidget18 != null) {
                        ConstraintWidget constraintWidget19 = constraintWidget18.mListNextVisibleWidget[i];
                        if (constraintWidget19 != null || constraintWidget18 == constraintWidget10) {
                            ConstraintAnchor constraintAnchor11 = constraintWidget18.mListAnchors[i2];
                            SolverVariable solverVariable13 = constraintAnchor11.mSolverVariable;
                            SolverVariable solverVariable14 = constraintAnchor11.mTarget != null ? constraintAnchor11.mTarget.mSolverVariable : null;
                            if (constraintWidget17 != constraintWidget18) {
                                solverVariable14 = constraintWidget17.mListAnchors[i2 + 1].mSolverVariable;
                            } else if (constraintWidget18 == constraintWidget9 && constraintWidget17 == constraintWidget18) {
                                solverVariable14 = constraintWidget7.mListAnchors[i2].mTarget != null ? constraintWidget7.mListAnchors[i2].mTarget.mSolverVariable : null;
                            }
                            int margin3 = constraintAnchor11.getMargin();
                            int i12 = i2 + 1;
                            int margin4 = constraintWidget18.mListAnchors[i12].getMargin();
                            if (constraintWidget19 != null) {
                                constraintAnchor5 = constraintWidget19.mListAnchors[i2];
                                solverVariable6 = constraintAnchor5.mSolverVariable;
                                solverVariable5 = constraintWidget18.mListAnchors[i12].mSolverVariable;
                            } else {
                                constraintAnchor5 = constraintWidget8.mListAnchors[i12].mTarget;
                                solverVariable6 = constraintAnchor5 != null ? constraintAnchor5.mSolverVariable : null;
                                solverVariable5 = constraintWidget18.mListAnchors[i12].mSolverVariable;
                            }
                            if (constraintAnchor5 != null) {
                                margin4 += constraintAnchor5.getMargin();
                            }
                            if (constraintWidget17 != null) {
                                margin3 += constraintWidget17.mListAnchors[i12].getMargin();
                            }
                            if (solverVariable13 != null && solverVariable14 != null && solverVariable6 != null && solverVariable5 != null) {
                                linearSystem.addCentering(solverVariable13, solverVariable14, constraintWidget18 == constraintWidget9 ? constraintWidget9.mListAnchors[i2].getMargin() : margin3, 0.5f, solverVariable6, solverVariable5, constraintWidget18 == constraintWidget10 ? constraintWidget10.mListAnchors[i12].getMargin() : margin4, z8 ? 6 : 4);
                            }
                        }
                        constraintWidget17 = constraintWidget18;
                        constraintWidget18 = constraintWidget19;
                    }
                } else if (z && constraintWidget9 != null) {
                    boolean z9 = chainHead.mWidgetsMatchCount <= 0 && chainHead.mWidgetsCount == chainHead.mWidgetsMatchCount;
                    ConstraintWidget constraintWidget20 = constraintWidget9;
                    constraintWidget3 = constraintWidget20;
                    while (constraintWidget3 != null) {
                        ConstraintWidget constraintWidget21 = constraintWidget3.mListNextVisibleWidget[i];
                        if (constraintWidget3 == constraintWidget9 || constraintWidget3 == constraintWidget10 || constraintWidget21 == null) {
                            constraintWidget4 = constraintWidget3;
                            constraintWidget5 = constraintWidget21;
                        } else {
                            constraintWidget5 = constraintWidget21 == constraintWidget10 ? null : constraintWidget21;
                            ConstraintAnchor constraintAnchor12 = constraintWidget3.mListAnchors[i2];
                            SolverVariable solverVariable15 = constraintAnchor12.mSolverVariable;
                            if (constraintAnchor12.mTarget != null) {
                                SolverVariable solverVariable16 = constraintAnchor12.mTarget.mSolverVariable;
                            }
                            int i13 = i2 + 1;
                            SolverVariable solverVariable17 = constraintWidget20.mListAnchors[i13].mSolverVariable;
                            int margin5 = constraintAnchor12.getMargin();
                            int margin6 = constraintWidget3.mListAnchors[i13].getMargin();
                            if (constraintWidget5 != null) {
                                constraintAnchor4 = constraintWidget5.mListAnchors[i2];
                                solverVariable4 = constraintAnchor4.mSolverVariable;
                                solverVariable3 = constraintAnchor4.mTarget != null ? constraintAnchor4.mTarget.mSolverVariable : null;
                            } else {
                                constraintAnchor4 = constraintWidget3.mListAnchors[i13].mTarget;
                                solverVariable4 = constraintAnchor4 != null ? constraintAnchor4.mSolverVariable : null;
                                solverVariable3 = constraintWidget3.mListAnchors[i13].mSolverVariable;
                            }
                            int margin7 = constraintAnchor4 != null ? margin6 + constraintAnchor4.getMargin() : margin6;
                            int margin8 = constraintWidget20 != null ? margin5 + constraintWidget20.mListAnchors[i13].getMargin() : margin5;
                            int i14 = z9 ? 6 : 4;
                            if (solverVariable15 == null || solverVariable17 == null || solverVariable4 == null || solverVariable3 == null) {
                                constraintWidget4 = constraintWidget3;
                            } else {
                                constraintWidget4 = constraintWidget3;
                                linearSystem.addCentering(solverVariable15, solverVariable17, margin8, 0.5f, solverVariable4, solverVariable3, margin7, i14);
                            }
                        }
                        constraintWidget20 = constraintWidget4;
                        constraintWidget3 = constraintWidget5;
                    }
                    ConstraintAnchor constraintAnchor13 = constraintWidget9.mListAnchors[i2];
                    constraintAnchor = constraintWidget7.mListAnchors[i2].mTarget;
                    int i15 = i2 + 1;
                    constraintAnchor2 = constraintWidget10.mListAnchors[i15];
                    constraintAnchor3 = constraintWidget8.mListAnchors[i15].mTarget;
                    if (constraintAnchor != null) {
                        if (constraintWidget9 != constraintWidget10) {
                            linearSystem.addEquality(constraintAnchor13.mSolverVariable, constraintAnchor.mSolverVariable, constraintAnchor13.getMargin(), 5);
                        } else if (constraintAnchor3 != null) {
                            linearSystem.addCentering(constraintAnchor13.mSolverVariable, constraintAnchor.mSolverVariable, constraintAnchor13.getMargin(), 0.5f, constraintAnchor2.mSolverVariable, constraintAnchor3.mSolverVariable, constraintAnchor2.getMargin(), 5);
                        }
                    }
                    if (!(constraintAnchor3 == null || constraintWidget9 == constraintWidget10)) {
                        linearSystem.addEquality(constraintAnchor2.mSolverVariable, constraintAnchor3.mSolverVariable, -constraintAnchor2.getMargin(), 5);
                    }
                }
                if ((!z2 || z) && constraintWidget9 != null) {
                    ConstraintAnchor constraintAnchor14 = constraintWidget9.mListAnchors[i2];
                    int i16 = i2 + 1;
                    ConstraintAnchor constraintAnchor15 = constraintWidget10.mListAnchors[i16];
                    solverVariable = constraintAnchor14.mTarget == null ? constraintAnchor14.mTarget.mSolverVariable : null;
                    SolverVariable solverVariable18 = constraintAnchor15.mTarget == null ? constraintAnchor15.mTarget.mSolverVariable : null;
                    if (constraintWidget8 == constraintWidget10) {
                        ConstraintAnchor constraintAnchor16 = constraintWidget8.mListAnchors[i16];
                        if (constraintAnchor16.mTarget != null) {
                            constraintWidget2 = constraintAnchor16.mTarget.mSolverVariable;
                        }
                        solverVariable2 = constraintWidget2;
                    } else {
                        solverVariable2 = solverVariable18;
                    }
                    if (constraintWidget9 == constraintWidget10) {
                        constraintAnchor14 = constraintWidget9.mListAnchors[i2];
                        constraintAnchor15 = constraintWidget9.mListAnchors[i16];
                    }
                    if (solverVariable != null && solverVariable2 != null) {
                        int margin9 = constraintAnchor14.getMargin();
                        if (constraintWidget10 != null) {
                            constraintWidget8 = constraintWidget10;
                        }
                        linearSystem.addCentering(constraintAnchor14.mSolverVariable, solverVariable, margin9, 0.5f, solverVariable2, constraintAnchor15.mSolverVariable, constraintWidget8.mListAnchors[i16].getMargin(), 5);
                        return;
                    }
                }
                return;
            }
        }
        if (z7) {
        }
        ArrayList<ConstraintWidget> arrayList22 = chainHead.mWeightedMatchConstraintsWidgets;
        if (chainHead.mHasUndefinedWeights) {
        }
        float f62 = 0.0f;
        ConstraintWidget constraintWidget152 = null;
        i3 = 0;
        float f72 = 0.0f;
        while (i3 < size) {
        }
        if (constraintWidget9 == null) {
        }
        if (!z2) {
        }
        if (chainHead.mWidgetsMatchCount <= 0) {
        }
        ConstraintWidget constraintWidget202 = constraintWidget9;
        constraintWidget3 = constraintWidget202;
        while (constraintWidget3 != null) {
        }
        ConstraintAnchor constraintAnchor132 = constraintWidget9.mListAnchors[i2];
        constraintAnchor = constraintWidget7.mListAnchors[i2].mTarget;
        int i152 = i2 + 1;
        constraintAnchor2 = constraintWidget10.mListAnchors[i152];
        constraintAnchor3 = constraintWidget8.mListAnchors[i152].mTarget;
        if (constraintAnchor != null) {
        }
        linearSystem.addEquality(constraintAnchor2.mSolverVariable, constraintAnchor3.mSolverVariable, -constraintAnchor2.getMargin(), 5);
        if (!z2) {
        }
        ConstraintAnchor constraintAnchor142 = constraintWidget9.mListAnchors[i2];
        int i162 = i2 + 1;
        ConstraintAnchor constraintAnchor152 = constraintWidget10.mListAnchors[i162];
        if (constraintAnchor142.mTarget == null) {
        }
        if (constraintAnchor152.mTarget == null) {
        }
        if (constraintWidget8 == constraintWidget10) {
        }
        if (constraintWidget9 == constraintWidget10) {
        }
        if (solverVariable != null) {
        }
    }
}
