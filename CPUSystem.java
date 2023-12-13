import java.util.ArrayList;

public abstract class CPUSystem {
    public abstract void run() ;
    public Process findShortestProcess(ArrayList<Process> processes, int curTime) {
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
    public final void print(ArrayList<Process> executionOrder, Process[] processes) {
        double averageWaitingTime = 0, averageAroundTime = 0;

        System.out.print("Execution Order:  ");
        for (Process process : executionOrder)  {
            System.out.print(process.name + "  ");
        }
        System.out.println();

        System.out.println("Waiting Time for each processor");
        for (Process process : processes)  {
            System.out.print(process.name + "    ");
        }
        System.out.println();
        for (Process process : processes)  {
            System.out.print(process.waitingTime + "     ");
            averageWaitingTime += process.waitingTime;
        }
        System.out.println();
        System.out.println("Turnaround Time for each processor");
        for (Process process : processes)  {
            System.out.print(process.name + "    ");
        }
        System.out.println();
        for (Process process : processes)  {
            System.out.print(process.turnAroundTime + "     ");
            averageAroundTime += process.turnAroundTime;
        }
        System.out.println();
        averageWaitingTime /=  processes.length;
        averageAroundTime /= processes.length;
        System.out.println("Average waiting time = " + (averageWaitingTime));
        System.out.println("Average turnaround time = " + (averageAroundTime));
    }
}
