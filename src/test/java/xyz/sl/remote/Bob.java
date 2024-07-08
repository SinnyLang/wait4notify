package xyz.sl.remote;

import xyz.sl.processNotify.LockIDString;
import xyz.sl.processNotify.NotifyTo;
import xyz.sl.processNotify.WaitFor;
import xyz.sl.processNotify.impl.ProcessWaitAndNotify;
import xyz.sl.processNotify.remote.RemoteWaitAndNotify;
import xyz.sl.processNotify.remote.SocketLockID;

import java.io.IOException;
import java.net.InetAddress;

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
    NotifyTo Bob2Alice =    new RemoteWaitAndNotify(new SocketLockID(InetAddress.getLocalHost(), 9121, InetAddress.getLocalHost(), 9021));
    NotifyTo Bob2Crick =    new RemoteWaitAndNotify(new SocketLockID(InetAddress.getLocalHost(), 9123, InetAddress.getLocalHost(), 9023));
    WaitFor Alice2Bob =     new RemoteWaitAndNotify(new SocketLockID(InetAddress.getLocalHost(), 9112, InetAddress.getLocalHost(), 9012));
    WaitFor Crick2Bob =     new RemoteWaitAndNotify(new SocketLockID(InetAddress.getLocalHost(), 9132, InetAddress.getLocalHost(), 9032));

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
