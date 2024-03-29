package com.forcetower.playtime.anim;

import android.transition.ArcMotion;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.TransitionSet;

public class ChangeBoundsTransition extends TransitionSet {
    public ChangeBoundsTransition() {
        setOrdering(ORDERING_TOGETHER);
        ChangeBounds changeBounds = new ChangeBounds();
        changeBounds.setPathMotion(new ArcMotion());
        addTransition(changeBounds);
        addTransition(new ChangeTransform());
        addTransition(new ChangeImageTransform());
    }
}