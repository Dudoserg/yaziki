import java.sql.Array;
import java.util.ArrayList;

public class Interpreter {

    private String arrayChar2String(ArrayList<Character> l){

        String result = "";
        for(int i = 0 ; i < l.size(); i++)
            result += l.get(i);
        return result;
    }


    // Устанавливаем ЗНАЧЕНИЕ переменной в КОНТЕЙНЕРЕ
    public void saveValue(ArrayList<Character> l, Container container){
        switch (container.type){
            case Node.TYPE_INTEGER :{
                // Преобразуем массив символов в строку
                String valueStr = arrayChar2String(l);
                // Сохраняем в контейнер значение константы
                container.value.data_Int = Integer.parseInt(valueStr);
                break;
            }
            case Node.TYPE_DOUBLE:{
                String valueStr = arrayChar2String(l);
                container.value.data_Double = Double.parseDouble(valueStr);
                break;
            }
            case Node.TYPE_CHAR:{
                // Берем первый элемент массива, т.к. у меня нет строк, то там всегда будет 1 символ в случае типа ЧАР
                container.value.data_Char = l.get(0);
                break;
            }
            case Node.TYPE_BOOLEAN:{
                break;
            }
        }
    }

    public void setValue_from_Tree(Tree node, Container container){
        container.value = node.n.value_copy();
    }


