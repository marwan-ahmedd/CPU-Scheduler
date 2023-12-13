import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Priority extends CPUSystem {
    private final Process[] processes;
    private final int contextSwitching, aging_factor;
    ArrayList<Process> executionOrder;
    public Priority(Process[] processes, int contextSwitching, int aging_factor) {
        this.processes = processes.clone();
        this.contextSwitching = contextSwitching;
        this.aging_factor = aging_factor;
        executionOrder = new ArrayList<>();
    }
    public Process findHighestPriorityProcess(ArrayList<Process> processes, int curTime) {
        Process highest = null;
        int highestPriority = Integer.MAX_VALUE;
        for (Process process : processes) {
            if (process.arrivalTime <= curTime && process.priority <= highestPriority) {
                highestPriority = process.priority;
                highest = process;
            }
        }
        return highest;
    }

    @Override
    public void run() {
        Arrays.sort(processes);
        ArrayList<Process> remaining = new ArrayList<>();
        Collections.addAll(remaining, processes);

        int curTime = 0;
        while (!remaining.isEmpty()) {
            Process highestPriorityProcess = findHighestPriorityProcess(remaining, curTime);
            if (highestPriorityProcess != null) {
                executionOrder.add(highestPriorityProcess);
                highestPriorityProcess.completionTime = curTime + highestPriorityProcess.burstTime + contextSwitching;
                highestPriorityProcess.turnAroundTime = highestPriorityProcess.completionTime - highestPriorityProcess.arrivalTime;
                highestPriorityProcess.waitingTime = highestPriorityProcess.turnAroundTime - highestPriorityProcess.burstTime;
                remaining.remove(highestPriorityProcess);
                curTime = highestPriorityProcess.completionTime;

                for (Process process : processes) {
                    if (process != highestPriorityProcess && process.arrivalTime <= curTime) {
                        process.priority -= (curTime - process.arrivalTime) / aging_factor;
                    }
                }
            } else {
                curTime++;
            }
        }
        print(executionOrder, processes);
    }
}
