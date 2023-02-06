package MULTILEVEL;

public class Process {
    private final String name;
    private int remainBurstTime;
    private final int priority;

    private int waitingTime = 0;

    public Process(String name, int remainBurstTime, int priority) {
        this.name = name;
        this.remainBurstTime = remainBurstTime;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public int getRemainBurstTime() {
        return remainBurstTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public int getPriority() {
        return priority;
    }

    public void addWaitingTime(int time) {
        this.waitingTime += time;
    }

    @Override
    public String toString() {
        return "프로세스 " + name + "의 남은 시간 : " + remainBurstTime
                + " , 누적 대기 시간 : " + waitingTime;
    }
}
