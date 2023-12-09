import java.io.*;
import java.util.*;
public class advent9pt2 {
    public static void main(String[] args) throws Exception {
        int total=0;
        File file = new File("C:\\Users\\18438\\Desktop\\adventDay9.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        //int counter=0;
        ArrayList<String> inputs=new ArrayList<>();
        while ((s=br.readLine()) != null){
            //use string s as input line
            inputs.add(s);
            //counter++;
        }
        for (String line: inputs){
            ArrayList<ArrayList<node>> vals=new ArrayList<>();
            vals.add(new ArrayList<node>());
            for (int i=0; i<line.length(); i+=0){
                if (line.indexOf(" ")!=-1){
                    vals.get(0).add(new node(Integer.parseInt(line.substring(0,line.indexOf(" ")))));
                    line=line.substring(line.indexOf(" ")+1);
                    //System.out.println(line);
                } else{
                    vals.get(0).add(new node(Integer.parseInt(line)));
                    i=line.length();
                }
            }
            while(!isAllZero(vals.get(vals.size()-1))){
                ArrayList<node> tempLine=new ArrayList<>();
                for (int nodeVal=0; nodeVal<-1+vals.get(vals.size()-1).size(); nodeVal++){//node prev: vals.get(vals.size()-1)){
                    tempLine.add(new node(vals.get(vals.size()-1).get(nodeVal+1).getVal()
                    -vals.get(vals.size()-1).get(nodeVal).getVal()));
                }
                vals.add(tempLine);
            }
            int predict=0;
            for (int i=vals.size()-1; i>=0; i--){
                predict=vals.get(i).get(0).getVal()-predict;
            }
            total+=predict;
            //System.out.println(predict);
            //System.out.println(vals);
        }
        System.out.println(total);
    }
    
    public static boolean isAllZero(ArrayList<node> n){
        for (node curr: n){
            if (curr.getVal()!=0){
                return false;
            }
        }
        return true;
    }
    
    public static class node{
        private int val;
    
        public node(int v){
            val=v; 
        }
        
        public  int getVal(){
            return val;
        }
        public String toString(){
            return this.getVal()+" ";
        }
    }
}
