package xyz.sl.processNotify;

public interface WaitFor {
    /**等待函数
     * 等待一个通知
     * @throws InterruptedException 打断等待
     */
    void wait4() throws InterruptedException;
}
