import java.io.*;
import java.util.*;
public class advent18 {
    public static void main(String[] args) throws Exception {
        int total=0;
        File file = new File("C:\\Users\\18438\\Desktop\\adventDay18.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        int counter=0;
        int size=1000;
        ArrayList<move> nums=new ArrayList<>();
        char[][] map=new char[size][size];
        while ((s=br.readLine()) != null){
            //use string s as input line
            String temp=s.substring(s.indexOf(" ")+1);
            nums.add(new move(s.charAt(0),Integer.parseInt(temp.substring(0,temp.indexOf(" ")))));
            counter++;
        }
        for (int i=0; i<map.length; i++){
            for (int j=0; j<map[i].length; j++){
                map[i][j]='.';
            }
        }
        int r=500;
        int c=500;
        int dr=0;
        int dc=0;
        map[r][c]='#';
        for (int i=0; i<nums.size(); i++){
            switch (nums.get(i).dir){
                case 'U':
                    dr=-1;
                    dc=0;
                    break;
                case 'D':
                    dr=1;
                    dc=0;
                    break;
                case 'R':
                    dc=1;
                    dr=0;
                    break;
                case 'L':
                    dc=-1;
                    dr=0;
                    break;
                default:
                    System.out.println("Error"+nums.get(i).dir);
            }
            for (int k=0; k<nums.get(i).amount; k++){
                r+=dr;
                c+=dc;
                map[r][c]='#';
            }
        }
        long lastSpread=0;
        ArrayList<point> spread=new ArrayList<>();
        for (int i=0; i<size; i++){
            spread.add(new point(i,0));
            map[i][0]='_';
            spread.add(new point(0,i));
            map[0][i]='_';
            spread.add(new point(i,size-1));
            map[i][size-1]='_';
            spread.add(new point(size-1,i));
            map[size-1][i]='_';
        }
        while (lastSpread!=spread.size()){
            lastSpread=spread.size();
            for (int i=0; i<spread.size(); i++){
                point curr=spread.get(i);
                if (curr.row+1>=0&&curr.row+1<size&&(map[curr.row+1][curr.col]!='#'&&map[curr.row+1][curr.col]!='_')){
                    spread.add(new point(curr.row+1,curr.col));
                    map[curr.row+1][curr.col]='_';
                }
                if (curr.row-1>=0&&curr.row-1<size&&(map[curr.row-1][curr.col]!='#'&&map[curr.row-1][curr.col]!='_')){
                    spread.add(new point(curr.row-1,curr.col));
                    map[curr.row-1][curr.col]='_';
                }
                if (curr.col+1>=0&&curr.col+1<size&&(map[curr.row][curr.col+1]!='#'&&map[curr.row][curr.col+1]!='_')){
                    spread.add(new point(curr.row,curr.col+1));
                    map[curr.row][curr.col+1]='_';
                }
                if (curr.col-1>=0&&curr.col-1<size&&(map[curr.row][curr.col-1]!='#'&&map[curr.row][curr.col-1]!='_')){
                    spread.add(new point(curr.row,curr.col-1));
                    map[curr.row][curr.col-1]='_';
                }
            }
        }
        System.out.println(size*size-spread.size()+4);
    }
    static class point{
        public int row;
        public int col;
        public point(int r, int c){
            row=r;
            col=c;
        }
    }
    static class move{
        public char dir;
        public int amount;
        public move(char d, int a){
            dir=d;
            amount=a;
        }
    }
}
