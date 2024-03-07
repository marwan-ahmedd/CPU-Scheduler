public class Main {
    public static void main(String[] args) {
        Process p1 = new Process("P1", 0, 1, 4);
        Process p2 = new Process("P2", 1, 4, 3);
        Process p3 = new Process("P3", 2, 7, 2);
        Process p4 = new Process("P4", 3, 5, 1);
        Process[] processes = {p1, p2, p3, p4};

        CPUScheduleSimulator simulator = new CPUScheduleSimulator();
        simulator.addSystem(new SJF(processes, 1));
        simulator.addSystem(new SRTF(processes, 1, 0));
        simulator.addSystem(new Priority(processes, 0, 1));
        simulator.simulate();
    }
}
