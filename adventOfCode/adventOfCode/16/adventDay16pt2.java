import java.io.*;
import java.util.*;
public class adventDay16pt2 {
    public static void main(String[] args) throws Exception {
        int total=0;
        File file = new File("C:\\Users\\18438\\Desktop\\adventDay16.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        int counter=0;
        int size=110;
        char[][] nums=new char[size][size];
        
        while ((s=br.readLine()) != null){
            //use string s as input line
            for (int i=0; i<s.length(); i++){
                nums[counter][i]=s.charAt(i);
                //used[counter][i]=false;
            }
            counter++;
        }
        ArrayList<move> versions=new ArrayList<>();
        for (int i=0; i<size; i++){
            versions.add(new move("left",i,0));
            versions.add(new move("right",i,size-1));
            versions.add(new move("up",0,i));
            versions.add(new move("down",size-1,i));
        }
        for (int x=0; x<versions.size(); x++){
            int tempTotal=0;
            ArrayList<move> movesToCheck=new ArrayList<>();
            movesToCheck.add(versions.get(x));
            boolean[][] used=new boolean[size][size];
            for (int p=0; p<size; p++){
                for (int q=0; q<size; q++){
                    used[p][q]=false;
                }
            }
            ArrayList<move> movesChecked=new ArrayList<>();
            for (int i=0; i<movesToCheck.size(); i++){
                move tempMove=movesToCheck.get(i);
                if (tempMove.row>=0&&tempMove.col>=0&&tempMove.row<size&&tempMove.col<size&&notIn(movesChecked,tempMove)){
                    used[tempMove.row][tempMove.col]=true;
                    movesChecked.add(tempMove);
                    //case .
                    if (nums[tempMove.row][tempMove.col]=='.'){
                        switch (tempMove.directionFro){
                            case "left":
                                movesToCheck.add(new move("left",tempMove.row,tempMove.col+1));
                                break;
                            case "right":
                                movesToCheck.add(new move("right",tempMove.row,tempMove.col-1));
                                break;
                            case "up":
                                movesToCheck.add(new move("up",tempMove.row+1,tempMove.col));
                                break;
                            case "down":
                                movesToCheck.add(new move("down",tempMove.row-1,tempMove.col));
                                break;
                            default:
                                System.out.println("error at 51, tried switching "+tempMove.directionFro);
                        }
                    }
                    //case /
                    else if (nums[tempMove.row][tempMove.col]=='/'){
                        switch (tempMove.directionFro){
                            case "left":
                                movesToCheck.add(new move("down",tempMove.row-1,tempMove.col));
                                break;
                            case "right":
                                movesToCheck.add(new move("up",tempMove.row+1,tempMove.col));
                                break;
                            case "up":
                                movesToCheck.add(new move("right",tempMove.row,tempMove.col-1));
                                break;
                            case "down":
                                movesToCheck.add(new move("left",tempMove.row,tempMove.col+1));
                                break;
                            default:
                                System.out.println("error at 70, tried switching "+tempMove.directionFro);
                        }
                    }
                    //case \
                    else if (nums[tempMove.row][tempMove.col]=='\\'){
                        switch (tempMove.directionFro){
                            case "right":
                                movesToCheck.add(new move("down",tempMove.row-1,tempMove.col));
                                break;
                            case "left":
                                movesToCheck.add(new move("up",tempMove.row+1,tempMove.col));
                                break;
                            case "down":
                                movesToCheck.add(new move("right",tempMove.row,tempMove.col-1));
                                break;
                            case "up":
                                movesToCheck.add(new move("left",tempMove.row,tempMove.col+1));
                                break;
                            default:
                                System.out.println("error at 89, tried switching "+tempMove.directionFro);
                        }
                    }
                    //case |
                    else if (nums[tempMove.row][tempMove.col]=='|'){
                        switch (tempMove.directionFro){
                            case "right":
                                movesToCheck.add(new move("down",tempMove.row-1,tempMove.col));
                                movesToCheck.add(new move("up",tempMove.row+1,tempMove.col));
                                break;
                            case "left":
                                movesToCheck.add(new move("down",tempMove.row-1,tempMove.col));
                                movesToCheck.add(new move("up",tempMove.row+1,tempMove.col));
                                break;
                            case "down":
                                movesToCheck.add(new move("down",tempMove.row-1,tempMove.col));
                                break;
                            case "up":
                                movesToCheck.add(new move("up",tempMove.row+1,tempMove.col));
                                break;
                            default:
                                System.out.println("error at 110, tried switching "+tempMove.directionFro);
                        }
                    }
                    //case -
                    else if (nums[tempMove.row][tempMove.col]=='-'){
                        switch (tempMove.directionFro){
                            case "right":
                                movesToCheck.add(new move("right",tempMove.row,tempMove.col-1));
                                break;
                            case "left":
                                movesToCheck.add(new move("left",tempMove.row,tempMove.col+1));
                                break;
                            case "down":
                                movesToCheck.add(new move("right",tempMove.row,tempMove.col-1));
                                movesToCheck.add(new move("left",tempMove.row,tempMove.col+1));
                                break;
                            case "up":
                                movesToCheck.add(new move("right",tempMove.row,tempMove.col-1));
                                movesToCheck.add(new move("left",tempMove.row,tempMove.col+1));
                                break;
                            default:
                                System.out.println("error at 131, tried switching "+tempMove.directionFro);
                        }
                    } else{
                        System.out.println("error tried switching "+nums[tempMove.row][tempMove.col]);
                    }
                }
            }
            for (int i2=0; i2<nums.length; i2++){
                for (int j2=0; j2<nums[i2].length; j2++){
                    if (used[i2][j2]){
                        tempTotal++;   
                    }
                }
            }
            if (tempTotal>total){
                total=tempTotal;
            }
        }
        System.out.println(total);
    }
    public static boolean notIn(ArrayList<move> nums, move n){
        for (move curr: nums){
            if (curr.row==n.row&&curr.col==n.col&&curr.directionFro.equals(n.directionFro)){
                return false;
            }
        }
        return true;
    }
    static class move{
        public String directionFro;
        public int row;
        public int col;
        public move(String s, int r, int c){
            directionFro=s;
            row=r;
            col=c;
        }
    }
}
