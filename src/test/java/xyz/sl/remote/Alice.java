package xyz.sl.remote;

import xyz.sl.processNotify.LockIDString;
import xyz.sl.processNotify.NotifyTo;
import xyz.sl.processNotify.WaitFor;
import xyz.sl.processNotify.impl.ProcessWaitAndNotify;
import xyz.sl.processNotify.remote.RemoteWaitAndNotify;
import xyz.sl.processNotify.remote.SocketLockID;

import java.io.IOException;
import java.net.InetAddress;

public class Alice {
    public static void main(String[] args) throws IOException, InterruptedException {
        Alice alice = new Alice();
        alice.runAlice();
    }
    // create pip
    NotifyTo Alice2Bob      = new RemoteWaitAndNotify(new SocketLockID(InetAddress.getLocalHost(), 9112, InetAddress.getLocalHost(), 9012));
    NotifyTo Alice2Crick    = new RemoteWaitAndNotify(new SocketLockID(InetAddress.getLocalHost(), 9113, InetAddress.getLocalHost(), 9013));
    WaitFor Bob2Alice       = new RemoteWaitAndNotify(new SocketLockID(InetAddress.getLocalHost(), 9121, InetAddress.getLocalHost(), 9021));
    WaitFor Crick2Alice     = new RemoteWaitAndNotify(new SocketLockID(InetAddress.getLocalHost(), 9131, InetAddress.getLocalHost(), 9031));

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
