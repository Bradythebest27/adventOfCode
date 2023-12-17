import java.io.*;
import java.util.*;
public class advent13pt2 {
    public static void main(String[] args) throws Exception {
        int total=0;
        File file = new File("C:\\Users\\18438\\Desktop\\adventDay13.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        int counter=0;
        ArrayList<ArrayList<String>> nums=new ArrayList<>();
        nums.add(new ArrayList<String>());
        ArrayList<Boolean> foundASmudge=new ArrayList<>();
        while ((s=br.readLine()) != null){ 
            if (s.length()==0){
                nums.add(new ArrayList<String>());
            } else{
                nums.get(nums.size()-1).add(s);
                foundASmudge.add(false);
            }
            counter++;
        }
        for (int j=0; j<nums.size(); j++){
            ArrayList<String> currentString=nums.get(j);
            boolean foundASmudge2=false;
            int x;
            int increment;
            int works;
            int locationOfSmudge;
            for (int i=0; i<currentString.size()-1; i++){
                x=i;
                increment=i+1;
                locationOfSmudge=-1;
                if (foundASmudge2){
                     works=1;    
                } else{
                     works=2;
                }
                while (x>=0&&increment<currentString.size()){
                    for (int k=0; k<currentString.get(0).length(); k++){            
                        if (currentString.get(x).charAt(k)!=currentString.get(increment).charAt(k)){
                            works--;
                            if (!foundASmudge2&&works==1&&locationOfSmudge==-1){
                                locationOfSmudge=k;
                            }      
                        }
                    }
                    x--;
                    increment++;
                }
                if (works==1&&!foundASmudge2){
                    foundASmudge2=true;
                    foundASmudge.set(j,true);
                    char[] n=nums.get(j).get(i).toCharArray();
                    if (n[locationOfSmudge]=='.'){
                        n[locationOfSmudge]='#';
                    } else{
                        n[locationOfSmudge]='.';
                    }
                    String y=new String(n);
                    nums.get(j).set(i,y);
                    total+=100*(1+i);
                }
            }
        }            
        
        for (int j=0; j<nums.size(); j++){
            ArrayList<String> currentString=nums.get(j);
            boolean foundASmudge2=foundASmudge.get(j);
            for (int i=0; i<currentString.get(0).length()-1; i++){
                int x=i;
                int increment=i+1;
                int works;
                int locationOfSmudge=-1;
                if (foundASmudge2){
                     works=1;    
                } else{
                     works=2;
                }
                while (x>=0&&increment<currentString.get(0).length()){
                    for (int k=0; k<currentString.size(); k++){
                        if (currentString.get(k).charAt(x)!=(currentString.get(k).charAt(increment))){
                            works--;     
                            if (works==1&&locationOfSmudge==-1){
                                locationOfSmudge=k;
                            }
                            if (works==0){
                                locationOfSmudge=-1;
                            }
                        }
                    }
                    x--;
                    increment++;
                }
                
                if (works==1&&!foundASmudge2){
                    foundASmudge2=true;
                    foundASmudge.set(j,true);
                    char[] n=nums.get(j).get(locationOfSmudge).toCharArray();
                    if (n[i]=='.'){
                        n[i]='#';
                    } else{
                        n[i]='.';
                    }
                    String y=new String(n);
                    nums.get(j).set(locationOfSmudge,y);
                    total+=1+i;
                }
            }
        }      
        System.out.println(total);
    }
    
}
