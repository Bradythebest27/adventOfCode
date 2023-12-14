import java.io.*;
import java.util.*;
public class advent14pt2 {
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
        for (long i2=0; i2<1050; i2++){
            up(nums);
            left(nums);
            down(nums);
            right(nums);
            if (i2<20||i2>1000-20){
                int tempTotal=0;
                for (int i=0; i<nums.length; i++){
                    for (int j=0; j<nums[0].length; j++){
                        if (nums[i][j]=='O'){
                            tempTotal+=nums.length-i; //(size of loop=42 from looking at data)1/42(1000000000-1007(i just picjed 1007))==23809499.8333
                            //(as int so not overflow)23809499*42+1007=999999965 (so check 35 after 1007)
                            //1007+35==1042 so check 1041's index
                        }
                    }
                }
                System.out.println(tempTotal+" "+i2);
            }
        }
        for (int i=0; i<nums.length; i++){
            for (int j=0; j<nums[0].length; j++){
                System.out.print(nums[i][j]+" ");
            }
            System.out.println();
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
    public static void up(char[][] nums){
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
    }
    public static void left(char[][] nums){
        for (int j=1; j<nums[0].length; j++){
            for (int i=0; i<nums[0].length; i++){
                if (nums[i][j]=='O'){
                    int up=j-1;
                    if (nums[i][up]=='.'){
                        up--;
                        while (up>=0&&nums[i][up]=='.'){
                            up--;
                        }
                        nums[i][j]='.';
                        nums[i][up+1]='O';
                    }
                }
            }
        }
    }
    public static void down(char[][] nums){
        for (int i=nums.length-2; i>=0; i--){
            for (int j=0; j<nums[0].length; j++){
                if (nums[i][j]=='O'){
                    int up=i+1;
                    if (nums[up][j]=='.'){
                        up++;
                        while (up<nums.length&&nums[up][j]=='.'){
                            up++;
                        }
                        nums[i][j]='.';
                        nums[up-1][j]='O';
                    }
                }
            }
        }
    }
    public static void right(char[][] nums){
        for (int j=nums[0].length-2; j>=0; j--){
            for (int i=0; i<nums.length; i++){
                if (nums[i][j]=='O'){
                    int up=j+1;
                    if (nums[i][up]=='.'){
                        up++;
                        while (up<nums[0].length&&nums[i][up]=='.'){
                            up++;
                        }
                        nums[i][j]='.';
                        nums[i][up-1]='O';
                    }
                }
            }
        }
    }
}
