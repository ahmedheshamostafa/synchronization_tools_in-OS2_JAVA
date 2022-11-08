import java.util.Random;

class Reader implements Runnable {

    private Message message;

    public Reader(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        String latestMessage;
        do{
            latestMessage= message.read();
            System.out.println(latestMessage);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Reader Thread Interrupted!!!");
            }
        }while(!"Finished!".equals(latestMessage));
    }
}
