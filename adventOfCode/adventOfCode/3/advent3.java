import java.io.*;

public class advent3 {
    public static void main(String[] args) throws Exception {
        char[][] nums = new char[141][141];
        int total=0;
        File file = new File("C:\\Users\\18438\\Desktop\\adventDay3.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        int counter=0;
        while ((s=br.readLine()) != null){
            for (int i=0; i<s.length(); i++){
                nums[counter][i] = s.charAt(i);
            }
            nums[counter][140]='.';
            counter++;
        }
        for (int i=0; i<141; i++){
            for (int j=0; j<141; j++){
                if (isANum(nums[i][j])){
                    boolean temp=true;
                    for (int k=0; k<getFullValLen(nums, i, j); k++){
                        if (!isIsolated(nums, i, j + k)){
                            temp=false;
                        }
                    }
                    if (!temp){
                        total+=getFullVal(nums, i, j);
                        j+=getFullValLen(nums, i, j);
                    } else{
                        j+=getFullValLen(nums, i, j);
                    }
                }
            }
        }
        System.out.println(total);
    }

    public static boolean isANum(char n){
        return n >= '0' && n <= '9';
    }

    public static boolean isIsolated(char[][] n, int i, int j){
        int jlen=141;
        int ilen=141;

        // Checking neighboring cells
        for (int x=i-1; x<=i+1; x++){
            for (int y=j-1; y<=j+1; y++){
                if (x>=0 && y>=0 && x<jlen && y<ilen && !(x==i && y==j)){
                    if (n[x][y]!='.'&&!isANum(n[x][y])){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static int getFullVal(char[][] n, int i, int j1){
        int j=j1;
        String s="";
        while (j < 141 && isANum(n[i][j])){
            s+=n[i][j];
            j++;
        }
        return Integer.parseInt(s);
    }

    public static int getFullValLen(char[][] n, int i, int j1){
        int j=j1;
        int count=0;
        while (j < n[0].length && isANum(n[i][j])){
            j++;
            count++;
        }
        return count;
    }
}
