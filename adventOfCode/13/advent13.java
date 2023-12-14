import java.io.*;
import java.util.*;
public class advent13 {
    public static void main(String[] args) throws Exception {
        int total=0;
        File file = new File("C:\\Users\\18438\\Desktop\\adventDay13.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        int counter=0;
        ArrayList<ArrayList<String>> nums=new ArrayList<>();
        nums.add(new ArrayList<String>());
        while ((s=br.readLine()) != null){ 
            if (s.length()==0){
                nums.add(new ArrayList<String>());
            } else{
                nums.get(nums.size()-1).add(s);
            }
            counter++;
        }
        System.out.println(nums);
        for (int j=0; j<nums.size(); j++){
            ArrayList<String> currentString=nums.get(j);
            for (int i=0; i<currentString.size()-1; i++){
                int x=i;
                int increment=i+1;
                boolean works=true;
                while (x>=0&&increment<currentString.size()){
                    if (!currentString.get(x).equals(currentString.get(increment))){
                        works=false;
                    }
                    x--;
                    increment++;
                }
                if (works){
                    total+=100*(i+1);
                }
            }
        }            
        for (int j=0; j<nums.size(); j++){
            ArrayList<String> currentString=nums.get(j);
            for (int i=0; i<currentString.get(0).length()-1; i++){
                int x=i;
                int increment=i+1;
                boolean works=true;
                while (x>=0&&increment<currentString.get(0).length()){
                    for (int k=0; k<currentString.size(); k++){
                        if (currentString.get(k).charAt(x)!=(currentString.get(k).charAt(increment))){
                            works=false;
                        }
                    }
                    x--;
                    increment++;
                }
                if (works){
                    total+=1+i;
                }
            }
        }      
            
    
        
        System.out.println(total);
    }
    
}
