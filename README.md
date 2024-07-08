构建工具 Gradle 8.1.0  
jdk版本 17

## 线程级别通知等待示例 ： ThreadLevel类

## 进程级别通知等待示例 ： Alice.java、Bob.java、Crick.java
首先运行Bob.java和Crick.java（运行Crick.java和Bob.java也行），然后运行A.java。最后在各自的输出窗口中可以看到他们互相通知的过程。

## 不同设备之间的等待通知示例 ：
### remote包中的Alice.java、Bob.java、Crick.java
首先运行Bob.java和Crick.java（运行Crick.java和Bob.java也行），然后运行A.java。最后在各自的输出窗口中可以看到他们互相通知的过程。  
> Bug：只能通知一次，再次通知会出问题。
