import java.util.ArrayList;

public abstract class CPUSystem {
    public abstract void run() ;
    public final Process findShortestProcess(ArrayList<Process> processes, int curTime) {
        Process shortest = null;
        int minBurstTime = Integer.MAX_VALUE;
        for (Process process : processes) {
            if (process.arrivalTime <= curTime && process.burstTime < minBurstTime) {
                shortest = process;
                minBurstTime = process.burstTime;
            }
        }
        return shortest;
    }
}
