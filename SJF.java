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
        print();
    }
    public void print() {
        double averageWaitingTime = 0, averageAroundTime = 0;

        System.out.println("Using the Non-preemptive Shortest-Job First (SJF)");
        for (Process proc : executionOrder)  {
            System.out.print(proc.name + "    ");
        }
        System.out.println();
        for (Process proc : executionOrder)  {
            System.out.print(proc.waitingTime + "     ");
            averageWaitingTime += proc.waitingTime;
        }
        System.out.println();
        for (Process proc : executionOrder)  {
            System.out.print(proc.turnAroundTime + "     ");
            averageAroundTime += proc.turnAroundTime;
        }
        System.out.println();
        averageWaitingTime /=  processes.length;
        averageAroundTime /= processes.length;
        System.out.println("Average waiting time = " + (averageWaitingTime));
        System.out.println("Average turnaround time = " + (averageAroundTime));
    }
}
