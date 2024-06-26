package xyz.sl;

import org.junit.jupiter.api.Test;
import xyz.sl.processNotify.NotifyTo;
import xyz.sl.processNotify.WaitFor;
import xyz.sl.processNotify.impl.ThreadWaitAndNotify;

public class ThreadLevel {
    public static void main(String[] args) {
        Object a2bLock = new Object();
        Object b2aLock = new Object();


        Thread a = new Thread(() -> {
            NotifyTo a2b = new ThreadWaitAndNotify(a2bLock);
            WaitFor b2a = new ThreadWaitAndNotify(b2aLock);
            try {
                System.out.println("[a] wait b .. ");
                b2a.wait4();
                Thread.sleep(1000);

                System.out.println("[a] notify b");
                a2b.notify2();
                Thread.sleep(1000);

                System.out.println("[a] notify b again");
                a2b.notify2();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });



        Thread b = new Thread(() -> {
            NotifyTo b2a = new ThreadWaitAndNotify(b2aLock);
            WaitFor a2b = new ThreadWaitAndNotify(a2bLock);
            try {
                System.out.println("[b] notify a .. ");
                b2a.notify2();
//                Thread.sleep(1000);

                System.out.println("[b] wait a");
                a2b.wait4();
//                Thread.sleep(1000);

                System.out.println("[b] wait a again");
                a2b.wait4();
//                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        a.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        b.start();



    }
}
