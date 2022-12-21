import java.lang.Math;

public class Execution {
    public static void main(String[] args) throws Exception {
        long time = System.currentTimeMillis();
        int N = (int)(10 + Math.random()*10);
        int K1 = (int)(10000 + Math.random()*10000);
        int K2 = (int)(10000 + Math.random()*10000);

        Thread[] threads = new Thread[N];
        Common common = new Common();

        for(int i = 0; i < N; i++){
            Thread t;
            if(i < N/2){
                t = new Thread(new MyThread(common, K1));
            }else{
                t = new Thread(new MyThread(common, K2));
            }
            t.start();
            threads[i] = t;
        }

        for(int i = 0; i < N; i++){
            try{
                threads[i].join();
            }catch(InterruptedException e){
                System.out.printf("%s has been interrupted", threads[i].getName());
            }
        }
        System.out.printf("Execution time: %2.0f milliseconds", (float)(System.currentTimeMillis() - time));
    }
}

