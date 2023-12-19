import java.io.*;
import java.util.*;
public class advent18pt2 {
    public static void main(String[] args) throws Exception {
        int total=0;
        File file = new File("C:\\Users\\18438\\Desktop\\adventDay18.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        int counter=0;
        ArrayList<move> nums=new ArrayList<>();
        ArrayList<point> polygon=new ArrayList<>();
        while ((s=br.readLine()) != null){
            char d=' ';
            switch(s.charAt(s.indexOf(")")-1)){
                case '0':
                    d='R';
                    break;
                case '1':
                    d='D';
                    break;
                case '2':
                    d='L';
                    break;
                case '3':
                    d='U';
                    break;
                default:
                    System.out.println("error char is "+s.charAt(s.indexOf(")")-1));
                    return;
            }
            nums.add(new move(d,getDecimal(s.substring(s.indexOf("#")+1,s.indexOf(")")-1))));
            counter++;
        }
        long r=0;
        long c=0;
        int dr=0;
        int dc=0;
        polygon.add(new point(0,0));
        int exteriorPts=1;
        for (int i=0; i<nums.size(); i++){
            switch (nums.get(i).dir){
                case 'U':
                    dr=-1;
                    dc=0;
                    break;
                case 'D':
                    dr=1;
                    dc=0;
                    break;
                case 'R':
                    dc=1;
                    dr=0;
                    break;
                case 'L':
                    dc=-1;
                    dr=0;
                    break;
                default:
                    System.out.println("Error"+nums.get(i).dir);
            }
            exteriorPts+=nums.get(i).amount;
            r+=dr*nums.get(i).amount;
            c+=dc*nums.get(i).amount;
            polygon.add(new point(r,c));
            
        }
        exteriorPts+=Math.abs(polygon.get(polygon.size()-1).row+polygon.get(polygon.size()-1).col)-1;
        polygon.add(new point(0,0));
        double sum = 0;
        for(int i = 0; i < polygon.size() - 1; i++)
            if(i == 0)
                sum += polygon.get(i).row * (polygon.get(i+1).col - polygon.get(polygon.size() - 1).col);
            else
                sum += polygon.get(i).row * (polygon.get(i+1).col - polygon.get(i-1).col);

        double area = 0.5 * Math.abs(sum);
        double out=(area+1-(exteriorPts*0.5)+exteriorPts);
        System.out.printf("%f\n", out);
        //area+1-(exteriorPts/2)+exteriorPts
    }
    public static int getDecimal(String hex){  
        String digits="0123456789ABCDEF";  
        hex=hex.toUpperCase();  
        int val=0;  
        for (int i=0; i<hex.length(); i++){  
            char c=hex.charAt(i);  
            int d=digits.indexOf(c);  
            val=16*val+d;  
        }  
        return val;  
    }  
    static class point{
        public long row;
        public long col;
        public point(long r, long c){
            row=r;
            col=c;
        }
    }
    static class move{
        public char dir;
        public int amount;
        public move(char d, int a){
            dir=d;
            amount=a;
        }
    }
}
