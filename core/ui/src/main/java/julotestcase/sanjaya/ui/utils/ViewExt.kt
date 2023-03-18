package julotestcase.sanjaya.ui.utils

import android.widget.ViewFlipper

fun ViewFlipper.initAnim() {
    setInAnimation(context, android.R.anim.fade_in)
    setOutAnimation(context, android.R.anim.fade_out)
}