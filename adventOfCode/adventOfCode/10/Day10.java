import java.io.*;
import java.util.*;
public class Day10 {
    public static void main(String[] args) throws Exception {
        int total=0;
        File file = new File("C:\\Users\\18438\\Desktop\\adventDay10.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        int counter=0;
        ArrayList<ArrayList<move>> nums=new ArrayList<>();
        ArrayList<move> temp=new ArrayList<>();
        for (int i=0; i<141; i++){
            temp.add(new move('.'));
        }
        nums.add(temp);
        counter++;
        while ((s=br.readLine()) != null){
            //use string s as input line
            nums.add(new ArrayList<move>());
            nums.get(counter).add(new move('.'));
            for (int i=0; i<s.length(); i++){
                nums.get(counter).add(new move(s.charAt(i)));
            }
            nums.get(counter).add(new move('.'));
            counter++;
        }
        ArrayList<move> temp2=new ArrayList<>();
        for (int i=0; i<141; i++){
            temp.add(new move('.'));
        }
        nums.add(temp2);
        counter++;
        
        //System.out.println(nums);
        int x=0; int y=0;
        ArrayList<point> previous=new ArrayList<>();
        for (int i=0; i<nums.size(); i++){
            for (int j=0; j<nums.get(i).size(); j++){
                if (nums.get(i).get(j).val=='S'){
                    y=i;
                    x=j;
                }
            }
        }
        point start=new point(x,y);
        point start1=new point(x,y-1);
        //point start1=new point(x+1,y);
        point start2=new point(x,y+1);
        System.out.println(start+" "+start1+" "+ start2);
        previous.add(start1);
        previous.add(start2);
        int max=0;
        while (findNextPoint(nums,previous,start1)!=null&&findNextPoint(nums,previous,start2)!=null){
            
            start1=findNextPoint(nums,previous,start1);
            start2=findNextPoint(nums,previous,start2);
            max++;
        }
        //System.out.println(previous.get(previous.size()-1)+" "+previous.get(previous.size()-1));
        System.out.println(max+1);
    }
    public static point findNextPoint(ArrayList<ArrayList<move>> nums, ArrayList<point> previous, point current){
        //if above
        previous.add(current);
        int x=current.x;
        int y=current.y;
        char curr=nums.get(current.y).get(current.x).val;
        if (curr=='J'||curr=='L'||curr=='|'){
            if (find(previous,new point(x,y-1))){
                if (nums.get(y-1).get(x).val=='|'){
                    return new point(x,y-1);
                }
                if (nums.get(y-1).get(x).val=='7'){
                    return new point(x,y-1);
                }
                if (nums.get(y-1).get(x).val=='F'){
                    return new point(x,y-1);
                }
            }
        } 
        //if below
        if (curr=='7'||curr=='F'||curr=='|'){
           if (find(previous,new point(x,y+1))){
                if (nums.get(y+1).get(x).val=='|'){
                    return new point(x,y+1);
                }
                if (nums.get(y+1).get(x).val=='J'){
                    return new point(x,y+1);
                }
                if (nums.get(y+1).get(x).val=='L'){
                    return new point(x,y+1);
                }
            }
        }
        //left
        if (curr=='-'||curr=='J'||curr=='7'){
           if (find(previous,new point(x-1,y))){
                if (nums.get(y).get(x-1).val=='-'){
                    return new point(x-1,y);
                }
                if (nums.get(y).get(x-1).val=='L'){
                    return new point(x-1,y);
                }
                if (nums.get(y).get(x-1).val=='F'){
                    return new point(x-1,y);
                }
            }
        }
        //right
        if (curr=='-'||curr=='L'||curr=='F'){
           if (find(previous,new point(x+1,y))){
                if (nums.get(y).get(x+1).val=='-'){
                    return new point(x+1,y);
                }
                if (nums.get(y).get(x+1).val=='J'){
                    return new point(x+1,y);
                }
                if (nums.get(y).get(x+1).val=='7'){
                    return new point(x+1,y);
                }
            }
        }
        System.out.println("error/final pos at "+x+" "+y+nums.get(y).get(x));
        return null;
    }
    
    public static boolean find(ArrayList<point> prev, point p){
        for (point curr: prev){
            if (curr.x==p.x&&curr.y==p.y){
                return false;
            }
        }
        return true;
    }
    public static class move{
        public char val;
        public move(char n){
            val=n;
        }
        
        public String toString(){
            return ""+val;
        }
    }
    
    public static class point{
        public int x;
        public int y;
        public point(int x1, int y1){
            x=x1;
            y=y1;
        }
        public String toString(){
            return x+" "+y;
        }
    }
}
