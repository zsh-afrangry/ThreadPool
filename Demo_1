class MyRunnable implements Runnable{
    private int count;
    private int ticket = 100;
    
    public void run(){
        int num = 0;
        while (true) {
            if(func()) break;
            num += 1;
        }
        System.out.println(Thread.currentThread().getName() + " 卖出了 " + num + " 张票");
    }

    private synchronized boolean func(){
        if(count >= ticket){
            System.out.println(Thread.currentThread().getName() + " 购买失败！票已经卖光了！");
            return true;
        }
        count += 1;
        try{
            Thread.sleep(10);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "卖出第" + count + " 张票");
        return false;
    }
}

public class Demo_1 {
    //这里是通过非静态的方法实现同步方法的书写
    public static void main(String[] args) {
        MyRunnable mr = new MyRunnable();
        Thread t1 = new Thread(mr, "窗口1");
        Thread t2 = new Thread(mr,"窗口2");
        Thread t3 = new Thread(mr,"窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
