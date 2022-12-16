import java.util.Queue;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.lang.Math;

public class Execution {
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        Queue<Integer> queue = new LinkedList<>();
        int N = (int)(10 + Math.random()*10);
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < N; i++) {
            queue.add(i);
        }

        while(queue.isEmpty() != true){
            Runnable myThread = new MyThread(queue);
            executor.execute(myThread);
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        System.out.println("Finished all threads");
        System.out.printf("Execution time: %2.0f milliseconds", (float)(System.currentTimeMillis() - time));
    }
}
