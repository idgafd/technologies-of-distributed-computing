public class Class2 {
    private static float data;

    public Class2(){
        this.data = 0F;
    }

    public Class2(float data){
        this.data = data;
    }

    public Class2(Class2 class2){
        this.data = class2.data;
    }

    public static float getData() {
        return data;
    }

    public static void setData(float new_data) {
        data = new_data;
    }
}
