import java.lang.Math;

public class MyThread implements Runnable{
    Common common;
    int K;
    MyThread(Common common, int K){
        this.common = common;
        this.K = K;
    }

    public void run(){
        for(int i = 0; i < K; i++){
            common.increment();
        }
    }
}

class Common {

    Class1 class1;
    Class2 class2;

    synchronized void increment(){
        try{
            float new_data1 = (float)(1 + Math.random()*10) + class1.getData();
            float new_data2 = (float)(1 + Math.random()*10) + class2.getData();
            class1.setData(new_data1);
            class2.setData(new_data2);
            //System.out.printf("%s %f %f\n", Thread.currentThread().getName(), class1.getData(), class2.getData());
        }catch(Exception e){
            System.out.println("Thread has been interrupted\n");
        }
    }
}
