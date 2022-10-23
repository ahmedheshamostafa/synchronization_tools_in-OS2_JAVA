class Main {
    static int val=0;
    static  int turn = 0;   //volatile
    static Thread process(int i) {
        return new Thread(() -> {

            int j = 1 - i;
            for (int n=0; n<4; n++) {
                System.out.println("process "+i+" request to enter critical section");
                while (turn != i) ;
                System.out.println("process "+i+" now in critical section");
                val++;   //critical section
                System.out.println("process "+i+" exit  critical section");
                turn=j;

            }
        });
    }
    public static void main(String[] args) {
        try {
            Thread p1 = process(1);
            Thread p0 = process(0);
            p0.start();
            p1.start();
            p0.join();
            p1.join();
            System.out.println("val = "+val);
        }
        catch (InterruptedException e) {}
    }


}