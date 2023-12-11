import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
public class SRTF extends CPUSystem {
    private final Process[] processes;
    private final int contextSwitchingTime;
    ArrayList<Process> executionOrder;
    public SRTF(Process[] processes, int contextSwitchingTime) {
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
        while (!remaining.isEmpty()) {
            Process shortestProcess = findShortestProcess(remaining, curTime);
            if (shortestProcess != null) {
                if (executionOrder.isEmpty() || executionOrder.get(executionOrder.size() - 1) != shortestProcess) {
                    executionOrder.add(shortestProcess);
                }
                shortestProcess.burstTime--;
                if (shortestProcess.burstTime == 0) {
                    shortestProcess.completionTime = curTime;
                    remaining.remove(shortestProcess);
                }
            }
            curTime++;
        }
    }
}
