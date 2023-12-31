import java.io.*;
import java.util.*;
public class advent8pt2 {
    public static void main(String[] args) throws Exception {
        int total=0;
        File file = new File("C:\\Users\\18438\\Desktop\\advent8.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        String direction="  ";
        ArrayList<String> nums=new ArrayList<>();
        ArrayList<String> left=new ArrayList<>();
        ArrayList<String> right=new ArrayList<>();
        ArrayList<Integer> loopSize=new ArrayList<>();
        ArrayList<String> words=new ArrayList<>();
        direction=br.readLine(); br.readLine();
        while ((s=br.readLine()) != null){
            //use string s as input line
            nums.add(s.substring(0,3));
            left.add(s.substring(7,10));
            right.add(s.substring(12,15));
            if (s.substring(2,3).equals("A")){
                words.add(s.substring(0,3));
            }
        }
        System.out.println(words);
        for (int i=0; i<words.size(); i++){
            String temp=words.get(i);
            int countOfKey=0;
            int counter2=0;
            while (!allEndsInZ(temp)){
                countOfKey=countOfKey%direction.length();
                if (direction.charAt(countOfKey)=='L'){
                    temp=left.get(nums.indexOf(temp));
                } else{
                    temp=right.get(nums.indexOf(temp));  
                }
                counter2=counter2+1;
                countOfKey++;
            }
            System.out.print(counter2+" ");
            loopSize.add(counter2);
        }
        System.out.println(loopSize);
        System.out.println(lcm_of_array(loopSize));
    }
    
    public static boolean allEndsInZ(String s){
        return (s.charAt(2)=='Z');
    }
    
    public static long gcd(long num1, long num2){
        if (num2 == 0){
            return num1;
        }
        return gcd(num2, num1%num2);
    }
 
    public static long lcm_of_array(ArrayList<Integer> arr){
        long lcm=arr.get(0);
        for (int i=1; i<arr.size(); i++) {
            long num1=lcm;
            long num2=arr.get(i);
            long gcd_val=gcd(num1, num2);
            lcm=(lcm*arr.get(i))/gcd_val;
        }
        return lcm;
    }
}
