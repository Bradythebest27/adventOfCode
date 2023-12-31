import java.io.*;
import java.util.*;
public class advent14 {
    public static void main(String[] args) throws Exception {
        int total=0;
        File file = new File("C:\\Users\\18438\\Desktop\\adventDay14.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        int counter=0;
        char[][] nums=new char[100][100];
        while ((s=br.readLine()) != null){
            //use string s as input line
            for (int i=0; i<s.length(); i++){
                nums[counter][i]=s.charAt(i);
            }
            counter++;
        }
        for (int i=1; i<nums.length; i++){
            for (int j=0; j<nums[0].length; j++){
                if (nums[i][j]=='O'){
                    int up=i-1;
                    if (nums[up][j]=='.'){
                        up--;
                        while (up>=0&&nums[up][j]=='.'){
                            up--;
                        }
                        nums[i][j]='.';
                        nums[up+1][j]='O';
                    }
                }
            }
        }
    
        for (int i=0; i<nums.length; i++){
            for (int j=0; j<nums[0].length; j++){
                if (nums[i][j]=='O'){
                    total+=nums.length-i;
                }
            }
        }
        System.out.println(total);
    }
}
