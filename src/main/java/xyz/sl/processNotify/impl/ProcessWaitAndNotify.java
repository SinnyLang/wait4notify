package xyz.sl.processNotify.impl;

import xyz.sl.processNotify.LockID;
import xyz.sl.processNotify.NotifyTo;
import xyz.sl.processNotify.WaitFor;

import java.io.*;

public class ProcessWaitAndNotify implements NotifyTo, WaitFor {

    LockID fileName;

    public ProcessWaitAndNotify(LockID lock) {
        this.fileName = lock;
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
            if (!new File((String) fileName.getLockID()).exists()){
                if (!new File((String) fileName.getLockID()).createNewFile()){
                    throw new IOException("error to create file " + fileName.getLockID());
                }
            }

            /* wait4 中 判断file.exists和lock.delete之间有空隙
               这个空隙中有可能执行两次notify2
               因此通知的过程需要有意打断，让wait4执行完
             */
            Thread.sleep(10);

//            fileLock.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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
    public void wait4() throws InterruptedException{

        File lock = new File((String) fileName.getLockID());
        while (!lock.exists()){
            Thread.sleep(10);
        }
        lock.delete();
//        fileLock = fileChannel.lock();
    }


}
