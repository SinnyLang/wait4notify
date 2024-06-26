package xyz.sl;

import org.junit.jupiter.api.Test;
import xyz.sl.processNotify.LockIDString;
import xyz.sl.processNotify.NotifyTo;
import xyz.sl.processNotify.WaitFor;
import xyz.sl.processNotify.impl.ProcessWaitAndNotify;

import java.io.IOException;

public class Alice {
    public static void main(String[] args) throws IOException, InterruptedException {
        Alice alice = new Alice();
        alice.runAlice();
    }
    // create pip
    NotifyTo Alice2Bob      = new ProcessWaitAndNotify(new LockIDString("A2B"));
    NotifyTo Alice2Crick    = new ProcessWaitAndNotify(new LockIDString("A2C"));
    WaitFor Bob2Alice       = new ProcessWaitAndNotify(new LockIDString("B2A"));
    WaitFor Crick2Alice     = new ProcessWaitAndNotify(new LockIDString("C2A"));

    public Alice() throws IOException {
    }


    void runAlice() throws InterruptedException {
        System.out.println("[Alice] A over, notify B C ...");
        Alice2Bob.notify2();
        Alice2Crick.notify2();
        Thread.sleep(1000);

        System.out.println("[Alice] A over, notify B C again ...");
        Alice2Bob.notify2();
        Alice2Crick.notify2();
        Thread.sleep(1000);

        System.out.println("[Alice] wait B C over ...");
        Bob2Alice.wait4();
        Crick2Alice.wait4();
        Thread.sleep(1000);

        System.out.println("[Alice] ABC Over");
    }
}
