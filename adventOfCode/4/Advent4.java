import java.io.*;
import java.util.*;

public class Advent4 {
    public static void main(String[] args) throws Exception {
        ArrayList<String> nums = new ArrayList<>();
        long total = 0;
        File file = new File("C:\\Users\\18438\\Desktop\\temp.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        ArrayList<Integer> amountOfCards=new ArrayList<>();
        while ((s=br.readLine())!=null) {
            if (!s.isEmpty()){
                nums.add(s);
                amountOfCards.add(1);
            }
        }

        for (int i=0; i<nums.size();i++){
            String curr=nums.get(i);
            curr=curr.substring(10).trim();
            ArrayList<Integer> first=split(curr.substring(0, curr.indexOf("|")));
            ArrayList<Integer> second=split(curr.substring(curr.indexOf("|")+1));
            int amount=0;
            for (Integer find: first){
                for (Integer inThis: second){
                    if (find.equals(inThis)){
                        amount++;
                    }
                }
            }
            if(amount>0){
                for (int j=i+1; j<i+1+amount; j++){
                    amountOfCards.set(j,amountOfCards.get(j)+amountOfCards.get(i));
                }
            }
        }
        for (Integer curr: amountOfCards){
            total+=curr;
        }
        System.out.println(total);
    }

    public static ArrayList<Integer> split(String s){
        int counter=0;
        ArrayList<Integer> returner = new ArrayList<>();

        while(counter<s.length()){
            while(counter<s.length()&&s.charAt(counter)== ' '){
                counter++;
            }
            int x=0;
            while(counter<s.length()&&Character.isDigit(s.charAt(counter))){
                x*=10;
                x+=s.charAt(counter)-'0';
                counter++;
            }
            if(x != 0){
                returner.add(x);
            }
            counter++;
        }
        return returner;
    }
}
