plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.android.library).apply(false)
    alias(libs.plugins.kotlin.multiplatform).apply(false)
    alias(libs.plugins.swiftklib).apply(false)
    id("pro-layne-amps-kmp-publish").apply(false)
}
