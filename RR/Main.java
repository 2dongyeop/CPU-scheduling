package RR;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static final int TIME_SLICE = 4;

    public static void main(final String... args) {

        //COMMENT 예제 삽입
        Process p1 = new Process("p1", 24);
        Process p2 = new Process("p2", 3);
        Process p3 = new Process("p3", 3);

        //COMMENT 솔루션
        rr(p1, p2, p3);
    }

    private static void rr(final Process... processes) {
        //COMMENT 큐에 프로세스들 삽입
        Queue<Process> waitingQueue = new LinkedList<>();
        for (Process p : processes) {
            waitingQueue.add(p);
        }

        int preProcessRuntime = 0;
        String preProcessName = "";
        //COMMENT 대기 큐가 빌 때까지 = 모든 프로세스가 종료될 때까지
        while (!waitingQueue.isEmpty()) {
            Process temp = waitingQueue.poll();             //comment 이 temp가 마지막 프로세스일 경우 큐는 빈 상태

            if (!temp.getName().equals(preProcessName)) {
                if (!waitingQueue.isEmpty())                //comment 큐가 빈 상태가 아니라면
                    temp.addWaitingTime(preProcessRuntime); //comment 이전 프로세스의 런타임만큼 대기 시간 추가
                else                                        //comment 큐가 빈 상태라면
                    temp.addWaitingTime(preProcessRuntime - TIME_SLICE);
            }

            //COMMENT 남은 버스트 시간이 timeslice보다 크다면?
            if (temp.getRemainBurstTime() >= TIME_SLICE) {
                temp.reduceBurstTime(TIME_SLICE);  //comment 버스트 시간에서 타임 슬라이스만큼 차감
                preProcessName = temp.getName();   //comment 이전에 어느 프로세스가 실행했는지를 기록
                preProcessRuntime += TIME_SLICE;    //comment 타임 슬라이스만큼 실행함을 기록
                waitingQueue.add(temp);            //comment 버스트 시간이 남았으므로 큐에 다시 삽입

            } else { //COMMENT 남은 버스트 시간이 timeSlice보다 작으면? -> 실행 후 종료
                preProcessName = temp.getName();
                preProcessRuntime += temp.getRemainBurstTime(); //comment 남은 시간만큼 실행함을 기록
                temp.reduceBurstTime(temp.getRemainBurstTime());
            }

            System.out.println(temp.toString());
        }

        int waitingTime = 0;
        for (Process p : processes) {
            waitingTime += p.getWaitingTime();
        }

        double avgWaitingTime = (double) waitingTime / processes.length;

        System.out.println("평균 대기 시간은 " + avgWaitingTime + "밀리 초입니다.");
    }
}
