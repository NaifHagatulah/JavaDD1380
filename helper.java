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
    List<Integer> indexImproved = new ArrayList();
    for(int i = 0; i < value.length(); i++){
        if (value.charAt(i) == '+' || value.charAt(i) == '-' || value.charAt(i) == '*' || value.charAt(i) == '/') {
            indexImproved.add(i);
          }
        if(value.charAt(i) == '*'){
                for(int j = i + 1; j < value.length(); j++){
                    if (value.charAt(j) == '+' || value.charAt(j) == '-' || value.charAt(j) == '*' || value.charAt(j) == '/') {
                        indexImproved.add(j);
                        j = value.length();
                }
            }
            if(indexImproved.size() > 2){
                char[] valueOneChar = new char[(int)indexImproved.get(indexImproved.size() - 2) - (int)indexImproved.get(indexImproved.size() - 3) - 1];
                value.getChars((int)indexImproved.get(indexImproved.size() - 3) + 1 , (int)indexImproved.get(indexImproved.size() - 2) , valueOneChar, 0);
                int valueOneint = charArrayToInt(valueOneChar, 0, valueOneChar.length);
                char[] valueTwoChar = new char[(int)indexImproved.get(indexImproved.size() - 1) - (int)indexImproved.get(indexImproved.size() - 2) - 1];
                value.getChars((int)indexImproved.get(indexImproved.size() - 2) + 1 , (int)indexImproved.get(indexImproved.size() - 1), valueTwoChar, 0);
                int valueTwoInt = charArrayToInt(valueTwoChar, 0, valueTwoChar.length);
                int resultInt = valueOneint * valueTwoInt;
                char[] resultChar = ("" + resultInt).toCharArray();
                value.delete((int)indexImproved.get(i - 3) + 1, (int)indexImproved.get(i - 1 ));
                System.out.println(value);
                value.insert((int)indexImproved.get(i- 2) - valueOneChar.length, resultChar, 0, resultChar.length);
                System.out.println(value);
                indexImproved.remove(i - 1);
                indexImproved.remove(i - 2);
                i  = indexImproved.get(i - 3) + 1;
            }
            else if(indexImproved.size() == 2){ // Då har vi något som är i början och har mer saker efter.
                char[] valueOneChar = new char[(int)indexImproved.get(i - 1)];
                value.getChars(0, (int)indexImproved.get(i - 1), valueOneChar, 0);
                int valueOneint = charArrayToInt(valueOneChar, 0, valueOneChar.length);
            }
        }
    }
    char[] newArray = new char[value.length()];
    value.getChars(0, value.length(), newArray, 0); 
    return newArray;
  }
 
}
