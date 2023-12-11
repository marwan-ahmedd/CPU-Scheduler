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
    public final void print(ArrayList<Process> executionOrder, int numProcesses) {
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
        averageWaitingTime /=  numProcesses;
        averageAroundTime /= numProcesses;
        System.out.println("Average waiting time = " + (averageWaitingTime));
        System.out.println("Average turnaround time = " + (averageAroundTime));
    }
}
