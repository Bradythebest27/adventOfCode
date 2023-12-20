import java.io.*;
import java.util.*;
public class advent19 {
    public static void main(String[] args) throws Exception {
        int total=0;
        File file = new File("C:\\Users\\18438\\Desktop\\adventDay19.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        int counter=0;
        ArrayList<input> nums=new ArrayList<>();
        ArrayList<xmas> test=new ArrayList<>();
        boolean first=true;
        while ((s=br.readLine()) != null){
            if (s.length()==0){
                first=false;
                s=br.readLine();
            }
            //use string s as input line
            if (first){
                ArrayList<String> conditions=new ArrayList<>();
                String n=s.substring(0,s.indexOf("{"));
                String temp=s.substring(s.indexOf("{")+1,s.indexOf("}"))+",";
                while(temp.indexOf(",")!=-1){
                    conditions.add(temp.substring(0,temp.indexOf(",")));
                    temp=temp.substring(temp.indexOf(",")+1);
                }
                nums.add(new input(n,conditions));
            } else{
                String temp=s.substring(0,s.indexOf("}"))+",";
                int x1=Integer.parseInt(temp.substring(temp.indexOf("=")+1,temp.indexOf(",")));
                temp=temp.substring(temp.indexOf(",")+1);
                int m1=Integer.parseInt(temp.substring(temp.indexOf("=")+1,temp.indexOf(",")));
                temp=temp.substring(temp.indexOf(",")+1);
                int a1=Integer.parseInt(temp.substring(temp.indexOf("=")+1,temp.indexOf(",")));
                temp=temp.substring(temp.indexOf(",")+1);
                int s1=Integer.parseInt(temp.substring(temp.indexOf("=")+1,temp.indexOf(",")));
                temp=temp.substring(temp.indexOf(",")+1);
                test.add(new xmas(x1,m1,a1,s1));
            }
            counter++;
        }
        int start=-1;
        for (int i=0; i<nums.size(); i++){
            if (nums.get(i).name.equals("in")){
                start=i;
                break;
            }
        }
        for (xmas curr: test){
            if (accept(nums,curr,start,0)){
                total+=curr.x+curr.m+curr.a+curr.s;
            }
        }
        System.out.println(total);
    }
    public static boolean accept(ArrayList<input> nums, xmas test, int numIn, int conIn){
        String conTest=nums.get(numIn).con.get(conIn);
        if (conTest.equals("A")){
            return true;
        }
        if (conTest.equals("R")){
            return false;
        }
        if (conTest.indexOf(":")==-1){
            int rePosIn=-1;
            for (int i=0; i<nums.size(); i++){
                if (nums.get(i).name.equals(conTest)){
                    rePosIn=i;
                    break;
                }
            }
            if (rePosIn==-1){
                System.out.println("error finding move "+conTest);
            }
            return accept(nums,test,rePosIn,0);
        } else{
            //normal
            boolean greaterThan=(conTest.charAt(1)=='>');
            switch (conTest.charAt(0)){
                case 'x':
                    if (greaterThan){
                        if (test.x>Integer.parseInt(conTest.substring(2,conTest.indexOf(":")))){
                            String ret=conTest.substring(conTest.indexOf(":")+1);
                            if (ret.equals("A")){
                                return true;  
                            }
                            if (ret.equals("R")){
                                return false;
                            }
                            int rePosIn=-1;
                            for (int i=0; i<nums.size(); i++){
                                if (nums.get(i).name.equals(ret)){
                                    rePosIn=i;
                                    break;
                                }
                            }
                            if (rePosIn==-1){
                                System.out.println("error finding move "+conTest);
                            }
                            return accept(nums,test,rePosIn,0);
                        } else{
                            return accept(nums,test,numIn,conIn+1);
                        }
                    } else{
                        if (test.x<Integer.parseInt(conTest.substring(2,conTest.indexOf(":")))){
                            String ret=conTest.substring(conTest.indexOf(":")+1);
                            if (ret.equals("A")){
                                return true;  
                            }
                            if (ret.equals("R")){
                                return false;
                            }
                            int rePosIn=-1;
                            for (int i=0; i<nums.size(); i++){
                                if (nums.get(i).name.equals(ret)){
                                    rePosIn=i;
                                    break;
                                }
                            }
                            if (rePosIn==-1){
                                System.out.println("error finding move "+conTest);
                            }
                            return accept(nums,test,rePosIn,0);
                        } else{
                            return accept(nums,test,numIn,conIn+1);
                        }
                    }
                case 'm':
                    if (greaterThan){
                        if (test.m>Integer.parseInt(conTest.substring(2,conTest.indexOf(":")))){
                            String ret=conTest.substring(conTest.indexOf(":")+1);
                            if (ret.equals("A")){
                                return true;  
                            }
                            if (ret.equals("R")){
                                return false;
                            }
                            int rePosIn=-1;
                            for (int i=0; i<nums.size(); i++){
                                if (nums.get(i).name.equals(ret)){
                                    rePosIn=i;
                                    break;
                                }
                            }
                            if (rePosIn==-1){
                                System.out.println("error finding move "+conTest);
                            }
                            return accept(nums,test,rePosIn,0);
                        } else{
                            return accept(nums,test,numIn,conIn+1);
                        }
                    } else{
                        if (test.m<Integer.parseInt(conTest.substring(2,conTest.indexOf(":")))){
                            String ret=conTest.substring(conTest.indexOf(":")+1);
                            if (ret.equals("A")){
                                return true;  
                            }
                            if (ret.equals("R")){
                                return false;
                            }
                            int rePosIn=-1;
                            for (int i=0; i<nums.size(); i++){
                                if (nums.get(i).name.equals(ret)){
                                    rePosIn=i;
                                    break;
                                }
                            }
                            if (rePosIn==-1){
                                System.out.println("error finding move "+conTest);
                            }
                            return accept(nums,test,rePosIn,0);
                        } else{
                            return accept(nums,test,numIn,conIn+1);
                        }
                    }
                case 'a':
                    if (greaterThan){
                        if (test.a>Integer.parseInt(conTest.substring(2,conTest.indexOf(":")))){
                            String ret=conTest.substring(conTest.indexOf(":")+1);
                            if (ret.equals("A")){
                                return true;  
                            }
                            if (ret.equals("R")){
                                return false;
                            }
                            int rePosIn=-1;
                            for (int i=0; i<nums.size(); i++){
                                if (nums.get(i).name.equals(ret)){
                                    rePosIn=i;
                                    break;
                                }
                            }
                            if (rePosIn==-1){
                                System.out.println("error finding move "+conTest);
                            }
                            return accept(nums,test,rePosIn,0);
                        } else{
                            return accept(nums,test,numIn,conIn+1);
                        }
                    } else{
                        if (test.a<Integer.parseInt(conTest.substring(2,conTest.indexOf(":")))){
                            String ret=conTest.substring(conTest.indexOf(":")+1);
                            if (ret.equals("A")){
                                return true;  
                            }
                            if (ret.equals("R")){
                                return false;
                            }
                            int rePosIn=-1;
                            for (int i=0; i<nums.size(); i++){
                                if (nums.get(i).name.equals(ret)){
                                    rePosIn=i;
                                    break;
                                }
                            }
                            if (rePosIn==-1){
                                System.out.println("error finding move "+conTest);
                            }
                            return accept(nums,test,rePosIn,0);
                        } else{
                            return accept(nums,test,numIn,conIn+1);
                        }
                    }
                case 's':
                    if (greaterThan){
                        if (test.s>Integer.parseInt(conTest.substring(2,conTest.indexOf(":")))){
                            String ret=conTest.substring(conTest.indexOf(":")+1);
                            if (ret.equals("A")){
                                return true;  
                            }
                            if (ret.equals("R")){
                                return false;
                            }
                            int rePosIn=-1;
                            for (int i=0; i<nums.size(); i++){
                                if (nums.get(i).name.equals(ret)){
                                    rePosIn=i;
                                    break;
                                }
                            }
                            if (rePosIn==-1){
                                System.out.println("error finding move "+conTest);
                            }
                            return accept(nums,test,rePosIn,0);
                        } else{
                            return accept(nums,test,numIn,conIn+1);
                        }
                    } else{
                        if (test.s<Integer.parseInt(conTest.substring(2,conTest.indexOf(":")))){
                            String ret=conTest.substring(conTest.indexOf(":")+1);
                            if (ret.equals("A")){
                                return true;  
                            }
                            if (ret.equals("R")){
                                return false;
                            }
                            int rePosIn=-1;
                            for (int i=0; i<nums.size(); i++){
                                if (nums.get(i).name.equals(ret)){
                                    rePosIn=i;
                                    break;
                                }
                            }
                            if (rePosIn==-1){
                                System.out.println("error finding move "+conTest);
                            }
                            return accept(nums,test,rePosIn,0);
                        } else{
                            return accept(nums,test,numIn,conIn+1);
                        }
                    }
                default:
                    System.out.println("error tried switching "+conTest.charAt(0));
                    return false;
            }
        }
    }
    static class input{
        public String name;
        public ArrayList<String> con;
        public input(String s, ArrayList<String> c){
            name=s;
            con=c;
        }
    }
    
    static class xmas{
        public int x;
        public int m;
        public int a;
        public int s;
        public xmas(int x1, int m1, int a1, int s1){
            x=x1;
            m=m1;
            a=a1;
            s=s1;
        }
    }
}
