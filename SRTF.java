import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
public class SRTF extends CPUSystem {
    private final Process[] processes;
    private final int contextSwitchingTime, aging_factor;
    ArrayList<Process> executionOrder;
    public SRTF(Process[] processes, int contextSwitchingTime, int aging_factor) {
        this.processes = processes.clone();
        this.contextSwitchingTime = contextSwitchingTime;
        this.aging_factor = aging_factor;
        executionOrder = new ArrayList<>();
    }
    @Override
    public Process findShortestProcess(ArrayList<Process> processes, int curTime) {
        Process shortest = null;
        int minStarvationTime = Integer.MAX_VALUE;
        for (Process process : processes) {
            if (process.arrivalTime <= curTime && process.priority < minStarvationTime) {
                shortest = process;
                minStarvationTime = process.priority;
            }
        }
        return shortest;
    }

    @Override
    public void run() {
        Arrays.sort(processes);
        for (Process process : processes) {
            process.priority = process.burstTime;
        }

        ArrayList<Process> remaining = new ArrayList<>();
        Collections.addAll(remaining, processes);
        ArrayList<Process> original = new ArrayList<>();
        Collections.addAll(original, processes);

        int curTime = 0;
        while (!remaining.isEmpty()) {
            Process shortestProcess = findShortestProcess(remaining, curTime);
            if (shortestProcess != null) {
                for (Process process : processes) {
                    if (process != shortestProcess && process.arrivalTime <= curTime) {
                        process.priority -= ((curTime + 1 - process.arrivalTime) * aging_factor);
                    }
                }
                if (executionOrder.isEmpty() || executionOrder.get(executionOrder.size() - 1) != shortestProcess) {
                    executionOrder.add(shortestProcess);
                    shortestProcess.waitingTime += curTime - shortestProcess.arrivalTime - shortestProcess.serviceTime;
                    curTime += contextSwitchingTime;
                }
                shortestProcess.serviceTime++;
                if (shortestProcess.serviceTime == shortestProcess.burstTime) {
                    shortestProcess.completionTime = curTime - contextSwitchingTime + 1;
                    shortestProcess.turnAroundTime = shortestProcess.completionTime - shortestProcess.arrivalTime;
                    remaining.remove(shortestProcess);
                }
            }
            curTime++;
        }

        print(executionOrder, processes);
    }
}
