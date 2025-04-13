package layne.pro.amplify.kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform