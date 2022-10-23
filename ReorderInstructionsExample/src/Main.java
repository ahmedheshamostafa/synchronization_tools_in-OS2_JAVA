class ReorderExample {
    private boolean flg = false;
    private int a = 2;


    public void method1() {
        a = 1;
        flg = true;
    }

    public void method2() {
   if(flg) {
       //2 might be printed out on some JVM/machines
       System.out.println("a = " + a);
   }

    }


}
public class Main {

    public static void main(String[] args) {

           ReorderExample reorderExample = new ReorderExample();
            Thread thread1 = new Thread(() -> { reorderExample.method1();

            });

            Thread thread2 = new Thread(() -> {  reorderExample.method2();});
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        thread1.start();
            thread2.start();


        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}