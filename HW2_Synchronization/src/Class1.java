public class Class1 {
    private static float data;

    public Class1(){
        this.data = 0F;
    }

    public Class1(float data){
        this.data = data;
    }

    public Class1(Class1 class1){
        this.data = class1.data;
    }

    public static float getData() {
        return data;
    }

    public static void setData(float new_data) {
        data = new_data;
    }

}
