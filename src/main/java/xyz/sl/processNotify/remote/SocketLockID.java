package xyz.sl.processNotify.remote;

import xyz.sl.processNotify.LockID;

import java.net.InetAddress;

/**标识一个socket锁
 * 表识一个socket锁，通过源IP端口和目的IP端口
 */
public class SocketLockID implements LockID {
    private InetAddress waiterIp;
    private int waiterPort;
    private InetAddress notifierIp;
    private int notifierPort;

    public SocketLockID(InetAddress waiterIp, int waiterPort, InetAddress notifierIp, int notifierPort) {
        this.waiterIp = waiterIp;
        this.waiterPort = waiterPort;
        this.notifierIp = notifierIp;
        this.notifierPort = notifierPort;
    }

    public boolean equals(SocketLockID obj) {
        String thisLockID = this.getLockID().toString();
        String objLockID = obj.getLockID().toString();
        return thisLockID.equals(objLockID);
    }

    @Override
    public Object getLockID() {
        return "socketID@"+"{"+ waiterIp +":"+ waiterPort +"==>"+ notifierIp +":"+ notifierPort +"}";
    }

    public InetAddress getWaiterIp() {
        return waiterIp;
    }

    public int getWaiterPort() {
        return waiterPort;
    }

    public InetAddress getNotifierIp() {
        return notifierIp;
    }

    public int getNotifierPort() {
        return notifierPort;
    }

    @Override
    public String toString() {
        return "SocketLockID{" +
                "dstIp=" + waiterIp +
                ", dstPort=" + waiterPort +
                ", srcIp=" + notifierIp +
                ", srcPort=" + notifierPort +
                '}';
    }
}
