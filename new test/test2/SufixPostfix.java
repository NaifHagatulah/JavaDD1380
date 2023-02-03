import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class SufixPostfix {
    private int getPreference(char c){
        if(c=='+'|| c=='-') return 1;
        else if(c=='*' || c=='/') return 2;
        else return -1;
    }
    private int calculatePostFix(List<String> postFixList){
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<postFixList.size();i++){
            String word = postFixList.get(i);
            if(word.length()==1 && (word.charAt(0)=='+'||word.charAt(0)=='-'||word.charAt(0)=='*'||word.charAt(0)=='/')){
                int number2 = stack.pop();
                int number1 = stack.pop();
                if(word.charAt(0)=='+'){
                    int number = number1+number2;
                    stack.push(number);
                }else if(word.charAt(0)=='-'){
                    int number = number1-number2;
                    stack.push(number);
                }else if(word.charAt(0)=='*'){
                    int number = number1*number2;
                    stack.push(number);
                }else{
                    int number = number1/number2;
                    stack.push(number);
                }
            }else{
                int number = Integer.parseInt(word);
                stack.push(number);
            }
        }
        return stack.peek();
    }
    private List<String> getPostFixString(String s){
        Stack<Character> stack = new Stack<>();
        List<String> postFixList = new ArrayList<>();
        boolean flag = false;
        for(int i=0;i<s.length();i++){
            char word = s.charAt(i);
            if(word==' '){
                continue;
            }
            if(word=='('){
                stack.push(word);
                flag = false;
            }else if(word==')'){
                flag = false;
                while(!stack.isEmpty()){
                    if(stack.peek()=='('){
                        stack.pop();
                        break;
                    }else{
                        postFixList.add(stack.pop()+"");
                    }
                }
            }else if(word=='+' || word=='-' || word=='*' || word=='/'){
                flag = false;
                if(stack.isEmpty()){
                    stack.push(word);
                }
                else{
                    while(!stack.isEmpty() && getPreference(stack.peek())>=getPreference(word)){
                        postFixList.add(stack.pop()+"");
                    }
                    stack.push(word);
                }
            }else{
                if(flag){
                    String lastNumber = postFixList.get(postFixList.size()-1);
                    lastNumber+=word;
                    postFixList.set(postFixList.size()-1, lastNumber);
                }else
                postFixList.add(word+"");
                flag = true;
            }
        }
        while(!stack.isEmpty()){
            postFixList.add(stack.pop()+"");
        }
        return postFixList;
    }
    public  int calculate(String s) {
        List<String> postFixString = getPostFixString(s);
        return calculatePostFix(postFixString);
    }
    public static void main(String[] args)throws IOException {
        SufixPostfix test = new SufixPostfix();
       
        String infix;  
        //create an input stream object  
        BufferedReader keyboard = new BufferedReader (new InputStreamReader(System.in));  
        //get input from user  
        System.out.print("\nEnter the infix expression you want to convert: ");  
        infix = keyboard.readLine(); 
        int res = test.calculate(infix); 
        System.out.println(res);
    }
}