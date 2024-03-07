import java.util.ArrayList;

public abstract class PriorityProcessSystem extends CPUSystem {

    public PriorityProcessSystem(Process[] processes, int contextSwitchingTime) {
        super(processes, contextSwitchingTime);
    }
    protected Process findHighestPriorityProcess(ArrayList<Process> processes, int curTime) {
        Process highest = null;
        int highestPriority = Integer.MAX_VALUE;
        for (Process process : processes) {
            if (process.arrivalTime <= curTime && process.priority <= highestPriority) {
                if (highestPriority == process.priority) {
                    assert highest != null;
                    highest = (highest.burstTime < process.burstTime ? highest : process);
                } else {
                    highestPriority = process.priority;
                    highest = process;
                }
            }
        }
        return highest;
    }
}
