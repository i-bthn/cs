package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;

public class Barrier extends Helper {
    public static final int BOTTOM = 3;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int TOP = 2;
    private boolean mAllowsGoneWidget = true;
    private int mBarrierType = 0;
    private ArrayList<ResolutionAnchor> mNodes = new ArrayList<>(4);

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public boolean allowedInBarrier() {
        return true;
    }

    public void setBarrierType(int i) {
        this.mBarrierType = i;
    }

    public void setAllowsGoneWidget(boolean z) {
        this.mAllowsGoneWidget = z;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void resetResolutionNodes() {
        super.resetResolutionNodes();
        this.mNodes.clear();
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void analyze(int i) {
        ResolutionAnchor resolutionAnchor;
        ResolutionAnchor resolutionAnchor2;
        if (this.mParent != null && ((ConstraintWidgetContainer) this.mParent).optimizeFor(2)) {
            switch (this.mBarrierType) {
                case 0:
                    resolutionAnchor = this.mLeft.getResolutionNode();
                    break;
                case 1:
                    resolutionAnchor = this.mRight.getResolutionNode();
                    break;
                case 2:
                    resolutionAnchor = this.mTop.getResolutionNode();
                    break;
                case 3:
                    resolutionAnchor = this.mBottom.getResolutionNode();
                    break;
                default:
                    return;
            }
            resolutionAnchor.setType(5);
            int i2 = this.mBarrierType;
            if (i2 == 0 || i2 == 1) {
                this.mTop.getResolutionNode().resolve(null, 0.0f);
                this.mBottom.getResolutionNode().resolve(null, 0.0f);
            } else {
                this.mLeft.getResolutionNode().resolve(null, 0.0f);
                this.mRight.getResolutionNode().resolve(null, 0.0f);
            }
            this.mNodes.clear();
            for (int i3 = 0; i3 < this.mWidgetsCount; i3++) {
                ConstraintWidget constraintWidget = this.mWidgets[i3];
                if (this.mAllowsGoneWidget || constraintWidget.allowedInBarrier()) {
                    switch (this.mBarrierType) {
                        case 0:
                            resolutionAnchor2 = constraintWidget.mLeft.getResolutionNode();
                            break;
                        case 1:
                            resolutionAnchor2 = constraintWidget.mRight.getResolutionNode();
                            break;
                        case 2:
                            resolutionAnchor2 = constraintWidget.mTop.getResolutionNode();
                            break;
                        case 3:
                            resolutionAnchor2 = constraintWidget.mBottom.getResolutionNode();
                            break;
                        default:
                            resolutionAnchor2 = null;
                            break;
                    }
                    if (resolutionAnchor2 != null) {
                        this.mNodes.add(resolutionAnchor2);
                        resolutionAnchor2.addDependent(resolutionAnchor);
                    }
                }
            }
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void resolve() {
        ResolutionAnchor resolutionAnchor;
        float f = Float.MAX_VALUE;
        switch (this.mBarrierType) {
            case 0:
                resolutionAnchor = this.mLeft.getResolutionNode();
                break;
            case 1:
                resolutionAnchor = this.mRight.getResolutionNode();
                f = 0.0f;
                break;
            case 2:
                resolutionAnchor = this.mTop.getResolutionNode();
                break;
            case 3:
                resolutionAnchor = this.mBottom.getResolutionNode();
                f = 0.0f;
                break;
            default:
                return;
        }
        int size = this.mNodes.size();
        ResolutionAnchor resolutionAnchor2 = null;
        for (int i = 0; i < size; i++) {
            ResolutionAnchor resolutionAnchor3 = this.mNodes.get(i);
            if (resolutionAnchor3.state == 1) {
                int i2 = this.mBarrierType;
                if (i2 == 0 || i2 == 2) {
                    if (resolutionAnchor3.resolvedOffset < f) {
                        f = resolutionAnchor3.resolvedOffset;
                        resolutionAnchor2 = resolutionAnchor3.resolvedTarget;
                    }
                } else if (resolutionAnchor3.resolvedOffset > f) {
                    f = resolutionAnchor3.resolvedOffset;
                    resolutionAnchor2 = resolutionAnchor3.resolvedTarget;
                }
            } else {
                return;
            }
        }
        if (LinearSystem.getMetrics() != null) {
            LinearSystem.getMetrics().barrierConnectionResolved++;
        }
        resolutionAnchor.resolvedTarget = resolutionAnchor2;
        resolutionAnchor.resolvedOffset = f;
        resolutionAnchor.didResolve();
        switch (this.mBarrierType) {
            case 0:
                this.mRight.getResolutionNode().resolve(resolutionAnchor2, f);
                return;
            case 1:
                this.mLeft.getResolutionNode().resolve(resolutionAnchor2, f);
                return;
            case 2:
                this.mBottom.getResolutionNode().resolve(resolutionAnchor2, f);
                return;
            case 3:
                this.mTop.getResolutionNode().resolve(resolutionAnchor2, f);
                return;
            default:
                return;
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void addToSolver(LinearSystem linearSystem) {
        boolean z;
        this.mListAnchors[0] = this.mLeft;
        this.mListAnchors[2] = this.mTop;
        this.mListAnchors[1] = this.mRight;
        this.mListAnchors[3] = this.mBottom;
        for (int i = 0; i < this.mListAnchors.length; i++) {
            this.mListAnchors[i].mSolverVariable = linearSystem.createObjectVariable(this.mListAnchors[i]);
        }
        int i2 = this.mBarrierType;
        if (i2 >= 0 && i2 < 4) {
            ConstraintAnchor constraintAnchor = this.mListAnchors[this.mBarrierType];
            int i3 = 0;
            while (true) {
                if (i3 >= this.mWidgetsCount) {
                    z = false;
                    break;
                }
                ConstraintWidget constraintWidget = this.mWidgets[i3];
                if (this.mAllowsGoneWidget || constraintWidget.allowedInBarrier()) {
                    int i4 = this.mBarrierType;
                    if ((i4 != 0 && i4 != 1) || constraintWidget.getHorizontalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        int i5 = this.mBarrierType;
                        if ((i5 == 2 || i5 == 3) && constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                            z = true;
                            break;
                        }
                    } else {
                        z = true;
                        break;
                    }
                }
                i3++;
            }
            int i6 = this.mBarrierType;
            if (i6 == 0 || i6 == 1) {
                if (getParent().getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    z = false;
                }
            } else if (getParent().getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                z = false;
            }
            for (int i7 = 0; i7 < this.mWidgetsCount; i7++) {
                ConstraintWidget constraintWidget2 = this.mWidgets[i7];
                if (this.mAllowsGoneWidget || constraintWidget2.allowedInBarrier()) {
                    SolverVariable createObjectVariable = linearSystem.createObjectVariable(constraintWidget2.mListAnchors[this.mBarrierType]);
                    ConstraintAnchor[] constraintAnchorArr = constraintWidget2.mListAnchors;
                    int i8 = this.mBarrierType;
                    constraintAnchorArr[i8].mSolverVariable = createObjectVariable;
                    if (i8 == 0 || i8 == 2) {
                        linearSystem.addLowerBarrier(constraintAnchor.mSolverVariable, createObjectVariable, z);
                    } else {
                        linearSystem.addGreaterBarrier(constraintAnchor.mSolverVariable, createObjectVariable, z);
                    }
                }
            }
            int i9 = this.mBarrierType;
            if (i9 == 0) {
                linearSystem.addEquality(this.mRight.mSolverVariable, this.mLeft.mSolverVariable, 0, 6);
                if (!z) {
                    linearSystem.addEquality(this.mLeft.mSolverVariable, this.mParent.mRight.mSolverVariable, 0, 5);
                }
            } else if (i9 == 1) {
                linearSystem.addEquality(this.mLeft.mSolverVariable, this.mRight.mSolverVariable, 0, 6);
                if (!z) {
                    linearSystem.addEquality(this.mLeft.mSolverVariable, this.mParent.mLeft.mSolverVariable, 0, 5);
                }
            } else if (i9 == 2) {
                linearSystem.addEquality(this.mBottom.mSolverVariable, this.mTop.mSolverVariable, 0, 6);
                if (!z) {
                    linearSystem.addEquality(this.mTop.mSolverVariable, this.mParent.mBottom.mSolverVariable, 0, 5);
                }
            } else if (i9 == 3) {
                linearSystem.addEquality(this.mTop.mSolverVariable, this.mBottom.mSolverVariable, 0, 6);
                if (!z) {
                    linearSystem.addEquality(this.mTop.mSolverVariable, this.mParent.mTop.mSolverVariable, 0, 5);
                }
            }
        }
    }
}
