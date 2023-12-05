import java.io.*;
import java.util.*;
public class advent5
{
    public static class triplet{
        private long val1;
        private long val2;
        private long val3;
        public triplet(long n1, long n2, long n3){
            val1=n1;
            val2=n2;
            val3=n3;
        }
        public long getOne(){
            return val1;
        }
        public long getTwo(){
            return val2;
        }
        public long getThree(){
            return val3;
        }
    }
    public static void main(String[] args) throws Exception {
        ArrayList<String> nums = new ArrayList<>();
        long total = 0;
        File file = new File("C:\\Users\\18438\\Desktop\\temp.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        br.readLine();br.readLine();
        //long[] findVals={79L, 14L, 55L, 13L};
         long[] findVals={3640772818L, 104094365L, 1236480411L, 161072229L, 376099792L, 370219099L, 1590268366L, 273715765L, 3224333694L, 68979978L, 2070154278L,
            189826014L,3855332650L, 230434913L, 3033760782L, 82305885L, 837883389L, 177854788L, 2442602612L, 571881366L};
        String s;
        int arraySize=8;
        ArrayList<triplet>[] matrix = new ArrayList[arraySize];
        for (int i=0; i<arraySize; i++){
            matrix[i] = new ArrayList<>();
        }
        int count=0;
        while ((s=br.readLine())!=null){
            if (!s.isEmpty()){
                //System.out.println(s);
                if (s.indexOf(":")!=-1){
                    count++;
                } else{
                    long l1=Long.parseLong(s.substring(0,s.indexOf(" ")));
                    s=s.substring(s.indexOf(" ")+1);
                    long l2=Long.parseLong(s.substring(0,s.indexOf(" ")));
                    s=s.substring(s.indexOf(" ")+1);
                    long l3=Long.parseLong(s);
                    matrix[count].add(new triplet(l1,l2,l3));
                }
            }
        }
        long min=findMapped(findVals[0],matrix);
        for (int i=0; i<findVals.length; i+=2){
            for (long j=findVals[i]; j<findVals[i]+findVals[i+1]; j++){
                long temp5=findMapped(j,matrix);
                if (temp5<min){
                    min=temp5;
                }
            }
            System.out.println(min);
        }
        System.out.println(min);
    }
    public static long findMapped(long l, ArrayList<triplet>[] matrix){
        long currVal=l;
        for (int count=0; count<matrix.length;count++){
            for (triplet curr: matrix[count]){
                //System.out.println("\t\t one:"+curr.getOne()+" two: "+curr.getTwo()+" three: "+curr.getThree());
                if (currVal>=curr.getTwo()&&currVal<curr.getTwo()+curr.getThree()){
                    currVal=currVal-curr.getTwo()+curr.getOne();
                    break;
                }
            }
            //System.out.println("\t"+currVal);
        }
        return currVal;
    }
}
