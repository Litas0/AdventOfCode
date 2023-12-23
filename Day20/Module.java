package Day20;

import java.util.List;

public class Module {
    public String name;
    protected List<String> destinations;

    public Module(String name, List<String> destinations) {
        this.name = name;
        this.destinations = destinations;
    }

    public void SendPulse(boolean pulse) {
        for (String m : destinations) Day20.queue.add(new Pulse (name,pulse,m));
        Day20.SendPulse();
    }
    public void RecivePulse(String sender, boolean pulse) {
    }

    public List<String> Getdestinations() {
        return destinations;
    }
    public void SetDestination(List<String> destinations) {
        this.destinations = destinations;
    }
}

