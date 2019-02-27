public class TDataValue {
    int data_Int;
    double data_Double;
    char data_Char;
    boolean data_Boolean;


    public TDataValue(int data_Int, double data_Double, char data_Char, boolean data_Boolean) {
        this.data_Int = data_Int;
        this.data_Double = data_Double;
        this.data_Char = data_Char;
        this.data_Boolean = data_Boolean;
    }
    public  TDataValue(){

    }

    public TDataValue copy(){
        return new TDataValue(this.data_Int, this.data_Double, this.data_Char, this.data_Boolean);
    }

    private void clearData(){
        this.data_Int = 0;
        this.data_Double = 0;
        this.data_Boolean = false;
        this.data_Char = Character.MIN_VALUE;;
    }

    void set_data_Int(int x){
        clearData();
        this.data_Int = x;
    }
    void set_data_Int(double x){
        clearData();
        this.data_Int = (int)x;
    }
    void set_data_Int(char x){
        clearData();
        this.data_Int = x;
    }

    void set_data_Double(int x){
        clearData();
        this.data_Double = x;
    }
    void set_data_Double(double x){
        clearData();
        this.data_Double = x;
    }
    void set_data_Double(char x){
        clearData();
        this.data_Double = x;
    }

    void set_data_Char(int x){
        clearData();
        this.data_Char = (char)x;
    }
    void set_data_Char(double x){
        clearData();
        this.data_Char = (char)x;
    }

    void set_data_Char(char x){
        clearData();
        this.data_Char = x;
    }



}
