package xyz.sl.remote;

import xyz.sl.processNotify.LockIDString;
import xyz.sl.processNotify.NotifyTo;
import xyz.sl.processNotify.WaitFor;
import xyz.sl.processNotify.impl.ProcessWaitAndNotify;
import xyz.sl.processNotify.remote.RemoteWaitAndNotify;
import xyz.sl.processNotify.remote.SocketLockID;

import java.io.IOException;
import java.net.InetAddress;

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
    NotifyTo Crick2Alice =  new RemoteWaitAndNotify(new SocketLockID(InetAddress.getLocalHost(), 9131, InetAddress.getLocalHost(), 9031));
    NotifyTo Crick2Bob =    new RemoteWaitAndNotify(new SocketLockID(InetAddress.getLocalHost(), 9132, InetAddress.getLocalHost(), 9032));
    WaitFor Bob2Crick =     new RemoteWaitAndNotify(new SocketLockID(InetAddress.getLocalHost(), 9123, InetAddress.getLocalHost(), 9023));
    WaitFor Alice2Crick =   new RemoteWaitAndNotify(new SocketLockID(InetAddress.getLocalHost(), 9113, InetAddress.getLocalHost(), 9013));

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
