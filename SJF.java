import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SJF implements CPUSystem {
    private final Process[] processes;
    private int contextSwitchingTime;
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
        print();
    }
    public static Process findShortestProcess(ArrayList<Process> processes, int curTime) {
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
    public void print() {
        double averageWaitingTime = 0, averageAroundTime = 0;
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
