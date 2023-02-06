package MULTILEVEL;


import java.util.LinkedList;
import java.util.Queue;

public class Main {
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

        multilevel(p1, p2, p3, p4, p5, p6, p6, p7, p8);
    }

    private static void multilevel(Process... processes) {
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
                case 1 -> multilevelQueue[1].add(p);
                case 2 -> multilevelQueue[2].add(p);
                case 3 -> multilevelQueue[3].add(p);
                case 4 -> multilevelQueue[4].add(p);
                case 5 -> multilevelQueue[5].add(p);
            }
        }

        int preProcessRunTime = 0;
        for (int i = 1; i < multilevelQueue.length; i++) {
            for (int j = 0; j < multilevelQueue[i].size(); j++) {
                //comment 이전 프로세스들의 누적 실행 시간이 곧 이 프로세스의 대기 시간
                multilevelQueue[i].get(j).addWaitingTime(preProcessRunTime);

                //comment 누적 실행시간에 자신의 버스트타임만큼 추가
                preProcessRunTime += multilevelQueue[i].get(j).getRemainBurstTime();
            }
        }

        int waitingTime = 0;
        for (int i = 1; i < multilevelQueue.length; i++) {
            for (Process process : multilevelQueue[i]) {
                waitingTime += process.getWaitingTime();
            }
        }

        double avgWaitingTime = (double) waitingTime / processes.length;

        System.out.println("평균 대기 시간은 " + avgWaitingTime + "밀리 초입니다.");
    }
}
