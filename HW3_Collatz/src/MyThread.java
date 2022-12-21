import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class MyThread implements Runnable {
    private Queue<Integer> queue;
    ReentrantLock lock = new ReentrantLock();
    public MyThread(Queue<Integer> queue){
        this.queue=queue;
    }

    @Override
    public void run() {
        processCommand();
    }

    private void processCommand() {
        lock.lock();
        try {
            int digit = queue.element();
            if(digit != 1){
                if(digit % 2 == 0){
                    digit = digit / 2;
                }else{
                    digit = 3 * digit + 1;
                }
                queue.add(digit);
                queue.remove();
            }else{
                queue.remove();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            lock.unlock();
        }

//        if (queue.isEmpty() != true){
//            processCommand();
//        }
    }
}

