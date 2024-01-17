import java.io.*;
import java.util.*;
public class advent23 {
    public static void main(String[] args) throws Exception {
        int total=1;
        File file = new File("C:\\Users\\18438\\Desktop\\adventDay23.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        int counter=0;
        final int size=141;//23
        char[][] nums=new char[size][size];
        while ((s=br.readLine()) != null){
            //use string s as input line
            for (int i=0; i<s.length(); i++){
                nums[counter][i]=s.charAt(i);
            }
            counter++;
        }
        nums[0][1]='#';
        //start is 1,1
        System.out.println(path(nums,new point(1,1),new point(0,1),1,size));
    }   
    public static int path(char[][] nums, point c, point p, int len, int size){
        
        if (nums[c.r][c.c]=='#'){
            return -99999;
        }
        if (nums[c.r][c.c]=='<'&&p.c<c.c){
            return -999999;
        }
        if (nums[c.r][c.c]=='>'&&p.c>c.c){
            return -999999;
        }
        if (nums[c.r][c.c]=='v'&&p.r>c.r){
            return -99999;
        }
        if (c.r==size-1&&c.c==size-2){
            return len;
        }
        point p1=new point(c.r+1,c.c);
        if (p1.r==p.r){
            p1.r=0;
            p1.c=0;
        }
        point p2=new point(c.r-1,c.c);
        if (p2.r==p.r){
            p2.r=0;
            p2.c=0;
        }
        point p3=new point(c.r,c.c+1);
        if (p3.c==p.c){
            p3.r=0;
            p3.c=0;
        }
        point p4=new point(c.r,c.c-1);
        if (p4.c==p.c){
            p4.r=0;
            p4.c=0;
        }
        return greaterOf(greaterOf(path(nums,p1,c,len+1,size),path(nums,p2,c,len+1,size)),greaterOf(path(nums,p3,c,len+1,size),path(nums,p4,c,len+1,size)));
    }
    public static int greaterOf(int a, int b){
        if (a>b){
            return a;
        }
        return b;
    }
    static class point{
        public int r;
        public int c;
        point(int r1,int c1){
            r=r1;
            c=c1;
        }
    }
}