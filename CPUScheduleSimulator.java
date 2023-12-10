import java.util.ArrayList;
public class CPUScheduleSimulator {
    private final ArrayList<CPUSystem> systems;

    public CPUScheduleSimulator() {
        systems = new ArrayList<>();
    }

    public void addSystem(CPUSystem sys) {
        systems.add(sys);
    }
    public void simulate() {
        for (CPUSystem sys : systems) {
            sys.run();
        }
    }
}
