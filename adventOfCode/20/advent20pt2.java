import java.io.*;
import java.util.*;
public class advent20pt2 {
    public static void main(String[] args) throws IOException {
        int lo = 0, hi = 0;

        File file = new File("C:\\Users\\18438\\Desktop\\adventDay20.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        int counter = 0;
        ArrayList<Module> nums = new ArrayList<>();
        ArrayList<String> broadcastTargets = new ArrayList<>();

        while ((s = br.readLine()) != null) {
            String[] parts = s.split(" -> ");
            String left = parts[0];
            String right = parts[1];

            String[] outputs = right.split(", ");
            if (left.equals("broadcaster")) {
                broadcastTargets = new ArrayList<>(Arrays.asList(outputs));
            } else {
                char type = left.charAt(0);
                String name = left.substring(1);
                ArrayList<String> outputList = new ArrayList<>(Arrays.asList(outputs));
                Module module = new Module(name, type, outputList);
                nums.add(module);
            }
        }

        for (Module module : nums) {
            for (String output : module.outputs) {
                for (Module existingModule : nums) {
                    if (existingModule.name.equals(output) && existingModule.type == '&') {
                        existingModule.memory.put(module.name, "lo");
                    }
                }
            }
        }

        for (int i = 0; i < 6000; i++) {
            lo += 1;
            Deque<Move> q = new ArrayDeque<>();
            for (String target : broadcastTargets) {
                q.add(new Move(false, target, "lo"));
            }

            while (!q.isEmpty()) {
                Move curr = q.pollFirst();
                String origin = curr.current;
                String target = curr.to;
                String pulse = curr.sent ? "hi" : "lo";
                if (pulse.equals("hi")&&(origin.equals("ln"))){
                    System.out.println(origin+" "+(i+1));
                }
                if (pulse.equals("hi")&&(origin.equals("db"))){
                    System.out.println(origin+" "+(i+1));
                }
                if (pulse.equals("hi")&&(origin.equals("vq"))){
                    System.out.println(origin+" "+(1+i));
                }
                if (pulse.equals("hi")&&(origin.equals("tf"))){
                    System.out.println(origin+" "+(i+1));
                }
                if (pulse.equals("lo")) {
                    lo += 1;
                } else {
                    hi += 1;
                }

                boolean foundTarget = false;
                for (Module module : nums) {
                    if (module.name.equals(target)) {
                        foundTarget = true;
                        if (module.type == '%') {
                            if (pulse.equals("lo")) {
                                module.memory.put("off", module.memory.get("off").equals("off") ? "on" : "off");
                                String outgoing = module.memory.get("off").equals("on") ? "hi" : "lo";
                                for (String output : module.outputs) {
                                    q.add(new Move(outgoing.equals("hi"), output, module.name));
                                }
                                
                            }
                        } else {
                            module.memory.put(origin, pulse);
                            boolean allHigh = true;
                            for (String val : module.memory.values()) {
                                if (!val.equals("hi")) {
                                    allHigh = false;
                                    break;
                                }
                            }
                            String outgoing = allHigh ? "lo" : "hi";
                            for (String output : module.outputs) {
                                q.add(new Move(outgoing.equals("hi"), output, module.name));
                            }
                            
                        }
                        break;
                    }
                }

                if (!foundTarget) {
                    continue;
                }
            }
        }

        //System.out.println(lo * hi);
    }
    static class Module {
        public String name;
        public char type;
        public ArrayList<String> outputs;
        public HashMap<String, String> memory;
    
        public Module(String name, char type, ArrayList<String> outputs) {
            this.name = name;
            this.type = type;
            this.outputs = outputs;
            this.memory = new HashMap<>();
    
            if (type == '%') {
                this.memory.put("off", "off");
            }
        }
    
        public String toString() {
            return this.name + "{type=" + this.type + ",outputs=" + String.join(",", this.outputs) +
                    ",memory=" + this.memory.toString() + "}";
        }
    }
    
    static class Move {
        public boolean sent;
        public String to;
        public String current;
    
        public Move(boolean sent, String to, String current) {
            this.sent = sent;
            this.to = to;
            this.current = current;
        }
    }
}
