import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Priority extends  PriorityProcessSystem {
    private final int aging_factor;
    ArrayList<Process> executionOrder;

    public Priority(Process[] processes, int contextSwitchingTime, int agingFactor) {
        super(processes, contextSwitchingTime);
        aging_factor = agingFactor;
        executionOrder = new ArrayList<>();
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
                highestPriorityProcess.completionTime = curTime + highestPriorityProcess.burstTime + contextSwitchingTime;
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
