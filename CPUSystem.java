import java.util.ArrayList;

public abstract class CPUSystem {
    protected final Process[] processes;
    protected final int contextSwitchingTime;

    public CPUSystem(Process[] processes, int contextSwitchingTime) {
        this.processes = processes;
        this.contextSwitchingTime = contextSwitchingTime;
    }

    public abstract void run();

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
        System.out.println("----------------------------------------");
    }
}
