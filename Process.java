public class Process implements Comparable<Process> {
    public String name;
    public int arrivalTime, burstTime, serviceTime, priority, completionTime, waitingTime, turnAroundTime;
    Process(String name, int arriveTime, int burstTime, int priority) {
        this.name = name;
        this.arrivalTime = arriveTime;
        this.burstTime = burstTime;
        this.priority = priority;
        serviceTime = completionTime = waitingTime = turnAroundTime = 0;
    }

    @Override
    public int compareTo(Process p) {
        return Integer.compare(this.arrivalTime, p.arrivalTime);
    }
}