package xyz.sl;

import org.junit.jupiter.api.Test;
import xyz.sl.processNotify.LockIDString;
import xyz.sl.processNotify.NotifyTo;
import xyz.sl.processNotify.WaitFor;
import xyz.sl.processNotify.impl.ProcessWaitAndNotify;

import java.io.IOException;

public class Bob {
    public static void main(String[] args) {
        Bob bob = null;
        try {
            bob = new Bob();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            bob.runBob();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // create pip
    NotifyTo Bob2Alice = new ProcessWaitAndNotify(new LockIDString("B2A"));
    NotifyTo Bob2Crick = new ProcessWaitAndNotify(new LockIDString("B2C"));
    WaitFor Alice2Bob = new ProcessWaitAndNotify(new LockIDString("A2B"));
    WaitFor Crick2Bob = new ProcessWaitAndNotify(new LockIDString("C2B"));

    public Bob() throws IOException {
    }


    void runBob() throws InterruptedException {
        System.out.println("[Bob] wait A ...");
        Alice2Bob.wait4();
        Thread.sleep(1000);

        System.out.println("[Bob] B over, wait A again ...");
        Alice2Bob.wait4();
        Thread.sleep(1000);

        System.out.println("[Bob] B over, notify C to start ...");
        Bob2Crick.notify2();
        Thread.sleep(1000);

        System.out.println("[Bob] wait C over ..");
        Crick2Bob.wait4();
        Thread.sleep(1000);

        System.out.println("[Bob] notify A , all over");
        Bob2Alice.notify2();


    }
}
