package Day20;

import java.util.List;

public class FlipFlop extends Module{
    public boolean state;

    public FlipFlop(String name, List<String> destinations) {
        super(name,destinations);
        state = false;
    }

    @Override
    public void RecivePulse(String sender, boolean pulse) {
        if(!pulse){
            state = !state;
            for(String m : destinations) Day20.queue.add(new Pulse (name, state, m));
        }
    }
}
