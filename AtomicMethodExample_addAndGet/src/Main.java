import java.util.concurrent.atomic.AtomicInteger;
class Main extends Thread {

    // Atomic counter Variable
    AtomicInteger count= new AtomicInteger();
    public void run()
    {
        for (int i = 0; i < 1_000000; i++) {
           count.addAndGet(1);
        }
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
        System.out.println(c.count);
    }
}