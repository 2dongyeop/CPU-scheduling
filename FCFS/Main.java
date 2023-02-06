package FCFS;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(final String... args) {

        //COMMENT 예제 삽입
        Process p1 = new Process("p1", 24);
        Process p2 = new Process("p2", 3);
        Process p3 = new Process("p3", 3);

        //COMMENT 솔루션
        fcfs(p1, p2, p3);
    }

    private static void fcfs(final Process... processes) {
        int waitingTime = 0;
        List<Process> processList = new ArrayList<>();

        //COMMENT 인덱스를 이용한 빠른 접근을 위해 배열리스트로 전환
        for (Process p : processes) {
            processList.add(p);
        }

        //COMMENT 인덱스 0번은 대기시간이 없으므로 1번부터 반복
        for (int i = 1; i < processList.size(); i++) {

            //COMMENT j번 프로세스의 대기 시간 : 0 ~ j-1 번 프로세스의 버스트타임의 합
            for (int j = 0; j < i; j++) {
                processList.get(i).addWaitingTime(processList.get(j).getBurstTime());
            }
            System.out.println(processList.get(i).toString());
        }

        for (Process p : processes) {
            waitingTime += p.getWaitingTime();
        }

        int avgWaitingTime = waitingTime / processes.length;

        System.out.println("평균 대기 시간은 " + avgWaitingTime + "밀리 초입니다.");
    }
}
