package Day20;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Day20 {
    public static HashMap<String,Module> modules = new HashMap<String,Module>();
    public static Queue<Pulse> queue = new LinkedList<Pulse>();
    public static long lowPulses = 0;
    public static long highPulses = 0;
    public static long buttonClicks = 0;

    public static void SendPulse() {
        while(queue.peek() != null) {
            Pulse current = queue.poll();
            if (current.value) highPulses++;
            else lowPulses++;
            if (modules.containsKey(current.destination)){
                modules.get(current.destination).RecivePulse(current.sender ,current.value);
            }
            
        }
    }
    public static void UpdateConected()
    {
        for (Map.Entry<String, Module> module : modules.entrySet()) {
            if(module.getValue().getClass() == Conjunction.class) {
                List<String> connected = new ArrayList<String>();
                for(Map.Entry<String, Module> m : modules.entrySet()){                
                    if (m.getValue().destinations.contains(module.getValue().name)) connected.add(m.getValue().name);
                }
                setConnected((Conjunction)module.getValue(), connected);
            }
        }
    }
    public static void setConnected(Conjunction c, List<String> connected) {
        for (String module : connected)c.connected.put(module,false);
    }
    
    public static void main(String[] args) throws Exception {
        List<String> allLines = Files.readAllLines(Paths.get("Day20/input20.txt"));
        String br = "";
        for(String line : allLines){
            String[] split = line.split(" ");
            split[split.length - 1] += ",";
            if (split[0].charAt(0) == 'b') {
                br = split[0];
                List<String> destinations = new ArrayList<String>();
                for (int i = 2; i < split.length; i++) destinations.add(split[i].substring(0, split[i].length() - 1));
                modules.put(br, new Module(split[0], destinations));
            }
            else if (split[0].charAt(0) == '%'){
                List<String> destinations = new ArrayList<String>();
                for (int i = 2; i < split.length; i++) destinations.add(split[i].substring(0, split[i].length() - 1));
                modules.put(split[0].substring(1), new FlipFlop(split[0].substring(1), destinations));
            }
            else if (split[0].charAt(0) == '&'){
                List<String> destinations = new ArrayList<String>();
                for (int i = 2; i < split.length; i++) destinations.add(split[i].substring(0, split[i].length() - 1));
                modules.put(split[0].substring(1), new Conjunction(split[0].substring(1), destinations));
            }
        }

        UpdateConected();

        for (int i = 0; i < 4500; i++) {       
        buttonClicks++;
        lowPulses++;
        modules.get(br).SendPulse(false);
        }

        System.out.println(lowPulses * highPulses);
    }
}
