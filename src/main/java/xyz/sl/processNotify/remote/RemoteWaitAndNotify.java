package xyz.sl.processNotify.remote;

import xyz.sl.processNotify.LockID;
import xyz.sl.processNotify.NotifyTo;
import xyz.sl.processNotify.WaitFor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**远程通知等待，通过socket实现
 */
public class RemoteWaitAndNotify implements WaitFor, NotifyTo {

    private SocketLockID socketLock;

    public RemoteWaitAndNotify(LockID socketLock) {
        this.socketLock = (SocketLockID) socketLock;
    }


    /**连接socket通道并通知
     *
     * socket.connect
     *
     */
    @Override
    public void notify2() {
        try {
            Socket socket = new Socket(
                    socketLock.getWaiterIp(),
                    socketLock.getWaiterPort(),
                    socketLock.getNotifierIp(),
                    socketLock.getNotifierPort());
            socket.close();
            // 等待socket关闭
//            while (!socket.isClosed()){
//                Thread.sleep(10);
//            }
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**建立socket通道并监听通知
     *
     * serverSocket.start
     * do
     *     socket.accept
     * until this.lockID == socket.socketLockID
     * socket.close
     *
     * @throws InterruptedException 等待过程中被打断
     */
    @Override
    public void wait4() throws InterruptedException {
        try {
            ServerSocket ss = new ServerSocket(socketLock.getWaiterPort());
            Socket socket;
            do {
                socket = ss.accept();

                // 如果连接的远程主机是期望的主机，则证明收到通知
                if (socket.getInetAddress().equals(this.socketLock.getNotifierIp()) &&
                        this.socketLock.getNotifierPort() == socket.getPort()) {
                    socket.close();
                    break;
//                }else {
//                    socket.close();
                }
            }while (true);
//            // 等待socket关闭
//            while (!socket.isClosed()){
//                Thread.sleep(10);
//            }
            ss.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
