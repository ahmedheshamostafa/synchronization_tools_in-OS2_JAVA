class task implements Runnable{
    private int count=0;

    @Override
    public void run() {
      //  synchronized (this) {
            for (int i = 0; i < 1000000; i++) {
                count++;
            }
            System.out.println(Thread.currentThread().getName()+"Counter =" + count);
        }
    //}
}

public class Main {
    public static void main(String[] args) {
        task mytask=new task();
        Thread t1=new Thread(mytask, "threadA ");
        Thread t2=new Thread(mytask, "threadB ");
        t1.start();
        t2.start();

    }
}