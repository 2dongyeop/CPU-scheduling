package RR;

public class Process {
    private final String name;
    private int remainBurstTime;

    private int waitingTime = 0;

    public Process(String name, int remainBurstTime) {
        this.name = name;
        this.remainBurstTime = remainBurstTime;
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

    public void addWaitingTime(int time) {
        this.waitingTime += time;
    }

    public void reduceBurstTime(int timeSlice) {
        this.remainBurstTime -= timeSlice;
    }

    @Override
    public String toString() {
        return "프로세스 " + name + "의 남은 시간 : " + remainBurstTime
                + " , 누적 대기 시간 : " + waitingTime;
    }
}
