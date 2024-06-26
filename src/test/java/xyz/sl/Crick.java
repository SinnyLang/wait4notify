package xyz.sl;

import org.junit.jupiter.api.Test;
import xyz.sl.processNotify.LockIDString;
import xyz.sl.processNotify.NotifyTo;
import xyz.sl.processNotify.WaitFor;
import xyz.sl.processNotify.impl.ProcessWaitAndNotify;

import java.io.IOException;

public class Crick {
    public static void main(String[] args) {
        Crick crick = null;
        try {
            crick = new Crick();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            crick.runCrick();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // create pip
    NotifyTo Crick2Alice = new ProcessWaitAndNotify(new LockIDString("C2A"));
    NotifyTo Crick2Bob = new ProcessWaitAndNotify(new LockIDString("C2B"));
    WaitFor Bob2Crick = new ProcessWaitAndNotify(new LockIDString("B2C"));
    WaitFor Alice2Crick = new ProcessWaitAndNotify(new LockIDString("A2C"));

    public Crick() throws IOException {
    }

    void runCrick() throws InterruptedException {
        System.out.println("[Crick] wait A ...");
        Alice2Crick.wait4();
        Thread.sleep(1000);

        System.out.println("[Crick] C over, wait A again ...");
        Alice2Crick.wait4();
        Thread.sleep(1000);

        System.out.println("[Crick] wait B for starting ..");
        Bob2Crick.wait4();
        Thread.sleep(1000);

        System.out.println("[Crick] C over, notify B ..");
        Crick2Bob.notify2();
        Thread.sleep(1000);

        System.out.println("[Crick] notify A , all over");
        Crick2Alice.notify2();
    }
}
