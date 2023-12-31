import java.io.*;
import java.util.*;
public class advent9pt2 {
    public static void main(String[] args) throws Exception {
        int total=0;
        File file = new File("C:\\Users\\18438\\Desktop\\adventDay9.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        ArrayList<String> inputs=new ArrayList<>();
        while ((s=br.readLine()) != null){
            //use string s as input line
            inputs.add(s);
        }
        for (String line: inputs){
            ArrayList<ArrayList<Integer>> vals=new ArrayList<>();
            vals.add(new ArrayList<Integer>());
            for (int i=0; i<line.length(); i+=0){
                if (line.indexOf(" ")!=-1){
                    vals.get(0).add(Integer.parseInt(line.substring(0,line.indexOf(" "))));
                    line=line.substring(line.indexOf(" ")+1);
                } else{
                    vals.get(0).add(Integer.parseInt(line));
                    i=line.length();
                }
            }
            while(!isAllZero(vals.get(vals.size()-1))){
                ArrayList<Integer> tempLine=new ArrayList<>();
                for (int nodeVal=0; nodeVal<-1+vals.get(vals.size()-1).size(); nodeVal++){
                    tempLine.add(vals.get(vals.size()-1).get(nodeVal+1)
                    -vals.get(vals.size()-1).get(nodeVal));
                }
                vals.add(tempLine);
            }
            int predict=0;
            for (int i=vals.size()-1; i>=0; i--){
                predict=vals.get(i).get(0)-predict;
            }
            total+=predict;
        }
        System.out.println(total);
    }
    public static boolean isAllZero(ArrayList<Integer> n){
        for (Integer curr: n){
            if (!curr.equals(0)){
                return false;
            }
        }
        return true;
    }
}
