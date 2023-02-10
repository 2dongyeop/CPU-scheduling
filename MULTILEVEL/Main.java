package MULTILEVEL;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static final int TIME_SLICE = 4;
    private static int preProcessRuntime = 0;

    public static void main(final String... args) {
        //COMMENT 예제 삽입
        Process p1 = new Process("p1", 10, 3);
        Process p2 = new Process("p2", 1, 1);
        Process p3 = new Process("p3", 2, 4);
        Process p4 = new Process("p4", 1, 5);
        Process p5 = new Process("p5", 3, 2);
        Process p6 = new Process("p6", 3, 3);
        Process p7 = new Process("p7", 3, 1);
        Process p8 = new Process("p8", 3, 4);
        Process[] processes = new Process[] {p1, p2, p3, p4, p5, p6, p7, p8};

        multilevel(processes);
    }

    private static void multilevel(Process[] processes) {
        //COMMENT init
        LinkedList<Process>[] multilevelQueue = new LinkedList[6]; //1부터 5까지
        multilevelQueue[1] = new LinkedList<>();
        multilevelQueue[2] = new LinkedList<>();
        multilevelQueue[3] = new LinkedList<>();
        multilevelQueue[4] = new LinkedList<>();
        multilevelQueue[5] = new LinkedList<>();

        //COMMENT 우선순위별로 별도의 레벨 큐로 할당
        for (Process p : processes) {
            switch (p.getPriority()) {
                case 1 : {
                    multilevelQueue[1].add(p); break;
                }
                case 2 : {
                    multilevelQueue[2].add(p); break;
                }
                case 3 : {
                    multilevelQueue[3].add(p); break;
                }
                case 4 : {
                    multilevelQueue[4].add(p); break;
                }
                case 5 : {
                    multilevelQueue[5].add(p); break;
                }
            }
        }

        //COMMENT 레벨 1부터 5까지 각 레벨 큐별로 (RR + 우선순위 스케줄링) 실행
        for (int i = 1; i < multilevelQueue.length; i++) {
            rr(multilevelQueue[i], preProcessRuntime);
        }

        int waitingTime = 0;
        for (int i = 1; i < multilevelQueue.length; i++) {
            for (int j = 0; j < multilevelQueue[i].size(); j++) {
                waitingTime += multilevelQueue[i].get(j).getWaitingTime();
            }
        }

        double avgWaitingTime = (double) waitingTime / processes.length;
        System.out.println("평균 대기 시간은 " + avgWaitingTime + "밀리 초입니다.");
    }

    private static void rr(final LinkedList<Process> processes, int preProcessRuntime) {
        //COMMENT 큐에 프로세스들 삽입
        Queue<Process> waitingQueue = processes;

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
        }
    }
}
