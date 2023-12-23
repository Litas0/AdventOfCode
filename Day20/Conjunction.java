package Day20;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Conjunction extends Module{
    public HashMap<String,Boolean> connected;

    public Conjunction(String name, List<String> destinations) {
        super(name, destinations);
        connected = new HashMap<String,Boolean>();
    }

    @Override
    public void RecivePulse(String sender, boolean pulse) {
        connected.replace(sender, pulse);
        boolean allStates = true;
        for (Map.Entry<String, Boolean> pair : connected.entrySet()) if (!pair.getValue()) allStates = false;
        for (String m : destinations) Day20.queue.add(new Pulse(name, !allStates, m));
    }

}
