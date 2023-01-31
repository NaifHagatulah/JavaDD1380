import java.util.Scanner;

public class helper {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String math = input.nextLine();
        String[] phrase = math.split("\\s+");
        StringBuilder phraseMut = new StringBuilder();
    
        for (int i = 0; i < phrase.length; i++) {
          phraseMut.append(phrase[i]);
        }
        System.out.print(phraseMut);
    }

    public static int calculate(char[] value, int last){
        if(last == 1)
            return 0;
        for
    }
