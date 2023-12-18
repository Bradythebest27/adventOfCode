import java.io.*;
import java.util.*;
public class advent17 {
    public static void main(String[] args) throws Exception {
        int total=0;
        File file = new File("C:\\Users\\18438\\Desktop\\adventDay17.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        int counter=0;
        int size=141;
        int[][] nums=new int[size][size];
        while ((s=br.readLine()) != null){
            //use string s as input line
            for (int i=0; i<s.length(); i++){
                nums[counter][i]=Integer.parseInt(s.substring(i,i+1));
            }
            counter++;
        }
        HashSet<move> seen=new HashSet<>();
        PriorityQueue<move> priorityQueue=new PriorityQueue<>();
        priorityQueue.add(new move(0,0,0,0,0,0));
        while (priorityQueue.size()>0){
            move current=priorityQueue.poll();
            if (current.row==size-1&&current.col==size-1){
                System.out.println(current.heatLoss);
                break;
            }
            if (!(current.row>=0&&current.row<size&&current.col>=0&&current.col<size)){
                System.out.println(current.heatLoss+" "+current.row+" "+current.col+" "+current.drow+" "+current.dcol+" "+current.steps);
                continue;
            }
            if (isIn(seen,current)){
                continue;
            }
            seen.add(current);
            if (current.steps<3&&!(current.drow==0&&current.dcol==0)){
                int tempDRow=current.row+current.drow;
                int tempDCol=current.col+current.dcol;
                if (tempDRow>=0&&tempDRow<size&&tempDCol>=0&&tempDCol<size){
                    priorityQueue.add(new move(current.heatLoss+nums[tempDRow][tempDCol],tempDRow,tempDCol,current.drow,current.dcol,current.steps+1));
                }
            }
            //turning
            //0,0 for start is an option on both
            //0,1 or //0,-1 
            if ((current.drow==0&&current.dcol==0)||(current.drow==0&&(current.dcol==1||current.dcol==-1))){
                int tempDRow=current.row+1;
                int tempDCol=current.col+0;
                if (tempDRow>=0&&tempDRow<size&&tempDCol>=0&&tempDCol<size){
                    priorityQueue.add(new move(current.heatLoss+nums[tempDRow][tempDCol],tempDRow,tempDCol,1,0,1));
                }
                int tempDRow2=current.row-1;
                int tempDCol2=current.col+0;
                if (tempDRow2>=0&&tempDRow2<size&&tempDCol2>=0&&tempDCol2<size){
                    priorityQueue.add(new move(current.heatLoss+nums[tempDRow2][tempDCol2],tempDRow2,tempDCol2,-1,0,1));
                }
            }
            //1,0 or //-1,0
            if ((current.drow==0&&current.dcol==0)||((current.drow==-1||current.drow==1)&&current.dcol==0)){
                int tempDRow=current.row+0;
                int tempDCol=current.col+1;
                if (tempDRow>=0&&tempDRow<size&&tempDCol>=0&&tempDCol<size){
                    priorityQueue.add(new move(current.heatLoss+nums[tempDRow][tempDCol],tempDRow,tempDCol,0,1,1));
                }
                int tempDRow2=current.row+0;
                int tempDCol2=current.col-1;
                if (tempDRow2>=0&&tempDRow2<size&&tempDCol2>=0&&tempDCol2<size){
                    priorityQueue.add(new move(current.heatLoss+nums[tempDRow2][tempDCol2],tempDRow2,tempDCol2,0,-1,1));
                }
            }
        }
    }
    public static boolean isIn(Set<move> seen, move curr){
        return seen.contains(curr);
    }
    public static move pqPop(ArrayList<move> nums){
        int minHL=nums.get(0).heatLoss;
        int minIn=0;
        for (int i=1; i<nums.size(); i++){
            if (nums.get(i).heatLoss<minHL){
                minIn=i;
                minHL=nums.get(i).heatLoss;
            }
        }
        move temp=nums.get(minIn);
        nums.remove(minIn);
        return temp;
    }
    static class move implements Comparable<move>{
        public int heatLoss;
        public int row;
        public int col;
        public int drow;
        public int dcol;
        public int steps;
        public move(int h, int r, int c, int d, int dc, int s){
            heatLoss=h;
            row=r;
            col=c;
            drow=d;
            dcol=dc;
            steps=s;
        }
        @Override
        public int compareTo(move other){
            return Integer.compare(this.heatLoss, other.heatLoss);
        }
        @Override
        public boolean equals(Object obj){
            if (this==obj){
                return true;
            }
            if (obj==null||getClass()!=obj.getClass()){
                return false;
            }
            move move=(move)obj;
            return row==move.row&&col==move.col&&drow==move.drow&&dcol==move.dcol&&steps==move.steps;
        }
    
        @Override
        public int hashCode(){
            return Objects.hash(row, col, drow, dcol, steps);
        }
    }
}