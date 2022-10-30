import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;


class Main extends Thread {
    volatile AtomicBoolean lock=new AtomicBoolean(false);
    volatile AtomicBoolean key=new AtomicBoolean(false);
    static int val=0;
    volatile HashMap<Long,Integer> freq =new HashMap<Long,Integer>();
    volatile HashMap<Long,Boolean> waiting=new HashMap<Long,Boolean>();

    public void run()
    {
        for (int i = 0; i < 5; i++) {
           long  indx  =Thread.currentThread().getId();
            long tmp=indx;
           waiting.put(indx,true);
           key.set(true);
           while((Boolean)waiting.get(indx)&&key.get()){
                    key.set(lock.compareAndExchange(false, true));
           }
            waiting.put(indx,false);
            if(freq.containsKey(indx))
                freq.put(indx, freq.get(indx)+1);
            else {
                freq.put(indx, 1);
            }
            val++; //critical section
            for(Map.Entry e:waiting.entrySet()) {
                tmp = ((Long) e.getKey());
                if ((Boolean) e.getValue()&&tmp!=indx) {
                    e.setValue(false);
                    break;
                }
            }

                lock.set(false);
        }
    }
    public static void main(String[] args)
            throws InterruptedException
    {
        Main c = new Main();
        Thread first = new Thread(c, "First");
        Thread second = new Thread(c, "Second");
        Thread third = new Thread(c, "Third");
        Thread fourth = new Thread(c, "Fourth");
        first.start();
        second.start();
        third.start();
        fourth.start();
        first.join();
        second.join();
        third.join();
        fourth.join();
        for(Map.Entry e:c.freq.entrySet())
            System.out.println("Thread id: "+ e.getKey()+" enter critical section "+e.getValue()+" times");
        System.out.println(val);
    }
}