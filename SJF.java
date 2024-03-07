import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SJF extends ShortestProcessSystem {
    ArrayList<Process> executionOrder;
    public SJF(Process[] processes, int contextSwitchingTime) {
        super(processes, contextSwitchingTime);
        executionOrder = new ArrayList<>();
    }
    @Override
    public void run() {
        Arrays.sort(processes);
        ArrayList<Process> remaining = new ArrayList<>();
        Collections.addAll(remaining, processes);

        int curTime = 0;
        for (int i = 0; i < processes.length; i++) {
            Process shortestProcess = findShortestProcess(remaining, curTime);
            executionOrder.add(shortestProcess);

            shortestProcess.completionTime = curTime + shortestProcess.burstTime + contextSwitchingTime;

            shortestProcess.waitingTime = shortestProcess.completionTime
                    - shortestProcess.arrivalTime - shortestProcess.burstTime;
            shortestProcess.turnAroundTime = shortestProcess.waitingTime + shortestProcess.burstTime;

            remaining.remove(shortestProcess);
            curTime = shortestProcess.completionTime;
        }
        print(executionOrder, processes);
    }
}
