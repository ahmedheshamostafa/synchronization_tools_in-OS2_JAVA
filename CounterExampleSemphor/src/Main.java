import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.Semaphore;


class Main extends Thread {
    Semaphore sem = new Semaphore(3);
    static int val=0;
    public void run()
    {
         try {
            sem.acquire();
            System.out.println(Thread.currentThread().getName() + " Enter in critical section" );
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " val=" + (++val));
            } }
        catch(InterruptedException e){
                throw new RuntimeException(e);
            }
        System.out.println(Thread.currentThread().getName() + " exit critical section" );
            sem.release();
        }


    public static void main(String[] args)
            throws InterruptedException
    {
        // Instance of Counter Class
        Main c = new Main();

        // Defining Two different threads
        Thread first = new Thread(c, "First");
        Thread second = new Thread(c, "Second");
        Thread third = new Thread(c, "Third");
        Thread fourth = new Thread(c, "Fourth");
        // Threads start executing
        first.start();
        second.start();
        third.start();
        fourth.start();
        // main thread will wait for child threads to complete execution
        first.join();
        second.join();
        third.join();
        fourth.join();


        System.out.println(val);
    }
}