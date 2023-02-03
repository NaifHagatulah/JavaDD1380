import java.util.List;
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader;

class Main {
  public static void main(String[] args) throws IOException{
    System.out.println("Enter a mathematical expression");
    //Enter data using BufferReader 
    while(true){
        BufferedReader reader =  
                   new BufferedReader(new InputStreamReader(System.in)); 

        // Reading data using readLine 
        String name = reader.readLine(); 

    Scanner sc = new Scanner(name);
    //(12*5)+1*(8.6-3*2^2)-34
    //(12*5)
    //12*5
    //12
    //-5+12*(-3+2)
    //-12
    //12
    //15/(3+2)

    List<ScannedToken> scanExp = sc.scan();
    Parser parser = new Parser(scanExp);
    List<ScannedToken> parsed = parser.parse();
    //scanExp.forEach(e->System.out.println(e));
    System.out.println(sc.evaluate(parsed));
    }
  }
}