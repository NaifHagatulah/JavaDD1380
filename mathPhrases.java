import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.RunElement;

public class mathPhrases {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    StringBuilder phraseMut = new StringBuilder(input.nextLine().replace(" ", ""));
    int result = calculate(phraseMut);
    System.out.println(result);
  }

  public static int[] findIndexOfParenthesis(StringBuilder phrase) {
    int[] index = new int[2];
    for (int i = 0; i < phrase.length(); i++) {
      char ph = phrase.charAt(i);
      if (phrase.charAt(i) == 40)
        index[0] = i + 1; 
      if (phrase.charAt(i) == 41) {
        index[1] = i; 
        return index;
      }
    }
    return index;
  }

  public static List<Integer> findIndexOfOperation(char[] subPhrase) {
    List<Integer> index = new ArrayList();

    for (int i = 0; i < subPhrase.length; i++) {
      if (subPhrase[i] == 43 || subPhrase[i] == 42 || subPhrase[i] == 45 || subPhrase[i] == 47) {
        index.add(i);
      }
    }
    return index;
  }

  public static int calculate(StringBuilder phrase) {
    int[] index = findIndexOfParenthesis(phrase);
    if(index[1] != 0 && index[0] != 0){
    char[] innerPhrase = new char[index[1] - index[0]];
    phrase.getChars(index[0], index[1], innerPhrase, 0); 
    List<Integer> indexOfOperations = findIndexOfOperation(innerPhrase); // index of the operations for the subphrase. 
    char[] result = doArthmetic(innerPhrase, indexOfOperations);
    phrase.delete(index[0] - 1, index[1] + 1);
    if(phrase.length() != 0){
        phrase.insert(index[0] - 1, result, 0, result.length);
        calculate(phrase);
    }
}
    char[] fullPhrase = new char[phrase.length() ];
    phrase.getChars(0, phrase.length(), fullPhrase, 0);
    List<Integer> indexOfOperations = findIndexOfOperation(fullPhrase);
    char[] result = doArthmetic(fullPhrase, indexOfOperations);
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

  public static char[] doArthmetic(char[] mathPhrase, List index){
    StringBuilder value = new StringBuilder();
        value.append(mathPhrase);
    for(int i = 0; i < index.size(); i++){
        if(value.charAt((int)index.get(i)) == '*'){
           if(i != 0){
            char[] valueOneChar = new char[(int)index.get(i) - (int)index.get(i - 1) - 1];
            value.getChars((int)index.get(i - 1) + 1, (int)index.get(i), valueOneChar, 0);
            int valueOneint = charArrayToInt(valueOneChar, 0, valueOneChar.length);
            if(i != index.size() - 1){
                char[] valueTwoChar = new char[(int)index.get(i + 1) - (int)index.get(i) - 1];
                value.getChars((int)index.get(i) + 1 , (int)index.get(i + 1), valueTwoChar, 0);
                int valueTwoInt = charArrayToInt(valueTwoChar, 0, valueTwoChar.length);
                int resultInt = valueOneint * valueTwoInt;
                char[] resultChar = ("" + resultInt).toCharArray();
                value.delete((int)index.get(i - 1) + 1, (int)index.get(i +1  ));
                value.insert((int)index.get(i) - valueOneChar.length, resultChar, 0, resultChar.length);
                char[] newArray = new char[value.length()];
                value.getChars(0, value.length(), newArray, 0); 
                index = findIndexOfOperation(newArray);
            }
            else{
                char[] valueTwoChar = new char[value.length() - (int)index.get(i) - 1];
                value.getChars((int)index.get(i) + 1 , value.length(), valueTwoChar, 0);
                int valueTwoInt = charArrayToInt(valueTwoChar, 0, valueTwoChar.length);
                int resultInt = valueOneint * valueTwoInt;
                char[] resultChar = ("" + resultInt).toCharArray();
                value.delete((int)index.get(i - 1) + 1, value.length());
                value.insert((int)index.get(i) - valueOneChar.length, resultChar, 0, resultChar.length);
                char[] newArray = new char[value.length()];
                value.getChars(0, value.length(), newArray, 0); 
                index = findIndexOfOperation(newArray);
            }
           }
            else{
             char[] valueOneChar = new char[(int)index.get(i)];
             value.getChars(0, (int)index.get(i), valueOneChar, 0);
             int valueOneint = charArrayToInt(valueOneChar, 0, valueOneChar.length);
             if(i != index.size() - 1) {
                char[] valueTwoChar = new char[(int)index.get(i + 1) - (int)index.get(i) - 1];
                value.getChars((int)index.get(i) + 1 , (int)index.get(i + 1), valueTwoChar, 0);
                int valueTwoInt = charArrayToInt(valueTwoChar, 0, valueTwoChar.length);
                int resultInt = valueOneint * valueTwoInt;
                char[] resultChar = ("" + resultInt).toCharArray();
                value.delete(0, (int)index.get(i +1));
                value.insert((int)index.get(i) - valueOneChar.length, resultChar, 0, resultChar.length);
                char[] newArray = new char[value.length()];
                value.getChars(0, value.length(), newArray, 0); 
                index = findIndexOfOperation(newArray);
             }
             else{
                char[] valueTwoChar = new char[value.length() - (int)index.get(i) - 1];
                value.getChars((int)index.get(i) + 1 , value.length(), valueTwoChar, 0);
                int valueTwoInt = charArrayToInt(valueTwoChar, 0, valueTwoChar.length);
                int resultInt = valueOneint * valueTwoInt;
                char[] resultChar = ("" + resultInt).toCharArray();
                return resultChar;
                
             }
            } 
            i = -1; 
        }
        else if(value.charAt((int)index.get(i)) == '/'){
            if(i != 0){
             char[] valueOneChar = new char[(int)index.get(i) - (int)index.get(i - 1) - 1];
             value.getChars((int)index.get(i - 1) + 1, (int)index.get(i), valueOneChar, 0);
             int valueOneint = charArrayToInt(valueOneChar, 0, valueOneChar.length);
             if(i != index.size() - 1){
                 char[] valueTwoChar = new char[(int)index.get(i + 1) - (int)index.get(i) - 1];
                 value.getChars((int)index.get(i) + 1 , (int)index.get(i + 1), valueTwoChar, 0);
                 int valueTwoInt = charArrayToInt(valueTwoChar, 0, valueTwoChar.length);
                 int resultInt = valueOneint / valueTwoInt;
                 char[] resultChar = ("" + resultInt).toCharArray();
                 value.delete((int)index.get(i - 1) + 1, (int)index.get(i +1  ));
                 value.insert((int)index.get(i) - valueOneChar.length, resultChar, 0, resultChar.length);
                 char[] newArray = new char[value.length()];
                 value.getChars(0, value.length(), newArray, 0); 
                 index = findIndexOfOperation(newArray);
             }
             else{
                 char[] valueTwoChar = new char[value.length() - (int)index.get(i) - 1];
                 value.getChars((int)index.get(i) + 1 , value.length(), valueTwoChar, 0);
                 int valueTwoInt = charArrayToInt(valueTwoChar, 0, valueTwoChar.length);
                 int resultInt = valueOneint / valueTwoInt;
                 char[] resultChar = ("" + resultInt).toCharArray();
                 value.delete((int)index.get(i - 1) + 1, value.length());
                 value.insert((int)index.get(i) - valueOneChar.length, resultChar, 0, resultChar.length);
                 char[] newArray = new char[value.length()];
                 value.getChars(0, value.length(), newArray, 0); 
                 index = findIndexOfOperation(newArray);
             }
            }
             else{
              char[] valueOneChar = new char[(int)index.get(i)];
              value.getChars(0, (int)index.get(i), valueOneChar, 0);
              int valueOneint = charArrayToInt(valueOneChar, 0, valueOneChar.length);
              if(i != index.size() - 1) {
                 char[] valueTwoChar = new char[(int)index.get(i + 1) - (int)index.get(i) - 1];
                 value.getChars((int)index.get(i) + 1 , (int)index.get(i + 1), valueTwoChar, 0);
                 int valueTwoInt = charArrayToInt(valueTwoChar, 0, valueTwoChar.length);
                 int resultInt = valueOneint / valueTwoInt;
                 char[] resultChar = ("" + resultInt).toCharArray();
                 value.delete(0, (int)index.get(i +1));
                 value.insert((int)index.get(i) - valueOneChar.length, resultChar, 0, resultChar.length);
                 char[] newArray = new char[value.length()];
                 value.getChars(0, value.length(), newArray, 0); 
                 index = findIndexOfOperation(newArray);
              }
              else{
                 char[] valueTwoChar = new char[value.length() - (int)index.get(i) - 1];
                 value.getChars((int)index.get(i) + 1 , value.length(), valueTwoChar, 0);
                 int valueTwoInt = charArrayToInt(valueTwoChar, 0, valueTwoChar.length);
                 int resultInt = valueOneint / valueTwoInt;
                 char[] resultChar = ("" + resultInt).toCharArray();
                 return resultChar;
              }
             } 
             i = -1; 
         }
    }
    for(int i = 0; i < index.size(); i++){
        if(value.charAt((int)index.get(i)) == '+'){
            if(i != 0){
             char[] valueOneChar = new char[(int)index.get(i) - (int)index.get(i - 1) - 1];
             value.getChars((int)index.get(i - 1) + 1, (int)index.get(i), valueOneChar, 0);
             int valueOneint = charArrayToInt(valueOneChar, 0, valueOneChar.length);
             if(i != index.size() - 1){
                 char[] valueTwoChar = new char[(int)index.get(i + 1) - (int)index.get(i) - 1];
                 value.getChars((int)index.get(i) + 1 , (int)index.get(i + 1), valueTwoChar, 0);
                 int valueTwoInt = charArrayToInt(valueTwoChar, 0, valueTwoChar.length);
                 int resultInt = valueOneint + valueTwoInt;
                 char[] resultChar = ("" + resultInt).toCharArray();
                 value.delete((int)index.get(i - 1) + 1, (int)index.get(i +1  ));
                 value.insert((int)index.get(i) - valueOneChar.length, resultChar, 0, resultChar.length);
                 char[] newArray = new char[value.length()];
                 value.getChars(0, value.length(), newArray, 0); 
                 index = findIndexOfOperation(newArray);
             }
             else{
                 char[] valueTwoChar = new char[value.length() - (int)index.get(i) - 1];
                 value.getChars((int)index.get(i) + 1 , value.length(), valueTwoChar, 0);
                 int valueTwoInt = charArrayToInt(valueTwoChar, 0, valueTwoChar.length);
                 int resultInt = valueOneint + valueTwoInt;
                 char[] resultChar = ("" + resultInt).toCharArray();
                 value.delete((int)index.get(i - 1) + 1, value.length());
                 value.insert((int)index.get(i) - valueOneChar.length, resultChar, 0, resultChar.length);
                 char[] newArray = new char[value.length()];
                 value.getChars(0, value.length(), newArray, 0); 
                 index = findIndexOfOperation(newArray);
             }
            }
             else{
              char[] valueOneChar = new char[(int)index.get(i)];
              value.getChars(0, (int)index.get(i), valueOneChar, 0);
              int valueOneint = charArrayToInt(valueOneChar, 0, valueOneChar.length);
              if(i != index.size() - 1) {
                 char[] valueTwoChar = new char[(int)index.get(i + 1) - (int)index.get(i) - 1];
                 value.getChars((int)index.get(i) + 1 , (int)index.get(i + 1), valueTwoChar, 0);
                 int valueTwoInt = charArrayToInt(valueTwoChar, 0, valueTwoChar.length);
                 int resultInt = valueOneint + valueTwoInt;
                 char[] resultChar = ("" + resultInt).toCharArray();
                 value.delete(0, (int)index.get(i +1));
                 value.insert((int)index.get(i) - valueOneChar.length, resultChar, 0, resultChar.length);
                 char[] newArray = new char[value.length()];
                 value.getChars(0, value.length(), newArray, 0); 
                 index = findIndexOfOperation(newArray);
              }
              else{
                 char[] valueTwoChar = new char[value.length() - (int)index.get(i) - 1];
                 value.getChars((int)index.get(i) + 1 , value.length(), valueTwoChar, 0);
                 int valueTwoInt = charArrayToInt(valueTwoChar, 0, valueTwoChar.length);
                 int resultInt = valueOneint + valueTwoInt;
                 char[] resultChar = ("" + resultInt).toCharArray();
                 return resultChar;
                 
              }
             } 
             i = -1; 
         }
         else if(value.charAt((int)index.get(i)) == '-'){
            if(i != 0){
             char[] valueOneChar = new char[(int)index.get(i) - (int)index.get(i - 1) - 1];
             value.getChars((int)index.get(i - 1) + 1, (int)index.get(i), valueOneChar, 0);
             int valueOneint = charArrayToInt(valueOneChar, 0, valueOneChar.length);
             if(i != index.size() - 1){
                 char[] valueTwoChar = new char[(int)index.get(i + 1) - (int)index.get(i) - 1];
                 value.getChars((int)index.get(i) + 1 , (int)index.get(i + 1), valueTwoChar, 0);
                 int valueTwoInt = charArrayToInt(valueTwoChar, 0, valueTwoChar.length);
                 int resultInt = valueOneint - valueTwoInt;
                 char[] resultChar = ("" + resultInt).toCharArray();
                 value.delete((int)index.get(i - 1) + 1, (int)index.get(i +1  ));
                 value.insert((int)index.get(i) - valueOneChar.length, resultChar, 0, resultChar.length);
                 char[] newArray = new char[value.length()];
                 value.getChars(0, value.length(), newArray, 0); 
                 index = findIndexOfOperation(newArray);
             }
             else{
                 char[] valueTwoChar = new char[value.length() - (int)index.get(i) - 1];
                 value.getChars((int)index.get(i) + 1 , value.length(), valueTwoChar, 0);
                 int valueTwoInt = charArrayToInt(valueTwoChar, 0, valueTwoChar.length);
                 int resultInt = valueOneint - valueTwoInt;
                 char[] resultChar = ("" + resultInt).toCharArray();
                 value.delete((int)index.get(i - 1) + 1, value.length());
                 value.insert((int)index.get(i) - valueOneChar.length, resultChar, 0, resultChar.length);
                 char[] newArray = new char[value.length()];
                 value.getChars(0, value.length(), newArray, 0); 
                 index = findIndexOfOperation(newArray);
             }
            }
             else{
              char[] valueOneChar = new char[(int)index.get(i)];
              value.getChars(0, (int)index.get(i), valueOneChar, 0);
              int valueOneint = charArrayToInt(valueOneChar, 0, valueOneChar.length);
              if(i != index.size() - 1) {
                 char[] valueTwoChar = new char[(int)index.get(i + 1) - (int)index.get(i) - 1];
                 value.getChars((int)index.get(i) + 1 , (int)index.get(i + 1), valueTwoChar, 0);
                 int valueTwoInt = charArrayToInt(valueTwoChar, 0, valueTwoChar.length);
                 int resultInt = valueOneint - valueTwoInt;
                 char[] resultChar = ("" + resultInt).toCharArray();
                 value.delete(0, (int)index.get(i +1));
                 value.insert((int)index.get(i) - valueOneChar.length, resultChar, 0, resultChar.length);
                 char[] newArray = new char[value.length()];
                 value.getChars(0, value.length(), newArray, 0); 
                 index = findIndexOfOperation(newArray);
              }
              else{
                 char[] valueTwoChar = new char[value.length() - (int)index.get(i) - 1];
                 value.getChars((int)index.get(i) + 1 , value.length(), valueTwoChar, 0);
                 int valueTwoInt = charArrayToInt(valueTwoChar, 0, valueTwoChar.length);
                 int resultInt = valueOneint - valueTwoInt;
                 char[] resultChar = ("" + resultInt).toCharArray();
                 return resultChar;
              }
             } 
             i = -1; 
         }
    }

    return null;
  }
}