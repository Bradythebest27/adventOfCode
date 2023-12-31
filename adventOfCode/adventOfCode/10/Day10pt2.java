import java.io.*;
import java.util.*;
import java.lang.Object;
import java.awt.*;
public class Day10pt2 {
    public static void main(String[] args) throws Exception {
        int total=0;
        File file = new File("C:\\Users\\18438\\Desktop\\adventDay10.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        int counter=0;
        ArrayList<ArrayList<move>> nums=new ArrayList<>();
        ArrayList<move> temp=new ArrayList<>();                                                                                              
        for (int i=0; i<20; i++){
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
            temp2.add(new move('.'));
        }
        nums.add(temp2);
        counter++;
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
        point start1=new point(x-1,y);
        point start2=new point(x,y+1);
        previous.add(start1);
        previous.add(start2);
        int max=0;
        int count=3;
        while (findNextPoint(nums,previous,start1)!=null&&findNextPoint(nums,previous,start2)!=null){
            start1=findNextPoint(nums,previous,start1);
            start2=findNextPoint(nums,previous,start2);
            count+=2;
            max++;
        }
        ArrayList<point> nuhuh=new ArrayList<>();
        for (int i=0; i<nums.size(); i++){
            for (int j=0; j<nums.get(i).size(); j++){
                if ((i==0||j==nums.get(i).size()-1||j==0||i==nums.size()-1)){
                    nuhuh.add(new point(j,i));
                    
                }
            }
        }
        boolean end=true;
        while (end){
            boolean foundOne=false;
            for (int i=1; i<nums.size(); i++){
                for (int j=1; j<nums.get(i).size(); j++){
                    if ((find(nuhuh, new point(j,i)))&&find(previous, new point(j,i))){
                        if (notClosed(nums,previous,new point(j,i),nuhuh)){
                            nuhuh.add(new point(j,i));
                            foundOne=true;
                        }
                    }
                }
            }
            if (!foundOne){
                end=false;
            }
        }
        long numSize=0;
        for (int i=0; i<nums.size(); i++){
            for (int j=0; j<nums.get(i).size(); j++){
                numSize++;
            }
        }
        Polygon p=new Polygon();
        removeDuplicatePoints(previous);
        p.addPoint(start.x,start.y);
        for (point r: previous){
            if (!(nums.get(r.y).get(r.x).val=='|')&&!(nums.get(r.y).get(r.x).val=='-'))    
                p.addPoint(r.x,r.y);
        }
        int counter3=0;
        ArrayList<point> newer=new ArrayList<>();
        for (int i=1; i<nums.size()-1; i++){
            for (int j=1; j<nums.get(i).size()-1; j++){
                if (p.inside(j,i)){
                    if (find(previous, new point(j,i))){
                        newer.add(new point(j-1,i-1));
                        counter3++;
                    }
                }
            }
        }
        removeDuplicatePoints(newer);
        System.out.println(newer);
        System.out.println(newer.size());
    }
    public static void removeDuplicatePoints(ArrayList<point> r){
        for (int i=0; i<-1+r.size(); i++){
            for (int j=i+1; j<r.size(); j++){
                if (r.get(i).x==r.get(j).x&&r.get(i).y==r.get(j).y){
                    r.remove(j);
                    j--;
                }
            }
        }
    }
    public static boolean notClosed(ArrayList<ArrayList<move>> nums,ArrayList<point> previous, point current, ArrayList<point> nuhuh){
        
        //if above
        if ((find(previous,(new point(current.x,current.y-1))))&&!find(nuhuh,(new point(current.x,current.y-1)))){
            return true;
        }
        //if below
        if ((find(previous,(new point(current.x,current.y+1))))&&!find(nuhuh,(new point(current.x,current.y+1)))){
            return true;
        }
        //if left
        if ((find(previous,(new point(current.x-1,current.y))))&&!find(nuhuh,(new point(current.x-1,current.y)))){
            return true;
        }
        //if right
        if ((find(previous,(new point(current.x+1,current.y))))&&!find(nuhuh,(new point(current.x+1,current.y)))){
            return true;
        }
        return false;
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
