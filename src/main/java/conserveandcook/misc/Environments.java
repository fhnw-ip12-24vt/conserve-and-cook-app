package conserveandcook.misc;

public enum Environments {
    PRODUCTION,
    TEST,
    LOCAL;

    public static Environments get() {
        String env = Config.get("environment");
        return Environments.valueOf(env.toUpperCase());
    }
}
