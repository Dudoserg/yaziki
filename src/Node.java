import java.util.ArrayList;

public class Node {


    static final int TYPE_FUNC = 1;
    static final int TYPE_DOUBLE = 2;
    static final int TYPE_INTEGER = 3;
    static final int TYPE_UNKNOWN = 4;
    static final int TYPE_BLACK = 5;
    static final int TYPE_CHAR = 6;
    static final int TYPE_BOOLEAN = 7;
    static int countNode = 0;

    int nameNode;
    ArrayList<Character> id;        // Изображение

    int dataType;                   // тип(функция инт ...

    ArrayList<Character> data;      // значение

    int param;                      // количество параметров

    int returnType;                 // возвращаемый тип

    int flag_constant;      //

    int flag_declared;

    SavePoint savePoint_before_body_function;

    //////////////////////////////////////////////////////////////////////////
    TDataValue value;
    //////////////////////////////////////////////////////////////////////////

    public Node() {
        flag_constant = 0;
        flag_declared = 0;
    }

    public TDataValue value_copy(){
        return this.value.copy();
    }
}
