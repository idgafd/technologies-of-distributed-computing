import java.util.*;

class MyThread implements Runnable{
    String name;
    int time;
    Random rand = new Random();

    public MyThread(String name){
        this.name = name;
        time = rand.nextInt(777);
    }

    public void run(){
        System.out.printf("%s sleeps %d\n", name, time);
        try{
            Thread.sleep(time);
        }catch(InterruptedException e){
            System.out.println("Thread has been interrupted\n");
        }
    }
}