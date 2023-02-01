import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.RunElement;

public class helper {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    StringBuilder phraseMut = new StringBuilder(input.nextLine().replace(" ", ""));
    int result = calculate(phraseMut);
    System.out.println(result);
  }

  public static int[] findIndexOfParenthesis(StringBuilder phrase) {
    int[] index = new int[2];
    for (int i = 0; i < phrase.length(); i++) {
      if (phrase.charAt(i) == 40)
        index[0] = i + 1; 
      if (phrase.charAt(i) == 41) {
        index[1] = i; 
        return index;
      }
    }
    return index;
  }

  public static int calculate(StringBuilder phrase) {
    int[] index = findIndexOfParenthesis(phrase);
    if(index[1] != 0 && index[0] != 0){
    char[] innerPhrase = new char[index[1] - index[0]];
    phrase.getChars(index[0], index[1], innerPhrase, 0);  
    char[] result = doArthmetic(innerPhrase);
    phrase.delete(index[0] - 1, index[1] + 1);
    if(phrase.length() != 0){
        phrase.insert(index[0] - 1, result, 0, result.length);
        calculate(phrase);
    }
}
    char[] fullPhrase = new char[phrase.length() ];
    phrase.getChars(0, phrase.length(), fullPhrase, 0);
    char[] result = doArthmetic(fullPhrase);
    return charArrayToInt(result, 0, result.length);


    
  }

  public static int charArrayToInt(char[] data, int start, int end) throws NumberFormatException {
    int result = 0;
    for (int i = start; i < end; i++) {
      int digit = (int) data[i] - (int) '0';
      if ((digit < 0) || (digit > 9))
        throw new NumberFormatException();
      result *= 10;
      result += digit;
    }
    return result;
  }

  public static char[] doArthmetic(char[] mathPhrase){
    StringBuilder value = new StringBuilder();
    value.append(mathPhrase);
    int valid = 0;
    List<Integer> indexImproved = new ArrayList();
    for(int i = 0; i < value.length(); i++){
      valid = 0;
        if (value.charAt(i) == '+' || value.charAt(i) == '-' || value.charAt(i) == '*' || value.charAt(i) == '/') {
            indexImproved.add(i);
          }
        if(value.charAt(i) == '*'){
                for(int j = i + 1; j < value.length(); j++){
                    if (value.charAt(j) == '+' || value.charAt(j) == '-' || value.charAt(j) == '*' || value.charAt(j) == '/') {
                        indexImproved.add(j);
                        j = value.length();
                        valid  = 1;
                }
            }
            if(indexImproved.size() > 2 && valid == 1){
                char[] valueOneChar = new char[(int)indexImproved.get(indexImproved.size() - 2) - (int)indexImproved.get(indexImproved.size() - 3) - 1];
                value.getChars((int)indexImproved.get(indexImproved.size() - 3) + 1 , (int)indexImproved.get(indexImproved.size() - 2) , valueOneChar, 0);
                int valueOneint = charArrayToInt(valueOneChar, 0, valueOneChar.length);
                char[] valueTwoChar = new char[(int)indexImproved.get(indexImproved.size() - 1) - (int)indexImproved.get(indexImproved.size() - 2) - 1];
                value.getChars((int)indexImproved.get(indexImproved.size() - 2) + 1 , (int)indexImproved.get(indexImproved.size() - 1), valueTwoChar, 0);
                int valueTwoInt = charArrayToInt(valueTwoChar, 0, valueTwoChar.length);
                int resultInt = valueOneint * valueTwoInt;
                char[] resultChar = ("" + resultInt).toCharArray();
                value.delete((int)indexImproved.get(indexImproved.size() - 3) + 1, (int)indexImproved.get(indexImproved.size() - 1));
                System.out.println(value);
                value.insert((int)indexImproved.get(indexImproved.size() - 2) - valueOneChar.length , resultChar, 0, resultChar.length);
                System.out.println(value);
                indexImproved.remove(indexImproved.size() - 1);
                indexImproved.remove(indexImproved.size() - 1);
                i  = indexImproved.get(indexImproved.size() - 1) + 1;
            }
            else if(indexImproved.size() > 2 && valid == 0){
              char[] valueOneChar = new char[(int)indexImproved.get(indexImproved.size() - 1) - (int)indexImproved.get(indexImproved.size() - 2) - 1];
                value.getChars((int)indexImproved.get(indexImproved.size() - 2) + 1 , (int)indexImproved.get(indexImproved.size() - 1) , valueOneChar, 0);
                int valueOneint = charArrayToInt(valueOneChar, 0, valueOneChar.length);
                char[] valueTwoChar = new char[value.length() - (int)indexImproved.get(indexImproved.size() - 1) - 1];
                value.getChars((int)indexImproved.get(indexImproved.size() - 1) + 1 , value.length(), valueTwoChar, 0);
                int valueTwoInt = charArrayToInt(valueTwoChar, 0, valueTwoChar.length);
                int resultInt = valueOneint * valueTwoInt;
                char[] resultChar = ("" + resultInt).toCharArray();
                value.delete((int)indexImproved.get(indexImproved.size() - 2) + 1, value.length());
                System.out.println(value);
                value.insert((int)indexImproved.get(indexImproved.size() - 1) - valueOneChar.length , resultChar, 0, resultChar.length);
                System.out.println(value);
                indexImproved.remove(indexImproved.size() - 1);
            }
            else if(indexImproved.size() == 2  && valid == 1){ // Då har vi något som är i början och har mer saker efter.
                char[] valueOneChar = new char[(int)indexImproved.get(indexImproved.size() - 2)];
                value.getChars(0, (int)indexImproved.get(indexImproved.size() - 2), valueOneChar, 0);
                int valueOneint = charArrayToInt(valueOneChar, 0, valueOneChar.length);
                char[] valueTwoChar = new char[(int)indexImproved.get(indexImproved.size() - 1) - (int)indexImproved.get(indexImproved.size() - 2) - 1];
                value.getChars((int)indexImproved.get(indexImproved.size() - 2) + 1 , (int)indexImproved.get(indexImproved.size() - 1), valueTwoChar, 0);
                int valueTwoInt = charArrayToInt(valueTwoChar, 0, valueTwoChar.length);
                int resultInt = valueOneint * valueTwoInt;
                char[] resultChar = ("" + resultInt).toCharArray();
                value.delete(0, (int)indexImproved.get(indexImproved.size() - 1));
                System.out.println(value);
                value.insert(0 , resultChar, 0, resultChar.length);
                System.out.println(value);
                indexImproved.remove(indexImproved.size() - 1);
                indexImproved.remove(indexImproved.size() - 1);
                i = 0;
            }
            else if(indexImproved.size() == 2  && valid == 0){ // en sträng med endast 2 tecken och multi på sista positionen dvs slutet behanlda som speciall fall.
              char[] valueOneChar = new char[(int)indexImproved.get(indexImproved.size() - 1) - (int)indexImproved.get(indexImproved.size() - 2) - 1];
              value.getChars((int)indexImproved.get(indexImproved.size() - 2) + 1, (int)indexImproved.get(indexImproved.size() - 1) , valueOneChar, 0);
              int valueOneint = charArrayToInt(valueOneChar, 0, valueOneChar.length);
              char[] valueTwoChar = new char[value.length() -  (int)indexImproved.get(indexImproved.size() -1)  - 1];
              value.getChars( (int)indexImproved.get(indexImproved.size() -1) + 1, value.length(), valueTwoChar, 0);
              int valueTwoInt = charArrayToInt(valueTwoChar, 0, valueTwoChar.length);
              int resultInt = valueOneint * valueTwoInt;
              char[] resultChar = ("" + resultInt).toCharArray();
              value.delete((int)indexImproved.get(indexImproved.size() - 2) + 1 , value.length());
              System.out.println(value);
              value.insert((int)indexImproved.get(indexImproved.size() - 2) + 1 , resultChar, 0, resultChar.length);
              System.out.println(value);
              indexImproved.remove(indexImproved.size() - 1);
            } //slutet av en sträng ligger multi
            else if(indexImproved.size() == 1){
                char[] valueOneChar = new char[(int)indexImproved.get(indexImproved.size() - 1)];
                value.getChars(0, (int)indexImproved.get(indexImproved.size() - 1), valueOneChar, 0);
                int valueOneint = charArrayToInt(valueOneChar, 0, valueOneChar.length);
                char[] valueTwoChar = new char[value.length() -  (int)indexImproved.get(indexImproved.size() -1)  - 1];
                value.getChars( (int)indexImproved.get(indexImproved.size() -1) + 1, value.length(), valueTwoChar, 0);
                int valueTwoInt = charArrayToInt(valueTwoChar, 0, valueTwoChar.length);
                int resultInt = valueOneint * valueTwoInt;
                char[] resultChar = ("" + resultInt).toCharArray();
                value.delete(0, value.length());
                System.out.println(value);
                value.insert(0 , resultChar, 0, resultChar.length);
                System.out.println(value);
            }
        }
         else if(value.charAt(i) == '/'){
            for(int j = i + 1; j < value.length(); j++){
                if (value.charAt(j) == '+' || value.charAt(j) == '-' || value.charAt(j) == '*' || value.charAt(j) == '/') {
                    indexImproved.add(j);
                    j = value.length();
                    valid  = 1;
            }
        }
        if(indexImproved.size() > 2 && valid == 1){
          char[] valueOneChar = new char[(int)indexImproved.get(indexImproved.size() - 2) - (int)indexImproved.get(indexImproved.size() - 3) - 1];
          value.getChars((int)indexImproved.get(indexImproved.size() - 3) + 1 , (int)indexImproved.get(indexImproved.size() - 2) , valueOneChar, 0);
          int valueOneint = charArrayToInt(valueOneChar, 0, valueOneChar.length);
          char[] valueTwoChar = new char[(int)indexImproved.get(indexImproved.size() - 1) - (int)indexImproved.get(indexImproved.size() - 2) - 1];
          value.getChars((int)indexImproved.get(indexImproved.size() - 2) + 1 , (int)indexImproved.get(indexImproved.size() - 1), valueTwoChar, 0);
          int valueTwoInt = charArrayToInt(valueTwoChar, 0, valueTwoChar.length);
          int resultInt = valueOneint / valueTwoInt;
          char[] resultChar = ("" + resultInt).toCharArray();
          value.delete((int)indexImproved.get(indexImproved.size() - 3) + 1, (int)indexImproved.get(indexImproved.size() - 1));
          System.out.println(value);
          value.insert((int)indexImproved.get(indexImproved.size() - 2) - valueOneChar.length , resultChar, 0, resultChar.length);
          System.out.println(value);
          indexImproved.remove(indexImproved.size() - 1);
          indexImproved.remove(indexImproved.size() - 1);
          i  = indexImproved.get(indexImproved.size() - 1) + 1;
      }
      else if(indexImproved.size() > 2 && valid == 0){
        char[] valueOneChar = new char[(int)indexImproved.get(indexImproved.size() - 1) - (int)indexImproved.get(indexImproved.size() - 2) - 1];
          value.getChars((int)indexImproved.get(indexImproved.size() - 2) + 1 , (int)indexImproved.get(indexImproved.size() - 1) , valueOneChar, 0);
          int valueOneint = charArrayToInt(valueOneChar, 0, valueOneChar.length);
          char[] valueTwoChar = new char[value.length() - (int)indexImproved.get(indexImproved.size() - 1) - 1];
          value.getChars((int)indexImproved.get(indexImproved.size() - 1) + 1 , value.length(), valueTwoChar, 0);
          int valueTwoInt = charArrayToInt(valueTwoChar, 0, valueTwoChar.length);
          int resultInt = valueOneint / valueTwoInt;
          char[] resultChar = ("" + resultInt).toCharArray();
          value.delete((int)indexImproved.get(indexImproved.size() - 2) + 1, value.length());
          System.out.println(value);
          value.insert((int)indexImproved.get(indexImproved.size() - 1) - valueOneChar.length , resultChar, 0, resultChar.length);
          System.out.println(value);
          indexImproved.remove(indexImproved.size() - 1);
      }
      else if(indexImproved.size() == 2  && valid == 1){ // Då har vi något som är i början och har mer saker efter.
        char[] valueOneChar = new char[(int)indexImproved.get(indexImproved.size() - 2)];
        value.getChars(0, (int)indexImproved.get(indexImproved.size() - 2), valueOneChar, 0);
        int valueOneint = charArrayToInt(valueOneChar, 0, valueOneChar.length);
        char[] valueTwoChar = new char[(int)indexImproved.get(indexImproved.size() - 1) - (int)indexImproved.get(indexImproved.size() - 2) - 1];
        value.getChars((int)indexImproved.get(indexImproved.size() - 2) + 1 , (int)indexImproved.get(indexImproved.size() - 1), valueTwoChar, 0);
        int valueTwoInt = charArrayToInt(valueTwoChar, 0, valueTwoChar.length);
        int resultInt = valueOneint / valueTwoInt;
        char[] resultChar = ("" + resultInt).toCharArray();
        value.delete(0, (int)indexImproved.get(indexImproved.size() - 1));
        System.out.println(value);
        value.insert(0 , resultChar, 0, resultChar.length);
        System.out.println(value);
        indexImproved.remove(indexImproved.size() - 1);
        indexImproved.remove(indexImproved.size() - 1);
        i = 0;
    }
    else if(indexImproved.size() == 2  && valid == 0){ // en sträng med endast 2 tecken och multi på sista positionen dvs slutet behanlda som speciall fall.
      char[] valueOneChar = new char[(int)indexImproved.get(indexImproved.size() - 1) - (int)indexImproved.get(indexImproved.size() - 2) - 1];
      value.getChars((int)indexImproved.get(indexImproved.size() - 2) + 1, (int)indexImproved.get(indexImproved.size() - 1) , valueOneChar, 0);
      int valueOneint = charArrayToInt(valueOneChar, 0, valueOneChar.length);
      char[] valueTwoChar = new char[value.length() -  (int)indexImproved.get(indexImproved.size() -1)  - 1];
      value.getChars( (int)indexImproved.get(indexImproved.size() -1) + 1, value.length(), valueTwoChar, 0);
      int valueTwoInt = charArrayToInt(valueTwoChar, 0, valueTwoChar.length);
      int resultInt = valueOneint / valueTwoInt;
      char[] resultChar = ("" + resultInt).toCharArray();
      value.delete((int)indexImproved.get(indexImproved.size() - 2) + 1 , value.length());
      System.out.println(value);
      value.insert((int)indexImproved.get(indexImproved.size() - 2) + 1 , resultChar, 0, resultChar.length);
      System.out.println(value);
      indexImproved.remove(indexImproved.size() - 1);
    } //slutet av en sträng ligger multi
    else if(indexImproved.size() == 1){
      char[] valueOneChar = new char[(int)indexImproved.get(indexImproved.size() - 1)];
      value.getChars(0, (int)indexImproved.get(indexImproved.size() - 1), valueOneChar, 0);
      int valueOneint = charArrayToInt(valueOneChar, 0, valueOneChar.length);
      char[] valueTwoChar = new char[value.length() -  (int)indexImproved.get(indexImproved.size() -1)  - 1];
      value.getChars( (int)indexImproved.get(indexImproved.size() -1) + 1, value.length(), valueTwoChar, 0);
      int valueTwoInt = charArrayToInt(valueTwoChar, 0, valueTwoChar.length);
      int resultInt = valueOneint / valueTwoInt;
      char[] resultChar = ("" + resultInt).toCharArray();
      value.delete(0, value.length());
      System.out.println(value);
      value.insert(0 , resultChar, 0, resultChar.length);
      System.out.println(value);
    }
  }
}
for(int j = 0; j < indexImproved.size(); j--){
  if(indexImproved.size() == 1){

  }
  else if(indexImproved.size() > 1 ){
    char[] valueOneChar = new char[(int)indexImproved.get(indexImproved.size() - 1) - (int)indexImproved.get(indexImproved.size() - 2) - 1];
    value.getChars((int)indexImproved.get(indexImproved.size() - 2) + 1, (int)indexImproved.get(indexImproved.size() - 1) , valueOneChar, 0);
    int valueOneint = charArrayToInt(valueOneChar, 0, valueOneChar.length);
    char[] valueTwoChar = new char[value.length() -  (int)indexImproved.get(indexImproved.size() -1)  - 1];
    value.getChars( (int)indexImproved.get(indexImproved.size() -1) + 1, value.length(), valueTwoChar, 0);
    int valueTwoInt = charArrayToInt(valueTwoChar, 0, valueTwoChar.length);
    int resultInt;
    System.out.println(value.charAt(indexImproved.get(indexImproved.size() - 1)));
    if(value.charAt(indexImproved.get(indexImproved.size() - 1)) == '-'){
      resultInt = valueOneint - valueTwoInt;
    }
    else{
      resultInt = valueOneint + valueTwoInt;
    }
    char[] resultChar = ("" + resultInt).toCharArray();
    value.delete((int)indexImproved.get(indexImproved.size() - 2) + 1, value.length());
    System.out.println(value);
    value.insert((int)indexImproved.get(indexImproved.size() - 1) - valueOneChar.length , resultChar, 0, resultChar.length);
    System.out.println(value);
    indexImproved.remove(indexImproved.size() - 1);

  }
}
//now we have the index for all the addition and this should be a breeze
    char[] newArray = new char[value.length()];
    value.getChars(0, value.length(), newArray, 0); 
    return newArray;
  }
 
}
