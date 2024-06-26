package xyz.sl.processNotify.impl;

import xyz.sl.processNotify.LockID;
import xyz.sl.processNotify.NotifyTo;
import xyz.sl.processNotify.WaitFor;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import sun.nio.ch.FileChannelImpl;

public class ProcessWaitAndNotify implements NotifyTo, WaitFor {

    LockID fileName;
    FileChannel fileChannel;
    FileLock fileLock;
    FileDescriptor fd;



    public ProcessWaitAndNotify(LockID lock) throws IOException {
        this.fileName = lock;
        if (!new File((String) fileName.getLockID()).exists()){
            if (!new File((String) fileName.getLockID()).createNewFile()){
                throw new IOException("error to create file " + fileName.getLockID());
            }
        }

        // 准备创建文件锁
        FileOutputStream fos = new FileOutputStream((String) fileName.getLockID());
        fd = fos.getFD();

        fileChannel = fos.getChannel();
        fileLock = fileChannel.lock();
    }

    /**发送通知的过程实际就是创建一个文件
     *
     * 文件创建的过程具有原子性
     *
     * if !file.exists
     *      file.create
     * return
     */
    @Override
    public void notify2() {
        try {
//            fileLock.release();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**文件不存在阻塞等待 文件存在则继续
     *
     * 文件创建的过程具有原子性
     *
     *  while !file.exists
     *      sleep ...
     *  delete file
     *  return
     */
    @Override
    public void wait4() {
        try {
//            fileLock = fileChannel.lock();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