    public void calculate( Container first, Container second, int sign){
        switch (first.type){
            case Node.TYPE_INTEGER :{

                switch (second.type){
                    // int int
                    case Node.TYPE_INTEGER :{
                        first.type =  Node.TYPE_INTEGER;        // теперь тип Int
                        // *
                        if( sign == Scaner._STAR){
                            first.value.data_Int *= second.value.data_Int;
                        }
                        // /
                        else if( sign == Scaner._SLASH){
                            first.value.data_Int /= second.value.data_Int;
                        }
                        // %
                        else if( sign == Scaner._PERCENT){
                            first.value.data_Int %= second.value.data_Int;
                        }
                        // +
                        else if( sign == Scaner._PLUS){
                            first.value.data_Int += second.value.data_Int;
                        }
                        // -
                        else if( sign == Scaner._MINUS){
                            first.value.data_Int -= second.value.data_Int;
                        }
                        // <<
                        else if( sign == Scaner._SHIFT_LEFT){
                            first.value.data_Int = first.value.data_Int << second.value.data_Int;
                        }
                        //  >>
                        else if( sign == Scaner._SHIFT_RIGHT){
                            first.value.data_Int = first.value.data_Int >> second.value.data_Int;
                        }
                        //  >
                        else if( sign == Scaner._GREAT){
                            if(first.value.data_Int > second.value.data_Int)
                                first.value.data_Int = 1;
                            else
                                first.value.data_Int = 0;
                        }
                        //  <
                        else if( sign == Scaner._LESS){
                            if(first.value.data_Int < second.value.data_Int)
                                first.value.data_Int = 1;
                            else
                                first.value.data_Int = 0;
                        }
                        //  >=
                        else if( sign == Scaner._GREAT_EQUALLY){
                            if(first.value.data_Int >= second.value.data_Int)
                                first.value.data_Int = 1;
                            else
                                first.value.data_Int = 0;
                        }
                        //  <=
                        else if( sign == Scaner._LESS_EQUALLY){
                            if(first.value.data_Int <= second.value.data_Int)
                                first.value.data_Int = 1;
                            else
                                first.value.data_Int = 0;
                        }
                        //  ==
                        else if( sign == Scaner._EQUALLY){
                            if(first.value.data_Int == second.value.data_Int)
                                first.value.data_Int = 1;
                            else
                                first.value.data_Int = 0;
                        }
                        //  !=
                        else if( sign == Scaner._NOT_EQUALLY){
                            if(first.value.data_Int != second.value.data_Int)
                                first.value.data_Int = 1;
                            else
                                first.value.data_Int = 0;
                        }

                        break;
                    }

                    // int double
                    case Node.TYPE_DOUBLE:{
                        first.type =  Node.TYPE_DOUBLE;

                        // *
                        if( sign == Scaner._STAR){
                            first.value.set_data_Double( first.value.data_Int * second.value.data_Double);
                        }
                        // /
                        else if( sign == Scaner._SLASH){
                            first.value.set_data_Double( first.value.data_Int / second.value.data_Double);
                        }
                        // %
                        else if( sign == Scaner._PERCENT){  /////////////////////////////////////////////////////////////////////////////////////
                            first.value.set_data_Double( first.value.data_Int % second.value.data_Double);
                        }
                        // +
                        else if( sign == Scaner._PLUS){
                            first.value.set_data_Double( first.value.data_Int + second.value.data_Double);
                        }
                        // -
                        else if( sign == Scaner._MINUS){
                            first.value.set_data_Double( first.value.data_Int - second.value.data_Double);
                        }
                        // <<
                        else if( sign == Scaner._SHIFT_LEFT){///////////////////////////////////////////////////////////////////////////////////////////////////////////////

                        }
                        //  >>
                        else if( sign == Scaner._SHIFT_RIGHT){///////////////////////////////////////////////////////////////////////////////////////////////////////////////

                        }
                        //  >
                        else if( sign == Scaner._GREAT){
                            if(first.value.data_Int > second.value.data_Double)
                                first.value.set_data_Double(1);
                            else
                                first.value.set_data_Double(0);
                        }
                        //  <
                        else if( sign == Scaner._LESS){
                            if(first.value.data_Int < second.value.data_Double)
                                first.value.set_data_Double(1);
                            else
                                first.value.set_data_Double(0);
                        }
                        //  >=
                        else if( sign == Scaner._GREAT_EQUALLY){
                            if(first.value.data_Int >= second.value.data_Double)
                                first.value.set_data_Double(1);
                            else
                                first.value.set_data_Double(0);
                        }
                        //  <=
                        else if( sign == Scaner._LESS_EQUALLY){
                            if(first.value.data_Int <= second.value.data_Double)
                                first.value.set_data_Double(1);
                            else
                                first.value.set_data_Double(0);
                        }
                        //  ==
                        else if( sign == Scaner._EQUALLY){
                            if(first.value.data_Int == second.value.data_Double)
                                first.value.set_data_Double(1);
                            else
                                first.value.set_data_Double(0);
                        }
                        //  !=
                        else if( sign == Scaner._NOT_EQUALLY){
                            if(first.value.data_Int != second.value.data_Double)
                                first.value.set_data_Double(1);
                            else
                                first.value.set_data_Double(0);
                        }


                        break;
                    }

                    // int char
                    case Node.TYPE_CHAR:{
                        first.type =  Node.TYPE_INTEGER;

                        // *
                        if( sign == Scaner._STAR){
                            first.value.set_data_Int( first.value.data_Int * second.value.data_Char);
                        }
                        // /
                        else if( sign == Scaner._SLASH){
                            first.value.set_data_Int( first.value.data_Int / second.value.data_Char);

                        }
                        // %
                        else if( sign == Scaner._PERCENT){  /////////////////////////////////////////////////////////////////////////////////////
                            first.value.set_data_Int( first.value.data_Int % second.value.data_Char);

                        }
                        // +
                        else if( sign == Scaner._PLUS){
                            first.value.set_data_Int( first.value.data_Int + second.value.data_Char);

                        }
                        // -
                        else if( sign == Scaner._MINUS){
                            first.value.set_data_Int( first.value.data_Int - second.value.data_Char);

                        }
                        // <<
                        else if( sign == Scaner._SHIFT_LEFT){///////////////////////////////////////////////////////////////////////////////////////////////////////////////
                            first.value.set_data_Int( first.value.data_Int << second.value.data_Char);

                        }
                        //  >>
                        else if( sign == Scaner._SHIFT_RIGHT){///////////////////////////////////////////////////////////////////////////////////////////////////////////////
                            first.value.set_data_Int( first.value.data_Int >> second.value.data_Char);

                        }
                        //  >
                        else if( sign == Scaner._GREAT){
                            if(first.value.data_Int > second.value.data_Char)
                                first.value.set_data_Int(1);
                            else
                                first.value.set_data_Int(0);

                        }
                        //  <
                        else if( sign == Scaner._LESS){
                            if(first.value.data_Int < second.value.data_Char)
                                first.value.set_data_Int(1);
                            else
                                first.value.set_data_Int(0);;
                        }
                        //  >=
                        else if( sign == Scaner._GREAT_EQUALLY){
                            if(first.value.data_Int >= second.value.data_Char)
                                first.value.set_data_Int(1);
                            else
                                first.value.set_data_Int(0);
                        }
                        //  <=
                        else if( sign == Scaner._LESS_EQUALLY){
                            if(first.value.data_Int <= second.value.data_Char)
                                first.value.set_data_Int(1);
                            else
                                first.value.set_data_Int(0);
                        }
                        //  ==
                        else if( sign == Scaner._EQUALLY){
                            if(first.value.data_Int == second.value.data_Char)
                                first.value.set_data_Int(1);
                            else
                                first.value.set_data_Int(0);
                        }
                        //  !=
                        else if( sign == Scaner._NOT_EQUALLY){
                            if(first.value.data_Int != second.value.data_Char)
                                first.value.set_data_Int(1);
                            else
                                first.value.set_data_Int(0);
                        }
                        break;
                    }
                }

                break;
            }

            case Node.TYPE_DOUBLE:{
                switch (second.type){

                    // double int
                    case Node.TYPE_INTEGER :{
                        first.type =  Node.TYPE_DOUBLE;

                        // *
                        if( sign == Scaner._STAR){
                            first.value.set_data_Double( first.value.data_Double * second.value.data_Int);
                        }
                        // /
                        else if( sign == Scaner._SLASH){
                            first.value.set_data_Double( first.value.data_Double / second.value.data_Int);
                        }
                        // %
                        else if( sign == Scaner._PERCENT){  /////////////////////////////////////////////////////////////////////////////////////
                            first.value.set_data_Double( first.value.data_Double % second.value.data_Int);
                        }
                        // +
                        else if( sign == Scaner._PLUS){
                            first.value.set_data_Double( first.value.data_Double + second.value.data_Int);
                        }
                        // -
                        else if( sign == Scaner._MINUS){
                            first.value.set_data_Double( first.value.data_Double - second.value.data_Int);
                        }
                        // <<
                        else if( sign == Scaner._SHIFT_LEFT){///////////////////////////////////////////////////////////////////////////////////////////////////////////////


                        }
                        //  >>
                        else if( sign == Scaner._SHIFT_RIGHT){///////////////////////////////////////////////////////////////////////////////////////////////////////////////


                        }
                        //  >
                        else if( sign == Scaner._GREAT){
                            if(first.value.data_Double > second.value.data_Int)
                                first.value.set_data_Double(1);
                            else
                                first.value.set_data_Double(0);

                        }
                        //  <
                        else if( sign == Scaner._LESS){
                            if(first.value.data_Double < second.value.data_Int)
                                first.value.set_data_Double(1);
                            else
                                first.value.set_data_Double(0);
                        }
                        //  >=
                        else if( sign == Scaner._GREAT_EQUALLY){
                            if(first.value.data_Double >= second.value.data_Int)
                                first.value.set_data_Double(1);
                            else
                                first.value.set_data_Double(0);
                        }
                        //  <=
                        else if( sign == Scaner._LESS_EQUALLY){
                            if(first.value.data_Double <= second.value.data_Int)
                                first.value.set_data_Double(1);
                            else
                                first.value.set_data_Double(0);
                        }
                        //  ==
                        else if( sign == Scaner._EQUALLY){
                            if(first.value.data_Double == second.value.data_Int)
                                first.value.set_data_Double(1);
                            else
                                first.value.set_data_Double(0);
                        }
                        //  !=
                        else if( sign == Scaner._NOT_EQUALLY){
                            if(first.value.data_Double != second.value.data_Int)
                                first.value.set_data_Double(1);
                            else
                                first.value.set_data_Double(0);
                        }
                        break;
                    }

                    // double double
                    case Node.TYPE_DOUBLE:{
                        first.type =  Node.TYPE_DOUBLE;

                        // *
                        if( sign == Scaner._STAR){
                            first.value.set_data_Double( first.value.data_Double * second.value.data_Double);
                        }
                        // /
                        else if( sign == Scaner._SLASH){
                            first.value.set_data_Double( first.value.data_Double / second.value.data_Double);
                        }
                        // %
                        else if( sign == Scaner._PERCENT){  /////////////////////////////////////////////////////////////////////////////////////
                            first.value.set_data_Double( first.value.data_Double % second.value.data_Double);
                        }
                        // +
                        else if( sign == Scaner._PLUS){
                            first.value.set_data_Double( first.value.data_Double + second.value.data_Double);
                        }
                        // -
                        else if( sign == Scaner._MINUS){
                            first.value.set_data_Double( first.value.data_Double - second.value.data_Double);
                        }
                        // <<
                        else if( sign == Scaner._SHIFT_LEFT){///////////////////////////////////////////////////////////////////////////////////////////////////////////////


                        }
                        //  >>
                        else if( sign == Scaner._SHIFT_RIGHT){///////////////////////////////////////////////////////////////////////////////////////////////////////////////


                        }
                        //  >
                        else if( sign == Scaner._GREAT){
                            if(first.value.data_Double > second.value.data_Double)
                                first.value.set_data_Double(1);
                            else
                                first.value.set_data_Double(0);

                        }
                        //  <
                        else if( sign == Scaner._LESS){
                            if(first.value.data_Double < second.value.data_Double)
                                first.value.set_data_Double(1);
                            else
                                first.value.set_data_Double(0);
                        }
                        //  >=
                        else if( sign == Scaner._GREAT_EQUALLY){
                            if(first.value.data_Double >= second.value.data_Double)
                                first.value.set_data_Double(1);
                            else
                                first.value.set_data_Double(0);
                        }
                        //  <=
                        else if( sign == Scaner._LESS_EQUALLY){
                            if(first.value.data_Double <= second.value.data_Double)
                                first.value.set_data_Double(1);
                            else
                                first.value.set_data_Double(0);
                        }
                        //  ==
                        else if( sign == Scaner._EQUALLY){
                            if(first.value.data_Double == second.value.data_Double)
                                first.value.set_data_Double(1);
                            else
                                first.value.set_data_Double(0);
                        }
                        //  !=
                        else if( sign == Scaner._NOT_EQUALLY){
                            if(first.value.data_Double != second.value.data_Double)
                                first.value.set_data_Double(1);
                            else
                                first.value.set_data_Double(0);
                        }

                        break;
                    }

                    //double char
                    case Node.TYPE_CHAR:{
                        first.type =  Node.TYPE_DOUBLE;

                        // *
                        if( sign == Scaner._STAR){
                            first.value.set_data_Double( first.value.data_Double * second.value.data_Char);
                        }
                        // /
                        else if( sign == Scaner._SLASH){
                            first.value.set_data_Double( first.value.data_Double / second.value.data_Char);
                        }
                        // %
                        else if( sign == Scaner._PERCENT){  /////////////////////////////////////////////////////////////////////////////////////
                            first.value.set_data_Double( first.value.data_Double % second.value.data_Char);
                        }
                        // +
                        else if( sign == Scaner._PLUS){
                            first.value.set_data_Double( first.value.data_Double + second.value.data_Char);
                        }
                        // -
                        else if( sign == Scaner._MINUS){
                            first.value.set_data_Double( first.value.data_Double - second.value.data_Char);
                        }
                        // <<
                        else if( sign == Scaner._SHIFT_LEFT){///////////////////////////////////////////////////////////////////////////////////////////////////////////////


                        }
                        //  >>
                        else if( sign == Scaner._SHIFT_RIGHT){///////////////////////////////////////////////////////////////////////////////////////////////////////////////


                        }
                        //  >
                        else if( sign == Scaner._GREAT){
                            if(first.value.data_Double > second.value.data_Char)
                                first.value.set_data_Double(1);
                            else
                                first.value.set_data_Double(0);

                        }
                        //  <
                        else if( sign == Scaner._LESS){
                            if(first.value.data_Double < second.value.data_Char)
                                first.value.set_data_Double(1);
                            else
                                first.value.set_data_Double(0);
                        }
                        //  >=
                        else if( sign == Scaner._GREAT_EQUALLY){
                            if(first.value.data_Double >= second.value.data_Char)
                                first.value.set_data_Double(1);
                            else
                                first.value.set_data_Double(0);
                        }
                        //  <=
                        else if( sign == Scaner._LESS_EQUALLY){
                            if(first.value.data_Double <= second.value.data_Char)
                                first.value.set_data_Double(1);
                            else
                                first.value.set_data_Double(0);
                        }
                        //  ==
                        else if( sign == Scaner._EQUALLY){
                            if(first.value.data_Double == second.value.data_Char)
                                first.value.set_data_Double(1);
                            else
                                first.value.set_data_Double(0);
                        }
                        //  !=
                        else if( sign == Scaner._NOT_EQUALLY){
                            if(first.value.data_Double != second.value.data_Char)
                                first.value.set_data_Double(1);
                            else
                                first.value.set_data_Double(0);
                        }

                        break;
                    }
                }
                break;
            }

            case Node.TYPE_CHAR:{
                switch (second.type){
                    // char int
                    case Node.TYPE_INTEGER :{
                        first.type =  Node.TYPE_INTEGER;

                        // *
                        if( sign == Scaner._STAR){
                            first.value.set_data_Int( (first.value.data_Char * second.value.data_Int) );
                        }
                        // /
                        else if( sign == Scaner._SLASH){
                            first.value.set_data_Int( (first.value.data_Char / second.value.data_Int) );
                        }
                        // %
                        else if( sign == Scaner._PERCENT){  /////////////////////////////////////////////////////////////////////////////////////
                            first.value.set_data_Int( (first.value.data_Char % second.value.data_Int) );
                        }
                        // +
                        else if( sign == Scaner._PLUS){
                            first.value.set_data_Int( (first.value.data_Char + second.value.data_Int) );
                        }
                        // -
                        else if( sign == Scaner._MINUS){
                            first.value.set_data_Int( (first.value.data_Char - second.value.data_Int) );
                        }
                        // <<
                        else if( sign == Scaner._SHIFT_LEFT){///////////////////////////////////////////////////////////////////////////////////////////////////////////////


                        }
                        //  >>
                        else if( sign == Scaner._SHIFT_RIGHT){///////////////////////////////////////////////////////////////////////////////////////////////////////////////


                        }
                        //  >
                        else if( sign == Scaner._GREAT){
                            if(first.value.data_Char > second.value.data_Int)
                                first.value.set_data_Int( 1 );
                            else
                                first.value.set_data_Int( 0 );
                        }
                        //  <
                        else if( sign == Scaner._LESS){
                            if(first.value.data_Char < second.value.data_Int)
                                first.value.set_data_Int( 1 );
                            else
                                first.value.set_data_Int( 0 );
                        }
                        //  >=
                        else if( sign == Scaner._GREAT_EQUALLY){
                            if(first.value.data_Char >= second.value.data_Int)
                                first.value.set_data_Int( 1 );
                            else
                                first.value.set_data_Int( 0 );
                        }
                        //  <=
                        else if( sign == Scaner._LESS_EQUALLY){
                            if(first.value.data_Char <= second.value.data_Int)
                                first.value.set_data_Int( 1 );
                            else
                                first.value.set_data_Int( 0 );
                        }
                        //  ==
                        else if( sign == Scaner._EQUALLY){
                            if(first.value.data_Char == second.value.data_Int)
                                first.value.set_data_Int( 1 );
                            else
                                first.value.set_data_Int( 0 );
                        }
                        //  !=
                        else if( sign == Scaner._NOT_EQUALLY){
                            if(first.value.data_Char != second.value.data_Int)
                                first.value.set_data_Int( 1 );
                            else
                                first.value.set_data_Int( 0 );
                        }

                        break;
                    }

                    //char double
                    case Node.TYPE_DOUBLE:{
                        first.type =  Node.TYPE_DOUBLE;

                        // *
                        if( sign == Scaner._STAR){
                            first.value.set_data_Double( (first.value.data_Char * second.value.data_Double) );
                        }
                        // /
                        else if( sign == Scaner._SLASH){
                            first.value.set_data_Double( (first.value.data_Char / second.value.data_Double) );
                        }
                        // %
                        else if( sign == Scaner._PERCENT){  /////////////////////////////////////////////////////////////////////////////////////
                            first.value.set_data_Double( (first.value.data_Char % second.value.data_Double) );
                        }
                        // +
                        else if( sign == Scaner._PLUS){
                            first.value.set_data_Double( (first.value.data_Char + second.value.data_Double) );
                        }
                        // -
                        else if( sign == Scaner._MINUS){
                            first.value.set_data_Double( (first.value.data_Char - second.value.data_Double) );
                        }
                        // <<
                        else if( sign == Scaner._SHIFT_LEFT){///////////////////////////////////////////////////////////////////////////////////////////////////////////////


                        }
                        //  >>
                        else if( sign == Scaner._SHIFT_RIGHT){///////////////////////////////////////////////////////////////////////////////////////////////////////////////


                        }
                        //  >
                        else if( sign == Scaner._GREAT){
                            if(first.value.data_Char > second.value.data_Double)
                                first.value.set_data_Double( 1 );
                            else
                                first.value.set_data_Double( 0 );
                        }
                        //  <
                        else if( sign == Scaner._LESS){
                            if(first.value.data_Char < second.value.data_Double)
                                first.value.set_data_Double( 1 );
                            else
                                first.value.set_data_Double( 0 );
                        }
                        //  >=
                        else if( sign == Scaner._GREAT_EQUALLY){
                            if(first.value.data_Char >= second.value.data_Double)
                                first.value.set_data_Double( 1 );
                            else
                                first.value.set_data_Double( 0 );
                        }
                        //  <=
                        else if( sign == Scaner._LESS_EQUALLY){
                            if(first.value.data_Char <= second.value.data_Double)
                                first.value.set_data_Double( 1 );
                            else
                                first.value.set_data_Double( 0 );
                        }
                        //  ==
                        else if( sign == Scaner._EQUALLY){
                            if(first.value.data_Char == second.value.data_Double)
                                first.value.set_data_Double( 1 );
                            else
                                first.value.set_data_Double( 0 );
                        }
                        //  !=
                        else if( sign == Scaner._NOT_EQUALLY){
                            if(first.value.data_Char != second.value.data_Double)
                                first.value.set_data_Double( 1 );
                            else
                                first.value.set_data_Double( 0 );
                        }

                        break;
                    }

                    // char char
                    case Node.TYPE_CHAR:{
                        first.type =  Node.TYPE_INTEGER;

                        // *
                        if( sign == Scaner._STAR){
                            first.value.set_data_Int( (first.value.data_Char * second.value.data_Char) );
                        }
                        // /
                        else if( sign == Scaner._SLASH){
                            first.value.set_data_Int( (first.value.data_Char / second.value.data_Char) );
                        }
                        // %
                        else if( sign == Scaner._PERCENT){  /////////////////////////////////////////////////////////////////////////////////////
                            first.value.set_data_Int( (first.value.data_Char % second.value.data_Char) );
                        }
                        // +
                        else if( sign == Scaner._PLUS){
                            first.value.set_data_Int( (first.value.data_Char + second.value.data_Char) );
                        }
                        // -
                        else if( sign == Scaner._MINUS){
                            first.value.set_data_Int( (first.value.data_Char - second.value.data_Char) );
                        }
                        // <<
                        else if( sign == Scaner._SHIFT_LEFT){///////////////////////////////////////////////////////////////////////////////////////////////////////////////


                        }
                        //  >>
                        else if( sign == Scaner._SHIFT_RIGHT){///////////////////////////////////////////////////////////////////////////////////////////////////////////////


                        }
                        //  >
                        else if( sign == Scaner._GREAT){
                            if(first.value.data_Char > second.value.data_Char)
                                first.value.set_data_Int( 1 );
                            else
                                first.value.set_data_Int( 0 );
                        }
                        //  <
                        else if( sign == Scaner._LESS){
                            if(first.value.data_Char < second.value.data_Char)
                                first.value.set_data_Int( 1 );
                            else
                                first.value.set_data_Int( 0 );
                        }
                        //  >=
                        else if( sign == Scaner._GREAT_EQUALLY){
                            if(first.value.data_Char >= second.value.data_Char)
                                first.value.set_data_Int( 1 );
                            else
                                first.value.set_data_Int( 0 );
                        }
                        //  <=
                        else if( sign == Scaner._LESS_EQUALLY){
                            if(first.value.data_Char <= second.value.data_Char)
                                first.value.set_data_Int( 1 );
                            else
                                first.value.set_data_Int( 0 );
                        }
                        //  ==
                        else if( sign == Scaner._EQUALLY){
                            if(first.value.data_Char == second.value.data_Char)
                                first.value.set_data_Int( 1 );
                            else
                                first.value.set_data_Int( 0 );
                        }
                        //  !=
                        else if( sign == Scaner._NOT_EQUALLY){
                            if(first.value.data_Char != second.value.data_Char)
                                first.value.set_data_Int( 1 );
                            else
                                first.value.set_data_Int( 0 );
                        }

                        break;
                    }
                }
                break;

            }
        }

    }


    // Записываем значение в дерево
    public void saveValue_in_Tree(Container container, Tree k){
        //
        k.n.value = container.value_Copy();

    }

}
