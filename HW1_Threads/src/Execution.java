public class Execution {
    public static void main(String[] args) throws Exception{
        System.out.println("Main thread started...");
        for(int i=1; i < 6; i++){
            Thread t = new Thread(new MyThread("Thread " + i));
            t.start();
            // розкоментуйте цю частину, якщо хочете побачити синхронізовані потоки.
            try{
                t.join();
            }
            catch(InterruptedException e) {
                System.out.printf("%s has been interrupted", t.getName());
            }
        }
        System.out.println("Main thread finished...");
    }
}