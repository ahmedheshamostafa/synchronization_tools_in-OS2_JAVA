class Main {
    static    int val=0;
    static     boolean[] flag = {false, false};
    static  int turn=0;
    static Thread process(int i) {
        return new Thread(() -> {
            int j = 1 - i;
            for (int n=0; n<1000000; n++) {
                flag[i] = true; // 1
                turn=j;
                while (flag[j]&&turn==j) ;
                val++;   //critical section
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