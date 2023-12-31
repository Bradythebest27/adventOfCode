import java.io.*;
import java.util.*;
public class advent7 {
    public static void main(String[] args) throws Exception {
        int total=0;
        File file = new File("C:\\Users\\18438\\Desktop\\adventDay7.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        int counter=0;
        ArrayList<Integer> bid=new ArrayList<>();
        ArrayList<String> card=new ArrayList<>();
        ArrayList<Double> val=new ArrayList<>();
        ArrayList<Integer> rank=new ArrayList<>();
        while ((s=br.readLine()) != null){
            //use string s as input line
            card.add(s.substring(0,5));
            bid.add(Integer.parseInt(s.substring(6)));
            counter++;
            val.add(0.0);
        }
        for (int i=0; i<card.size(); i++){
            val.set(i,getVal(card.get(i)));
        }
        ArrayList<Double> list=new ArrayList<>();
        for (Double curr: val){
            list.add(curr);
        }
        Collections.sort(list);
        
        for (int i=0; i<val.size(); i++){
            rank.add(1+find(list,val.get(i)));
        }
        //System.out.println(rank);
        for (int i=0; i<val.size(); i++){
            total+=rank.get(i)*bid.get(i);
        }
        System.out.println(total);
    }
    public static int find(ArrayList<Double> n, double val){
        for (int i=0; i<n.size(); i++){
            if (n.get(i).equals(val)){
                return i;
            }
        }
        System.out.println("error");
        return -999999;
    }
    /**
     * 6=five of kind
     * 5=four of kind   
     * 4=full house
     *  3=3 of a kind
     *  2=2 pair
     *  1=pair
     *  0=high card
     * .A    K   Q   J   T   9   8   7   6   5   4    3    2
     *  .99 .98 .97 .96 .95 .94 .93 .92 .91 .90 .89  .88 .87
     */
    public static double getVal(String s){
        double n=0;
        if (is6(s)){
            n+=6;
        } else if (is5(s)){
            n+=5;
        } else if (is4(s)){
            n+=4;
        } else if (is3(s)){
            n+=3;
        } else if (is2(s)){
            n+=2;
        } else if (is1(s)){
            n+=1;
        } else{
            
        }
        for (int i=0; i<s.length(); i++){
            n+=Math.pow(10,-2*(i+1))*(100-charToInt(s.charAt(i)));
        }
        return n;
    }
    public static boolean is6(String s){
        //System.out.println(s);
        int[] amount=new int[13];
        for (int i=0; i<s.length(); i++){
            //System.out.println(i+" "+s.charAt(i));
            amount[-1+charToInt(s.charAt(i))]++;
        }
        for (int curr: amount){
            if (curr==5){
                return true;
            }
        }
        return false;
    }
     public static boolean is5(String s){
        int[] amount=new int[13];
        for (int i=0; i<s.length(); i++){
            amount[-1+charToInt(s.charAt(i))]++;
        }
        for (int curr: amount){
            if (curr==4){
                return true;
            }
        }
        return false;
    }
     public static boolean is4(String s){
        int[] amount=new int[13];
        for (int i=0; i<s.length(); i++){
            amount[-1+charToInt(s.charAt(i))]++;
        }
        int count3=0;
        int count2=0;
        for (int curr: amount){
            if (curr==2){
                count2++;
            }
            if (curr==3){
                count3++;
            }
        }
        return count3>0&&count2>0;
    }
     public static boolean is3(String s){
        int[] amount=new int[13];
        for (int i=0; i<s.length(); i++){
            amount[-1+charToInt(s.charAt(i))]++;
        }
        for (int curr: amount){
            if (curr==3){
                return true;
            }
        }
        return false;
    }
     public static boolean is2(String s){
        int[] amount=new int[13];
        for (int i=0; i<s.length(); i++){
            amount[-1+charToInt(s.charAt(i))]++;
        }
        int count=0;
        for (int curr: amount){
            if (curr==2){
                count++;
            }
        }
        return count==2;
    }
     public static boolean is1(String s){
        int[] amount=new int[13];
        for (int i=0; i<s.length(); i++){
            amount[-1+charToInt(s.charAt(i))]++;
        }
        for (int curr: amount){
            if (curr==2){
                return true;
            }
        }
        return false;
    }
    public static int charToInt(char s){
        if (s=='A'){
            return 1;
        }
        if (s=='K'){
            return 2;
        }
        if (s=='Q'){
            return 3;
        }
        if (s=='J'){
            return 4;
        }
        if (s=='T'){
            return 5;
        }
        return 15-(s-'0');
    }
}
