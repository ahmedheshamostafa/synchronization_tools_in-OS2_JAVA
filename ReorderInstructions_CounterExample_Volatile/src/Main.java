public class Main {
    public static volatile boolean stopcount=false;
    public static void counter(){
        int x=0;
        System.out.println("inside counter");
        while(!stopcount){
            x++;
        }
        System.out.println("Count = "+x);
    }
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> { counter();});
        thread1.start();
        try {
            Thread.sleep(100);
            System.out.println("thread sleeping");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        stopcount=true;
    }
}