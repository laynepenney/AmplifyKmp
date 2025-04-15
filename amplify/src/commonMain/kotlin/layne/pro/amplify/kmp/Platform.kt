package layne.pro.amplify.kmp

public interface Platform {
    public val name: String
}

public expect fun getPlatform(): Platform