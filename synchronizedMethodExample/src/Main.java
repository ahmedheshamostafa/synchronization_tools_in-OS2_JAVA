   class counter{
private  int count;
public counter(int count){
    this.count=count;
}
public   synchronized void increment(){

        count++;

}
public  synchronized void decrement(){

        this.count--;

}

public int getCount(){
    return count;
}
}

class task1 extends Thread{
    counter obj;
    String name;
    task1(counter obj,String name){
        this.obj=obj;
        this.name=name;
        this.setName(name);
    }
    public void run(){
        for(int i=0;i<1000000;i++){
             obj.increment();
        }
    }
}

class task2 extends Thread{
    counter obj;
    String name;
    task2(counter obj,String name){
        this.obj=obj;
        this.setName(name);
        this.name=name;
    }
    public void run(){
        for(int i=0;i<1000000;i++){
            obj.increment();
        }
    }
}

class Main {

    public static void main(String[] args)
            throws InterruptedException
    {
        // Instance of Counter Class
        counter obj1 = new counter(0);
        counter obj2 = new counter(0);


        // Defining Two different threads
        task1 first = new task1(obj1,"task1");
        task2 second = new task2(obj1, "task2");

        task1 third = new task1(obj2,"task1");
        task2 fourth = new task2(obj2, "task2");

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

        System.out.println(obj1.getCount());
        System.out.println(obj2.getCount());

    }
}