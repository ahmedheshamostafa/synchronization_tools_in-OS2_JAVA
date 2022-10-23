class Main {
    static    int val=0;
    static     boolean[] flag = {false, false};
    static  int turn=0;
        static Thread process(int i) {
        return new Thread(() -> {
            int j = 1 - i;
            for (int n=0; n<10; n++) {
                System.out.println("process "+i+" request to enter critical section");
                flag[i] = true; // 1
                turn=j;
                while (flag[j]&&turn==j) ;
              System.out.println("process "+i+" now in critical section");
                val++;   //critical section
                System.out.println("process "+i+" exit  critical section");

                flag[i] = false; // // UNLOCK
            }

        });
    }
    public static void main(String[] args) {
        try {
            Thread p0 = process(0);
            Thread p1 = process(1);
            p0.start();
            p1.start();
            p0.join();
            p1.join();
            System.out.println("val = "+val);
        }
        catch (InterruptedException e) {}
    }


}