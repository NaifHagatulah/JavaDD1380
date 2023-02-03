import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.lang.*;
public class favtutor{

    public static int precedence(char x){

        if(x=='^'){   // highest precedence
            return 2;
        }
        else if(x=='*' || x=='/'){
            return 1;                        // second highest precedence
        }
        else if(x=='+' || x=='-'){
            // lowest precedence

            return 0;
        }
        return -1; // not an operator
    }

    public static String InfixToPostfix(String str){

        Stack stk= new Stack();             // used for converting infix to postfix

        String ans="";                // string containing our final answer

        int n= str.length();

        for (int i = 0; i <n ; i++) {
            char x= str.charAt(i);

            if(x>='0' && x<='9'){
                ans+=x;
            }

            else if(x=='('){     // push directly in the stack
                stk.push('(');
            }
            else if(x==')'){

                while(!stk.isEmpty() && stk.peek()!='('){          // keep popping till opening bracket is found
                    ans+=stk.pop();
                }
                if(!stk.isEmpty()){
                    stk.pop();
                }

            }
            else{

                while(!stk.isEmpty() && precedence(stk.peek())>=precedence(x)){    // remove all higher precedence values
                    ans+=stk.pop();
                }
                stk.push(x);

            }
        }
        while(!stk.isEmpty()){
            ans+=stk.pop();
        }
        return ans;
    }
    public static void main(String argv[]) throws IOException  
{  
String infix;  
//create an input stream object  
BufferedReader keyboard = new BufferedReader (new InputStreamReader(System.in));  
//get input from user  
System.out.print("\nEnter the infix expression you want to convert: ");  
infix = keyboard.readLine();  
//output as postfix
String res =   InfixToPostfix(infix);
int result = evaluate(res);
System.out.println("Postfix expression for the given infix expression is:" + InfixToPostfix(infix));
System.out.println("Postfix expression for the given infix expression is:" + result );  
} 
public static int evaluate(String expr)  {
    Stack stack = new Stack();
    int operators = 0;
    if(expr.equals(""))
        throw new Exception(expr);
    for (String symbol : expr.split(" ")){
        if(symbol.equals(""))
            continue;
        if(expr.length() == 0)
            throw new Exception(expr);
        if(isOperator(symbol)){
            if(stack.size() < 2)
                throw new Exception(expr);
            Integer valueTwo = Integer.parseInt(String.valueOf(stack.pop()));
            Integer valueOne = Integer.parseInt(String.valueOf(stack.pop()));
            
            if(symbol.equals("-")){
                stack.push(valueOne - valueTwo);
            }
            if(symbol.equals("*")){
                stack.push(valueOne * valueTwo);
            }
            if(symbol.equals("/")){
                if(valueTwo == 0)
                    throw new Exception(expr);
                else if(valueOne == 0)
                    stack.push(0);
                    
                else 
                    stack.push(valueOne / valueTwo);
            }
            if(symbol.equals("+")){
                stack.push(valueOne + valueTwo);
            }
            operators = 0;
        }
        else if(isInteger(symbol)){
            operators++;
            stack.push(Integer.parseInt(symbol));
        }
        else{
            throw new Exception(expr);
        }
    }
    if(stack.size() != 1)
        throw new Exception(expr);
    return stack.peek();
    
}

/**
 * Returns true if s is an operator.
 *
 * A word of caution on using the String.matches method: it returns true
 * if and only if the whole given string matches the regex. Therefore
 * using the regex "[0-9]" is equivalent to "^[0-9]$".
 *
 * An operator is one of '+', '-', '*', '/'.
 */
private static boolean isOperator(String s) {
    if(s.matches("[+*-]|[/]") )
        return true;
    
    return false;
}

/**
 * Returns true if s is an integer.
 *
 * A word of caution on using the String.matches method: it returns true
 * if and only if the whole given string matches the regex. Therefore
 * using the regex "[0-9]" is equivalent to "^[0-9]$".
 *
 * We accept two types of integers:
 *
 * - the first type consists of an optional '-'
 *   followed by a non-zero digit
 *   followed by zero or more digits,
 *
 * - the second type consists of an optional '-'
 *   followed by a single '0'.
 */
private static boolean isInteger(String s) {
    return(s.matches("(-?[1-9][0-9]*)|(-?[0])"));
                            
}
} 
