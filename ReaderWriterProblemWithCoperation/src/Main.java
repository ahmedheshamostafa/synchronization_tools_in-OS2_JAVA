public class Main {
    public static void main(String[] args) {
        //Shared message object between Reader and Writer threads.
        Message message = new Message();

        Thread writerThread = new Thread(new Writer(message));
        Thread readerThread = new Thread(new Reader(message));

        writerThread.start();
        readerThread.start();
    }
}