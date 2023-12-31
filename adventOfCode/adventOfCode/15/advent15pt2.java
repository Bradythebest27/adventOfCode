import java.io.*;
import java.util.*;
public class advent15pt2 {
    public static void main(String[] args) throws Exception {
        int total=0;
        File file = new File("C:\\Users\\18438\\Desktop\\adventDay15.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        int counter=0;
        String all="";
        while ((s=br.readLine()) != null){
            //use string s as input line
            all=all+s;
            counter++;
        }
        ArrayList<String> nums2=new ArrayList<>();
        String tempString="";
        for (int i=0; i<all.length(); i++){
            if(all.charAt(i)!='\n'&&all.charAt(i)!=','){
                tempString+=all.charAt(i);
        
            } else if (all.charAt(i)==','){
                //System.out.println(tempTotal);
                nums2.add(tempString);
                tempString="";
            }
        }
        ArrayList<box> nums=new ArrayList<>();
        for (String curr: nums2){
            nums.add(new box(curr));
        }
        //System.out.println(nums);
        ArrayList<box>[] arr=new ArrayList[256];
        for (int i=0; i<256; i++){
            arr[i]=(new ArrayList<box>());
        }
        for (box curr: nums){
            int index=hashWord(curr.name);
            if (curr.type=='-'){
                //
                for (int i=0; i<arr[index].size(); i++){
                    if (arr[index].get(i).name.equals(curr.name)){
                        //arr[index].remove(i);
                        arr[index].set(i,new box("empty"));
                    }
                }
                for (int i=0; i<arr[index].size(); i++){
                    if (arr[index].get(i).val==0){
                        arr[index].remove(i);
                        //arr[index].add(0,new box("empty"));
                    }
                }
            } else{
                //
                boolean happened=false;
                for (int i=0; i<arr[index].size(); i++){
                    if (arr[index].get(i).name.equals(curr.name)){
                        //arr[index].remove(i);
                        happened=true;
                        arr[index].set(i,curr);
                    }
                }
                if (!happened){
                    arr[index].add(curr);
                }
            }
        }
        for (int i=0; i<arr.length; i++){
            for (int j=0; j<arr[i].size(); j++){
                //System.out.println(arr[i].get(j).name+" "+(i+1)+" "+(j+1)+" "+arr[i].get(j).val);
                total+=(i+1)*(j+1)*arr[i].get(j).val;
            }
        }
        System.out.println(total);
    }
    
    public static int hashWord(String s){
        int tempTotal=0;
        for (int i=0; i<s.length(); i++){
            tempTotal=hash(s.charAt(i),tempTotal);
        }
        return tempTotal;
    }
    public static int hash(char a,int total){
        int tempTotal=total+a;
        tempTotal*=17;
        return tempTotal%256;
    }
    static class box{
        public String name;
        public char type;
        public int val;
        public box(String s){
            if (s.equals("empty")){
                name="";
                type=' ';
                val=0;
            } else if (s.indexOf("-")!=-1){
                name=s.substring(0,s.indexOf("-"));
                type='-';
                val=-1;
            } else if (s.indexOf("=")!=-1){
                val=Integer.parseInt(s.substring(s.indexOf("=")+1));
                type='=';
                name=s.substring(0,s.indexOf("="));
            } else{
                System.out.println("error");
                name="S";
                type='z';
                val=9;
            }
        }
        
        public String toString(){
            return name+" "+type+" "+val;
        }
    }
}
