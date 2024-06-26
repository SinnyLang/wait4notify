package xyz.sl.processNotify.impl;

import xyz.sl.processNotify.NotifyTo;
import xyz.sl.processNotify.WaitFor;

public class ThreadWaitAndNotify implements WaitFor, NotifyTo {

    Object obj;

    public ThreadWaitAndNotify(Object obj) {
        this.obj = obj;
    }

    @Override
    public void notify2() {
        synchronized (obj){
            obj.notify();
        }
    }

    @Override
    public void wait4() throws InterruptedException {
        synchronized (obj){
            obj.wait(10000);
        }
    }
}
