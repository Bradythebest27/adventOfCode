import java.io.*;
import java.util.*;
public class advent8 {
    public static void main(String[] args) throws Exception {
        int total=0;
        File file = new File("C:\\Users\\18438\\Desktop\\advent8.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        int counter=0;
        String direction="";
        ArrayList<String> nums=new ArrayList<>();
        ArrayList<String> left=new ArrayList<>();
        ArrayList<String> right=new ArrayList<>();
        direction=br.readLine(); br.readLine();
        while ((s=br.readLine()) != null){
            //use string s as input line
            nums.add(s.substring(0,3));
            left.add(s.substring(7,10));
            right.add(s.substring(12,15));
            counter++;
        }
        int wordOn=nums.indexOf("AAA");
        int counter2=0;
        boolean truth=true;
        for (int i=0; truth; i++){
            i=i%direction.length();
            if (direction.charAt(i)=='R'){
                wordOn=nums.indexOf(right.get(wordOn));  
            } else{
                wordOn=nums.indexOf(left.get(wordOn));  
            }
            counter2++;
            if (nums.get(wordOn).equals("ZZZ")){
                System.out.println(counter2);
                truth=false;
            }
        }
        
        System.out.println(nums.get(wordOn));
    }
}
