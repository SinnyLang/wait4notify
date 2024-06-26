package xyz.sl.processNotify;

public class LockIDString implements LockID{
    String lockString;

    public LockIDString(String lockString) {
        this.lockString = lockString;
    }

    public String getLockString() {
        return lockString;
    }

    @Override
    public Object getLockID() {
        return getLockString();
    }
}
