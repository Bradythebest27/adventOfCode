import java.io.*;

public class advent3_pt2 {
    public static void main(String[] args) throws Exception {
        char[][] nums = new char[141][141];
        int total = 0;
        File file = new File("C:\\Users\\18438\\Desktop\\adventDay3.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        int counter = 0;
        while ((s = br.readLine()) != null){
            for (int i = 0; i < s.length(); i++){
                nums[counter][i] = s.charAt(i);
            }
            nums[counter][140]='.';
            counter++;
        }
        
        for (int i=0; i <141; i++){
            for (int j=0; j<141; j++){
                if (isAStar(nums[i][j])){
                    total+=isIsolated(nums,i,j);
                }
            }
        }
        System.out.println(total);
    }
    public static boolean isAStar(char n){
        return (n=='*');
    }
    public static boolean isANum(char n){
        return n >= '0' && n <= '9';
    }

    public static int isIsolated(char[][] n, int i, int j){
        int jlen = 141;
        int ilen = 141;
        int firstPosi=-1;
        int firstPosj=-1;
        
        for (int x=i-1; x<=i+1; x++){
            for (int y=j-1; y<=j+1; y++){
                if (x>=0&&y>=0&&x<ilen&&y<jlen&&!(x==i&&y==j)){
                    if (isANum(n[x][y])){
                        if (firstPosi!=-1){
                            int secondPosi=findFirstPosi(n,x,y);
                            int secondPosj=findFirstPosj(n,x,y);
                            if (secondPosi!=firstPosi||secondPosj!=firstPosj){
                                return getFullVal(n,secondPosi,secondPosj)*getFullVal(n,firstPosi,firstPosj);
                            }
                        } else{
                            firstPosi=findFirstPosi(n,x,y);
                            firstPosj=findFirstPosj(n,x,y);
                        }
                    }
                }
            }
        }
        return 0;
    }
    public static int findFirstPosi(char[][] n, int i, int j){
        return i;
    }
    public static int findFirstPosj(char[][] n, int i, int j1){
        int j=j1;
        while (j>=0&&isANum(n[i][j])){
            j--;
        }
        
        return j+1;
    }
    public static int getFullVal(char[][] n, int i, int j1) {
        int j=j1;
        String s = "";
        while (j < 142 && isANum(n[i][j])){
            s += n[i][j];
            j++;
        }
        if (s.length()==0){
            System.out.println(i+" "+j);
        }
        return Integer.parseInt(s);
    }

    public static int getFullValLen(char[][] n, int i, int j1){
        int j=j1;
        int count = 0;
        while (j<n[0].length && isANum(n[i][j])){
            j++;
            count++;
        }
        return count;
    }
}
