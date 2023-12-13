public class Main {
    public static void main(String[] args) {
        Process p1 = new Process("P1", 0, 8, -1);
        Process p2 = new Process("P2", 1, 4, -1);
        Process p3 = new Process("P3", 2, 9, -1);
        Process p4 = new Process("P4", 3, 5, -1);
        Process[] processes = {p1, p3, p2};

        CPUScheduleSimulator simulator = new CPUScheduleSimulator();
        simulator.addSystem(new SRTF(processes, 0, 1));
        simulator.simulate();
    }
}
