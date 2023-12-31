
/**
 * Write a description of class advent2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;
public class advent2
{
    public static void main(String[] args) throws Exception{
        File file = new File("C:\\Users\\18438\\Desktop\\adventDay2.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        int total=0;
        //int counter=1;
        while ((s = br.readLine()) != null){
            s=(s.substring(s.indexOf(":")+2))+"; ";
            int [] count={0,0,0};
            if(recur(s,count)){
                System.out.println(count[0]*count[1]*count[2]);
                total+=count[0]*count[1]*count[2];
                //total+=counter
            }
            //System.out.println(counter+" "+total);
            //counter++;
        }
        System.out.println(total);
    }
    
    public static boolean recur(String s, int[] count){
         if (s.length()==0){
                return true;
         }
         int val=s.indexOf(";");
         if (val!=-1){
             String temp=s.substring(0,val);
             //System.out.println(temp+" "+temp.length());
             boolean pos=possible(temp,count);
             return pos&&recur(s.substring(val+2),count);
         } else{
             return true;  
         }
         
         
         //return true;
    }
    
    public static boolean possible(String s, int[] count){
        int first=Integer.parseInt(s.substring(0,s.indexOf(" ")));
        String color=" ";
        if (s.indexOf(",")!=-1){
            color=s.substring(s.indexOf(" ")+1,s.indexOf(","));
        } else{
            color=s.substring(s.indexOf(" ")+1);    
        }
        boolean same=true;
        if (color.equals("blue")){
            if (first>count[0]){
                count[0]=first;
            }
        }
        if (color.equals("red")){
            if (first>count[1]){
                count[1]=first;
            }
        }
        if (color.equals("green")){
            if (first>count[2]){
                count[2]=first;
            }
        }
        /**
         * if (color.equals("blue")){
         *      if (first>14){
         *          return false;
         *      }
         *  }
         *  if (color.equals("red")){
         *      if (first>12){
         *          return false;
         *      }
         *  }
         *  if (color.equals("green")){
         *     if (first>13){
         *         return false;
         *     }
         *  }
         */
        
        if (s.indexOf(",")!=-1){
            return same&&possible(s.substring(s.indexOf(",")+2),count);
        } else{
            return same;
        }
    
    
    }
}
