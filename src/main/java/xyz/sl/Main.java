package xyz.sl;

import java.net.InetAddress;

public class Main {
    public static void main(String[] args) throws Exception {

        assert InetAddress.getByName("1.1.1.1").equals(InetAddress.getByName("1.1.1.81"));


        System.out.println("Hello world! 中文测试");
        throw new Exception("中文异常测试 chinese exception test");
    }
}