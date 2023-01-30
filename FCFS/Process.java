package FCFS;

public class Process {
    private final String name;
    private final int burstTime;

    private int waitingTime = 0;

    public Process(String name, int burstTime) {
        this.name = name;
        this.burstTime = burstTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void addWaitingTime(int waitingTime) {
        this.waitingTime += waitingTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }
}
