import java.io.*;
import java.util.*;
public class advent22pt2{
    public static void main(String[] args) throws Exception{
        File file=new File("C:\\Users\\18438\\Desktop\\adventDay22.txt");
        BufferedReader br=new BufferedReader(new FileReader(file));
        String s;
        int counter=1;
        ArrayList<Block> nums=new ArrayList<>();
        int[][][] map=new int[10][10][353];

        // Parse input and create Blocks
        while ((s=br.readLine())!=null){
            String E1=s.substring(0, s.indexOf("~"));
            String E2=s.substring(s.indexOf("~") + 1);
            nums.add(new Block(E1, E2, counter));
            counter++;
        }
        for (Block currBlock: nums){
            for (Point p: currBlock.Blocks){
                map[p.x][p.y][p.z]=currBlock.name;
            }
        }
        boolean happened=true;
        while (happened){
            happened=false;
            for (Block currBlock: nums){
                boolean canMove=true;
                for (Point curr: currBlock.Blocks){
                    if (curr.z > 1 && (map[curr.x][curr.y][curr.z-1]!=0
                            && map[curr.x][curr.y][curr.z-1]!=currBlock.name)){
                        canMove=false;
                        break; // No need to check further if any Block cannot move
                    }
                    if (curr.z==1){
                        canMove=false;
                        break;
                    }
                }
                if (canMove){
                    happened=true;
                    if (currBlock.Blocks.size()<2||currBlock.Blocks.get(0).z!=currBlock.Blocks.get(1).z){
                        for (Point curr: currBlock.Blocks){
                            map[curr.x][curr.y][curr.z]=0;
                        }
                        for (Point curr: currBlock.Blocks){
                            map[curr.x][curr.y][curr.z-1]=currBlock.name;
                            curr.z--;
                        }
                    } else{
                        for (Point curr: currBlock.Blocks){
                            
                            map[curr.x][curr.y][curr.z]=0;
                            map[curr.x][curr.y][curr.z-1]=currBlock.name;
                            curr.z--;
                        }
                    }   
                }
            }
        }
        // Determine which bricks can be safely disintegrated
        
        ArrayList<Block> cannotMove=new ArrayList<>();
        for (Block currBlock: nums){
            for (Point p: currBlock.Blocks){
                map[p.x][p.y][p.z]=0;
            }
            if (canMoveOG(nums,map)){
                cannotMove.add(currBlock);
            }
            for (Point p: currBlock.Blocks){
                map[p.x][p.y][p.z]=currBlock.name;
            }
        }
        System.out.println(cannotMove);
        int safeBricks=0;
        for (Block currBlock: cannotMove){
            for (Point p: currBlock.Blocks){
                map[p.x][p.y][p.z]=0;
            }
            safeBricks+=canMove(nums,map);
            System.out.println(safeBricks);
            for (Point p: currBlock.Blocks){
                map[p.x][p.y][p.z]=currBlock.name;
            }
        }

        System.out.println("Number of bricks that can be safely disintegrated: " + safeBricks);
    }
    public static boolean canMoveOG(ArrayList<Block> nums, int[][][] map){
        for (Block currBlock: nums){
            boolean canMove=true;
            for (Point curr: currBlock.Blocks){
                if (curr.z > 1 && (map[curr.x][curr.y][curr.z-1]!=0
                        && map[curr.x][curr.y][curr.z-1]!=currBlock.name)){
                    canMove=false;
                    break; // No need to check further if any Block cannot move
                }
                if (curr.z==1){
                    canMove=false;
                    break;
                }
            }
            if (canMove){
                return true;
            } 
        }
        return false;
    }
    public static int canMove(ArrayList<Block> nums1, int[][][] map1){
        int[][][] map=new int[10][10][353];
        for (int i=0; i<10; i++){
            for (int j=0; j<10; j++){
                for (int k=0; k<353; k++){
                    map[i][j][k]=map1[i][j][k];
                }
            }
        }
        ArrayList<Block> nums=new ArrayList<>();
        for (Block currBlock: nums1){
            ArrayList<Point> p2=new ArrayList<>();
            for (Point p: currBlock.Blocks){
                p2.add(new Point(p.x,p.y,p.z));
            }
            nums.add(new Block(currBlock.name,p2));
        }
        int moved=0;
        boolean happened=true;
        ArrayList<Integer> t=new ArrayList<>();
        while (happened){
            happened=false;
            for (int i=0; i<nums.size(); i++){
                
                Block currBlock=nums.get(i);
                boolean canMove=true;
                for (Point curr: currBlock.Blocks){
                    if (curr.z > 1 && (map[curr.x][curr.y][curr.z-1]!=0
                            && map[curr.x][curr.y][curr.z-1]!=currBlock.name)){
                        canMove=false;
                        break; // No need to check further if any Block cannot move
                    }
                    if (curr.z==1){
                        canMove=false;
                        break;
                    }
                }
                if (canMove&&!(t.contains(i))){
                    //System.out.println("moved this "+i);
                    t.add(i);
                    moved++;
                    happened=true;
                    if (currBlock.Blocks.size()<2||currBlock.Blocks.get(0).z!=currBlock.Blocks.get(1).z){
                        for (Point curr: currBlock.Blocks){
                            map[curr.x][curr.y][curr.z]=0;
                        }
                        for (Point curr: currBlock.Blocks){
                            map[curr.x][curr.y][curr.z-1]=currBlock.name;
                            curr.z--;
                        }
                    } else{
                        for (Point curr: currBlock.Blocks){
                            
                            map[curr.x][curr.y][curr.z]=0;
                            map[curr.x][curr.y][curr.z-1]=currBlock.name;
                            curr.z--;
                        }
                    }   

                }
            }
        }
        return moved;
    }
    static class Block{
        public ArrayList<Point> Blocks;
        public int name;
        public Block(String one, String two, int n){
            int x1=Integer.parseInt(one.substring(0,one.indexOf(","))); 
            int y1=Integer.parseInt(one.substring(one.indexOf(",")+1,one.lastIndexOf(",")));
            int z1=Integer.parseInt(one.substring(one.lastIndexOf(",")+1));
            int x2=Integer.parseInt(two.substring(0,two.indexOf(",")));
            int y2=Integer.parseInt(two.substring(two.indexOf(",")+1,two.lastIndexOf(",")));
            int z2=Integer.parseInt(two.substring(two.lastIndexOf(",")+1));
            Blocks=new ArrayList<>();
            if (x1!=x2){
                for (int i=x1; i<=x2; i++){
                    Blocks.add(new Point(i,y1,z1));
                }
            } else if(y1!=y2){
                for (int i=y1; i<=y2; i++){
                    Blocks.add(new Point(x1,i,z1));
                }
            } else if (z1!=z2){
                for (int i=z1; i<=z2; i++){
                    Blocks.add(new Point(x1,y1,i));
                }
            } else{
                Blocks.add(new Point(x1,y1,z1));
            }
            name=n;
        }
        public Block(int i, ArrayList<Point> b){
            Blocks=b;
            name=i;
        }
        public String toString(){
            return Blocks.toString()+" "+name;
        }
    }
    static class Point{
        public int x;
        public int y;
        public int z;
        public Point(int x1, int y1, int z1){
            x=x1;
            y=y1;
            z=z1;
        }
        public String toString(){
            return x+" "+y+" "+z;
        }
    }
}

