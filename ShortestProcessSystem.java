import java.util.ArrayList;

public abstract class ShortestProcessSystem extends CPUSystem {
    public ShortestProcessSystem(Process[] processes, int contextSwitchingTime) {
        super(processes, contextSwitchingTime);
    }

    protected final Process findShortestProcess(ArrayList<Process> processes, int currentTime) {
        Process shortest = null;
        int minStarvationTime = Integer.MAX_VALUE;
        for (Process process : processes) {
            if (process.arrivalTime <= currentTime && process.priority < minStarvationTime) {
                shortest = process;
                minStarvationTime = process.priority;
            }
        }
        return shortest;
    }
}
