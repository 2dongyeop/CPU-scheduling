package SJF;

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

    public int getWaitingTime() {
        return waitingTime;
    }

    public void addWaitingTime(int waitingTime) {
        this.waitingTime += waitingTime;
    }

    @Override
    public String toString() {
        return "프로세스 " + name + "의 남은 시간 : " + burstTime
                + " , 누적 대기 시간 : " + waitingTime;
    }
}
