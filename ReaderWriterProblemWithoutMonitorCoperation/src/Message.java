/*this example extracted from fllowing tutorial 
https://www.digitalocean.com/community/tutorials/java-thread-wait-notify-and-notifyall-example
*/
class Message {
    String message;
    boolean empty = true;

    //Method used by reader
    public synchronized String read() {
        while (empty) ;//if message is empty then keep looping.
        empty = true;//Reader reads the message and marks empty as true.
        return message;//Reader reads the message.
    }

    //Method used by writer
    public synchronized void write(String message) {
        while (!empty) ;//if message is not empty then keep looping.
        this.message = message;//Writer writes the message.
        empty = false;//Now make empty as false.
    }
}
