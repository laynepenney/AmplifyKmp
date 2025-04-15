package layne.pro.amplify.kmp

private class AndroidPlatform : Platform {
    public override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

public actual fun getPlatform(): Platform = AndroidPlatform()