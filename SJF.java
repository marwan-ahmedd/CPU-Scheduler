import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SJF extends CPUSystem {
    private final Process[] processes;
    private final int contextSwitchingTime;
    ArrayList<Process> executionOrder;
    public SJF(Process[] processes, int contextSwitchingTime) {
        this.processes = processes;
        this.contextSwitchingTime = contextSwitchingTime;
        executionOrder = new ArrayList<>();
    }
    @Override
    public void run() {
        Arrays.sort(processes);
        ArrayList<Process> remaining = new ArrayList<>();
        Collections.addAll(remaining, processes);

        int curTime = 0;
        while (executionOrder.size() < processes.length) {
            Process shortestProcess = findShortestProcess(remaining, curTime);
            if (shortestProcess != null) {
                executionOrder.add(shortestProcess);

                shortestProcess.completionTime = curTime + shortestProcess.burstTime + contextSwitchingTime;

                shortestProcess.turnAroundTime = shortestProcess.completionTime - shortestProcess.arrivalTime;
                shortestProcess.waitingTime = shortestProcess.turnAroundTime - shortestProcess.burstTime;

                remaining.remove(shortestProcess);
                curTime = shortestProcess.completionTime;
            } else {
                curTime++;
            }
        }
        System.out.println("Using the Non-preemptive Shortest-Job First (SJF)");
        print(executionOrder, processes);
    }
}
