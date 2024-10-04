import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyThread extends Thread {
    static int num = 0; // 所有窗口共用票数
    static final int TICKET = 100; // 共100张票
    // 创建一个静态的ReentrantLock实例
    static Lock lock = new ReentrantLock();

    MyThread(String name){
        super(name);
    }

    public void run(){
        // 每卖出一张票
        int count = 0;
        while (true) {
            boolean sold = sellTicket();
            if (!sold) break;
            count++;
            // 模拟出票时间
            try {
                Thread.sleep(10); // 增加休眠，便于观察效果
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println(getName() + " 卖出了 " + count + " 张票");
    }

    // 使用Lock来同步
    private static boolean sellTicket() {
        // 尝试获取锁
        lock.lock();
        try {
            if (num >= TICKET) {
                System.out.println("购买失败！票已经卖光了！");
                return false;
            }
            num += 1;
            System.out.println(Thread.currentThread().getName() + " 卖出第 " + num + " 张票");
            return true;
        } finally {
            // 确保锁一定会被释放
            lock.unlock();
        }
    }
}

public class LockDemo {
    /*
     * 需求：电影院正在上映一部电影，有三个售票窗口，共100张票，请模拟买票过程
     */
    public static void main(String[] args) {
        MyThread t1 = new MyThread("窗口1");
        MyThread t2 = new MyThread("窗口2");
        MyThread t3 = new MyThread("窗口3");
        t1.start();
        t2.start();
        t3.start();
    }
}
