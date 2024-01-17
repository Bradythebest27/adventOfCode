import java.io.*;
import java.util.*;
public class trying {
    public static void main(String[] args) throws Exception {
        int total=0;
        File file = new File("C:\\Users\\18438\\Desktop\\adventDay12.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        int counter=0;
        ArrayList<String> nums=new ArrayList<>();
        ArrayList<ArrayList<Integer>> key=new ArrayList<>();
        while ((s=br.readLine()) != null){
            nums.add(s.substring(0,s.indexOf(" ")));
            ArrayList<Integer> tempKeys=new ArrayList<>();
            String tempS=(s.substring(1+s.indexOf(" ")));
            while(tempS.indexOf(",")!=-1){
                tempKeys.add(Integer.parseInt(tempS.substring(0,tempS.indexOf(","))));
                tempS=tempS.substring(1+tempS.indexOf(","));
            }
            tempKeys.add(Integer.parseInt(tempS));
            key.add(tempKeys);
            counter++;
        }
        System.out.println(nums);
        System.out.println(key);
        for (int i=0; i<nums.size(); i++){
            ArrayList<String> toTest=toTest(key.get(i),nums.get(i));
            for (int j=0; j<toTest.size(); j++){
                if (validString(nums.get(i),toTest.get(j))){
                    total++;
                }
            }
        }
        
        
        
        System.out.println(total);
    }
    
    public static boolean validString(String original, String test){
        for (int i=0; i<original.length(); i++){
            if (original.charAt(i)=='.'&&test.charAt(i)=='1'){
                return false;
            }
            if (original.charAt(i)=='#'&&test.charAt(i)=='0'){
                return false;
            }
        }
        return true;
    }
    static int countFreq(String pat, char txt){
        int count=0;
        for (int i=0; i<pat.length(); i++){
            if (pat.charAt(i)==txt){
                count++;
            }
        }
        return count;
    }
    public static ArrayList<String> toTest(ArrayList<Integer> key, String nums){
        ArrayList<String> results = new ArrayList<>();
        int amountOfOne=0;
        for (int i=0; i<key.size(); i++){
            amountOfOne+=key.get(i);
        }
        for (int i=0; i<Math.pow(2,nums.length()); i++) {
            String tempInt=(Integer.toBinaryString(i));
            //System.out.println(tempInt+" "+countFreq(tempInt,'1'));
            if (countFreq(tempInt, '1')==amountOfOne){
                while (tempInt.length()<nums.length()){
                    tempInt="0"+tempInt;
                }
                if(isSpaceds(tempInt, key)){
                    //System.out.println(tempInt+" "+key);
                    results.add(tempInt);
                }
            }
        }
        return results;
    }
    
    public static boolean isSpaceds(String s, ArrayList<Integer> key){
        
        if (s.length()==0){
            return true;
        }
        ArrayList<Integer> temp=new ArrayList<>();
        for (Integer curr: key){
            temp.add(curr);
        }
        int x=s.indexOf("1");
        if (x==-1){
            return key.size()==0;
        }
        
        if (key.size()==0||x+key.get(0)>s.length()){
            System.out.println("error"+s+" "+key.get(0));   
            return false;
        }
        for (int i=x; i<x+key.get(0); i++){
            if (s.charAt(i)!='1'){
                return false;
            }
        }
        if (x+key.get(0)>s.length()-1){
            return true;
        } else{
            if (s.charAt(x+(key.get(0)))!='0'){
                return false;
            }
        }
        
        temp.remove(0);
        return isSpaceds(s.substring(x+key.get(0)),temp);
    }
}
