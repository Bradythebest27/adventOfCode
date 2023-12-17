import java.io.*;
import java.util.*;
public class advent15 {
    public static void main(String[] args) throws Exception {
        int total=0;
        File file = new File("C:\\Users\\18438\\Desktop\\adventDay15.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        int counter=0;
        String all="";
        while ((s=br.readLine()) != null){
            //use string s as input line
            all=all+s;
            counter++;
        }
        int tempTotal=0;
        for (int i=0; i<all.length(); i++){
            if(all.charAt(i)!='\n'&&all.charAt(i)!=','){
                tempTotal=hash(all.charAt(i),tempTotal);
        
            } else if (all.charAt(i)==','){
                //System.out.println(tempTotal);
                total+=tempTotal;
                tempTotal=0;
            }
        }
        System.out.println(total);
    }
    
    public static int hash(char a,int total){
        int tempTotal=total+a;
        tempTotal*=17;
        return tempTotal%256;
    }
}
