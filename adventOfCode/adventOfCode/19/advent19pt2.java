import java.io.*;
import java.util.*;
public class advent19pt2 {
    public static void main(String[] args) throws Exception {
        long total=0;
        File file = new File("C:\\Users\\18438\\Desktop\\adventDay19.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        int counter=0;
        ArrayList<input> nums=new ArrayList<>();
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
        //System.out.println("start "+start);
        total=accept(nums,new xmas(1,4000,1,4000,1,4000,1,4000),start,0);
        System.out.println(total);
    }
    public static long accept(ArrayList<input> nums, xmas test, int numIn, int conIn){
        //System.out.println(numIn+" "+conIn+" "+"x: "+test.xlb+"-"+test.xub+" m: "+test.mlb+"-"+test.mub+" a: "+test.alb+"-"+test.aub+" s: "+test.slb+"-"+test.sub);
        String conTest=nums.get(numIn).con.get(conIn);
        if (conTest.equals("A")){
            return test.xmasVal();
        }
        if (conTest.equals("R")){
            return 0;
        }
        //last element switching to different list
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
        }
        //a condition
        boolean greaterThan=(conTest.charAt(1)=='>');
        boolean outA=false;
        boolean outR=false;
        int rePosIn=-1;
        for (int i=0; i<nums.size(); i++){
            if (nums.get(i).name.equals(conTest.substring(conTest.indexOf(":")+1))){
                rePosIn=i;
                break;
            }
        }
            
        if (conTest.substring(conTest.indexOf(":")+1).equals("A")){
            outA=true;
        } else if (conTest.substring(conTest.indexOf(":")+1).equals("R")){
            outR=true;
        }
        if (rePosIn==-1&&!outA&&!outR){
            System.out.println("Error at 80 "+conTest);
            return 0;
        }
        int compare=Integer.parseInt(conTest.substring(2,conTest.indexOf(":")));
        switch (conTest.charAt(0)){
            case 'x':
                if (greaterThan){
                    if (compare>test.xlb&&compare<test.xub){
                        //in between bounds
                        xmas acceptCon=new xmas(compare+1,test.xub,test.mlb,test.mub,test.alb,test.aub,test.slb,test.sub);
                        xmas next=new xmas(test.xlb,compare,test.mlb,test.mub,test.alb,test.aub,test.slb,test.sub);
                        if (outR){
                            return 0+accept(nums,next,numIn,conIn+1);
                        } else if (outA){
                            return acceptCon.xmasVal()+accept(nums,next,numIn,conIn+1);
                        } else{
                            return accept(nums,acceptCon,rePosIn,0)+accept(nums,next,numIn,conIn+1);
                        }
                    } else if (compare<test.xlb){
                        //left
                        if (outR){
                            return 0;
                        } else if (outA){
                            return test.xmasVal();
                        } else{
                            return accept(nums,test,rePosIn,0);
                        }
                    } else if (compare>test.xub){
                        //right
                        return accept(nums,test,numIn,conIn+1);
                    }
                } else{//less than
                    if (compare>test.xlb&&compare<test.xub){
                        //in between bounds
                        xmas acceptCon=new xmas(test.xlb,compare-1,test.mlb,test.mub,test.alb,test.aub,test.slb,test.sub);
                        xmas next=new xmas(compare,test.xub,test.mlb,test.mub,test.alb,test.aub,test.slb,test.sub);
                        if (outR){
                            return 0+accept(nums,next,numIn,conIn+1);
                        } else if (outA){
                            return acceptCon.xmasVal()+accept(nums,next,numIn,conIn+1);
                        } else{
                            return accept(nums,acceptCon,rePosIn,0)+accept(nums,next,numIn,conIn+1);
                        }
                    } else if (compare<test.xlb){
                        //left
                        return accept(nums,test,numIn,conIn+1);
                    } else if (compare>test.xub){
                        //right
                        if (outR){
                            return 0;
                        } else if (outA){
                            return test.xmasVal();
                        } else{
                            return accept(nums,test,rePosIn,0);
                        }
                    }
                }
            case 'm':
                if (greaterThan){
                    if (compare>test.mlb&&compare<test.mub){
                        //in between bounds
                        xmas acceptCon=new xmas(test.xlb,test.xub,compare+1,test.mub,test.alb,test.aub,test.slb,test.sub);
                        xmas next=new xmas(test.xlb,test.xub,test.mlb,compare,test.alb,test.aub,test.slb,test.sub);
                        if (outR){
                            return 0+accept(nums,next,numIn,conIn+1);
                        } else if (outA){
                            return acceptCon.xmasVal()+accept(nums,next,numIn,conIn+1);
                        } else{
                            return accept(nums,acceptCon,rePosIn,0)+accept(nums,next,numIn,conIn+1);
                        }
                    } else if (compare<test.mlb){
                        //left
                        if (outR){
                            return 0;
                        } else if (outA){
                            return test.xmasVal();
                        } else{
                            return accept(nums,test,rePosIn,0);
                        }
                    } else if (compare>test.mub){
                        //right
                        return accept(nums,test,numIn,conIn+1);
                    }
                } else{//less than
                    if (compare>test.mlb&&compare<test.mub){
                        //in between bounds
                        xmas acceptCon=new xmas(test.xlb,test.xub,test.mlb,compare-1,test.alb,test.aub,test.slb,test.sub);
                        xmas next=new xmas(test.xlb,test.xub,compare,test.mub,test.alb,test.aub,test.slb,test.sub);
                        if (outR){
                            return 0+accept(nums,next,numIn,conIn+1);
                        } else if (outA){
                            return acceptCon.xmasVal()+accept(nums,next,numIn,conIn+1);
                        } else{
                            return accept(nums,acceptCon,rePosIn,0)+accept(nums,next,numIn,conIn+1);
                        }
                    } else if (compare<test.mlb){
                        //left
                        return accept(nums,test,numIn,conIn+1);
                    } else if (compare>test.mub){
                        //right
                        if (outR){
                            return 0;
                        } else if (outA){
                            return test.xmasVal();
                        } else{
                            return accept(nums,test,rePosIn,0);
                        }
                    }
                }
            case 'a':
                if (greaterThan){
                    if (compare>test.alb&&compare<test.aub){
                        //in between bounds
                        xmas acceptCon=new xmas(test.xlb,test.xub,test.mlb,test.mub,compare+1,test.aub,test.slb,test.sub);
                        xmas next=new xmas(test.xlb,test.xub,test.mlb,test.mub,test.alb,compare,test.slb,test.sub);
                        if (outR){
                            return 0+accept(nums,next,numIn,conIn+1);
                        } else if (outA){
                            return acceptCon.xmasVal()+accept(nums,next,numIn,conIn+1);
                        } else{
                            return accept(nums,acceptCon,rePosIn,0)+accept(nums,next,numIn,conIn+1);
                        }
                    } else if (compare<test.alb){
                        //left
                        if (outR){
                            return 0;
                        } else if (outA){
                            return test.xmasVal();
                        } else{
                            return accept(nums,test,rePosIn,0);
                        }
                    } else if (compare>test.aub){
                        //right
                        return accept(nums,test,numIn,conIn+1);
                    }
                } else{//less than
                    if (compare>test.alb&&compare<test.aub){
                        //in between bounds
                        xmas acceptCon=new xmas(test.xlb,test.xub,test.mlb,test.mub,test.alb,compare-1,test.slb,test.sub);
                        xmas next=new xmas(test.xlb,test.xub,test.mlb,test.mub,compare,test.aub,test.slb,test.sub);
                        if (outR){
                            return 0+accept(nums,next,numIn,conIn+1);
                        } else if (outA){
                            return acceptCon.xmasVal()+accept(nums,next,numIn,conIn+1);
                        } else{
                            return accept(nums,acceptCon,rePosIn,0)+accept(nums,next,numIn,conIn+1);
                        }
                    } else if (compare<test.alb){
                        //left
                        return accept(nums,test,numIn,conIn+1);
                    } else if (compare>test.aub){
                        //right
                        if (outR){
                            return 0;
                        } else if (outA){
                            return test.xmasVal();
                        } else{
                            return accept(nums,test,rePosIn,0);
                        }
                    }
                }
            case 's':
                if (greaterThan){
                    if (compare>test.slb&&compare<test.sub){
                        //in between bounds
                        xmas acceptCon=new xmas(test.xlb,test.xub,test.mlb,test.mub,test.alb,test.aub,compare+1,test.sub);
                        xmas next=new xmas(test.xlb,test.xub,test.mlb,test.mub,test.alb,test.aub,test.slb,compare);
                        if (outR){
                            return 0+accept(nums,next,numIn,conIn+1);
                        } else if (outA){
                            return acceptCon.xmasVal()+accept(nums,next,numIn,conIn+1);
                        } else{
                            return accept(nums,acceptCon,rePosIn,0)+accept(nums,next,numIn,conIn+1);
                        }
                    } else if (compare<test.slb){
                        //left
                        if (outR){
                            return 0;
                        } else if (outA){
                            return test.xmasVal();
                        } else{
                            return accept(nums,test,rePosIn,0);
                        }
                    } else if (compare>test.sub){
                        //right
                        return accept(nums,test,numIn,conIn+1);
                    }
                } else{//less than
                    if (compare>test.slb&&compare<test.sub){
                        //in between bounds
                        xmas acceptCon=new xmas(test.xlb,test.xub,test.mlb,test.mub,test.alb,test.aub,test.slb,compare-1);
                        xmas next=new xmas(test.xlb,test.xub,test.mlb,test.mub,test.alb,test.aub,compare,test.sub);
                        if (outR){
                            return 0+accept(nums,next,numIn,conIn+1);
                        } else if (outA){
                            return acceptCon.xmasVal()+accept(nums,next,numIn,conIn+1);
                        } else{
                            return accept(nums,acceptCon,rePosIn,0)+accept(nums,next,numIn,conIn+1);
                        }
                    } else if (compare<test.slb){
                        //left
                        return accept(nums,test,numIn,conIn+1);
                    } else if (compare>test.sub){
                        //right
                        if (outR){
                            return 0;
                        } else if (outA){
                            return test.xmasVal();
                        } else{
                            return accept(nums,test,rePosIn,0);
                        }
                    }
                }
            default:
                System.out.println("error tried switching "+conTest.charAt(0));
        }
        System.out.println("Error at end of accept function");
        return -1000000L;
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
        public long xlb;
        public long xub;
        public long mub;
        public long mlb;
        public long aub;
        public long alb;
        public long slb;
        public long sub;
        public xmas(long x1, long x2, long m1, long m2, long a1, long a2, long s1, long s2){
            xlb=x1;
            xub=x2;
            mlb=m1;
            mub=m2;
            alb=a1;
            aub=a2;
            slb=s1;
            sub=s2;
        }
        
        public long xmasVal(){
            //System.out.println("adding "+(xub-xlb+1)*(mub-mlb+1)*(aub-alb+1)*(sub-slb+1));
            return (long)((xub-xlb+1)*(mub-mlb+1)*(aub-alb+1)*(sub-slb+1));
        }
    }
}
