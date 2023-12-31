import java.io.*;
import java.util.*;
public class advent11pt2 {
    public static void main(String[] args) throws Exception {
        long total=0L;
        File file = new File("C:\\Users\\18438\\Desktop\\adventDay11.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        int counter=0;
        ArrayList<ArrayList<Integer>> nums=new ArrayList<>();
        while ((s=br.readLine()) != null){
            //use string s as input line
            nums.add(new ArrayList<Integer>());
            for (int i=0; i<s.length(); i++){
                if (s.charAt(i)=='.'){
                    nums.get(counter).add(0);
                } else{
                    nums.get(counter).add(1);
                }
            }
            counter++;
        }
        int ammount=0;
        for (int i=0; i<nums.get(0).size(); i++){
            for (int j=0; j<nums.size(); j++){
                if (nums.get(j).get(i)==1){
                    for (int j2=0; j2<nums.size(); j2++){
                        for (int i2=0; i2<nums.get(0).size(); i2++){
                            if (nums.get(j2).get(i2)==1){
                                if ((j2>j)||(j2==j&&i2>i)){
                                    long tempTotal=0L;
                                    if (i2>i){
                                        for (int p=i; p<i2; p++){
                                            if (isAEmptyCol(nums,p)){
                                                tempTotal+=1000000-1;
                                            }
                                        }
                                    } else{
                                        for (int p=i; p>i2; p--){
                                            if (isAEmptyCol(nums,p)){
                                                tempTotal+=1000000-1;
                                            }
                                        }
                                    }
                                    for (int q=j; q<j2; q++){
                                        if (isAEmptyRow(nums,q)){
                                            tempTotal+=1000000-1;
                                        }
                                    }
                                    total+=tempTotal+Math.abs(j2-j)+Math.abs(i2-i);
                                    ammount++;
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(ammount+" "+total);
    }
    public static boolean isAEmptyRow(ArrayList<ArrayList<Integer>> nums, int q){
        for (int i=0; i<nums.get(q).size(); i++){
            if (nums.get(q).get(i)!=0){
                return false;
            }
        }
        return true;
    }
    public static boolean isAEmptyCol(ArrayList<ArrayList<Integer>> nums, int q){
        for (int i=0; i<nums.size(); i++){
            if (nums.get(i).get(q)!=0){
                return false;
            }
        }
        return true;
    }
}
