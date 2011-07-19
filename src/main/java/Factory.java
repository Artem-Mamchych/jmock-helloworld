public class Factory {

    private static Factory INSTANCE;

    private synchronized static Factory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Factory();
        }
        return INSTANCE;
    }

    public static MemberImpl getMember() {
        return getInstance().member;
    }

    private MemberImpl member;

}
