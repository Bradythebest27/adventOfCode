import java.io.*;
import java.util.*;
public class advent11 {
    public static void main(String[] args) throws Exception {
        int total=0;
        File file = new File("C:\\Users\\18438\\Desktop\\adventDay11.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        int counter=0;
        ArrayList<ArrayList<Integer>> nums=new ArrayList<>();
        while ((s=br.readLine()) != null){
            //use string s as input line
            nums.add(new ArrayList<Integer>());
            boolean allZeroRow=true;
            for (int i=0; i<s.length(); i++){
                if (s.charAt(i)!='.'){
                    allZeroRow=false;
                }
            }
            if (!allZeroRow)
                for (int i=0; i<s.length(); i++){
                    if (s.charAt(i)=='.'){
                        nums.get(counter).add(0);
                    } else{
                        nums.get(counter).add(1);
                    }
                }
            else{
                for (int i=0; i<s.length(); i++){
                    nums.get(counter).add(0);
                }
                counter++;
                nums.add(new ArrayList<Integer>());
                for (int i=0; i<s.length(); i++){
                    nums.get(counter).add(0);
                }
            }
            counter++;
        }
        for (int i=0; i<nums.get(0).size(); i++){
            boolean isAllZero=true;
            for (int row=0; row<nums.size(); row++){
                if (nums.get(row).get(i)!=0){
                    isAllZero=false;
                }
            }
            if (isAllZero){
                for (int row2=0; row2<nums.size(); row2++){
                    nums.get(row2).add(i,0);
                }
                i++;
            }   
        }
        for (int i=0; i<nums.get(0).size(); i++){
            for (int j=0; j<nums.size(); j++){
                if (nums.get(j).get(i)==1){
                    for (int j2=0; j2<nums.size(); j2++){
                        for (int i2=0; i2<nums.get(0).size(); i2++){
                            if (nums.get(j2).get(i2)==1){
                                if ((j2>j)||(j2==j&&i2>i)){
                                    total+=Math.abs(j2-j)+Math.abs(i2-i);
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(total);
    }
    
}
