
class Message2 {
    String message;
    boolean empty = true;


    public synchronized String read() {
        while (empty) {
            try {
               wait();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "Interrupted.");
            }
        }
        empty = true;
       notifyAll();
        return message;
    }

  public synchronized void write(String message) {
        while (!empty) {
            try {
               wait();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "Interrupted.");
            }
        }
        this.message = message;
        empty = false;
        notifyAll();
    }
}