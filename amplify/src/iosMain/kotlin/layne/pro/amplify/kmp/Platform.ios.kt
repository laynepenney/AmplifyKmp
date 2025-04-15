package layne.pro.amplify.kmp

import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIDevice
import pro.layne.amplify.kmp.ios.AmplifyIOS

private class IOSPlatform: Platform {
    public override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

public actual fun getPlatform(): Platform = IOSPlatform()

@OptIn(ExperimentalForeignApi::class)
private fun test() {
    val a: AmplifyIOS = AmplifyIOS()
}
