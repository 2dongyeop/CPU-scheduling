package PRIORITY;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(final String... args) {

        //COMMENT 예제 삽입
        Process p1 = new Process("p1", 10, 3);
        Process p2 = new Process("p2", 1, 1);
        Process p3 = new Process("p3", 2, 4);
        Process p4 = new Process("p4", 1, 5);
        Process p5 = new Process("p5", 5, 2);

        priority(p1, p2, p3, p4, p5);
    }

    /**
     * 비선점 우선순위 스케줄링
     * @param processes
     */
    private static void priority(final Process... processes) {
        //COMMENT 우선순위를 오름차순으로 정렬 (1이 높고, 5가 낮음)
        Arrays.sort(processes, new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                return o1.getPriority() - o2.getPriority();
            }
        });

        int waitingTime = 0;
        int preProcessBurstTime = 0;
        for (Process p : processes) {
            p.addWaitingTime(preProcessBurstTime); //comment 이전 프로세스의 실행시간만큼 대기시간 증가
            preProcessBurstTime += p.getRemainBurstTime(); //comment 실행시간만큼 추가

            System.out.println(p.toString());
        }

        //COMMENT 평균 대기 시간 계산
        for (Process p : processes) {
            waitingTime += p.getWaitingTime();
        }

        double avgWaitingTime = (double) waitingTime / processes.length;

        System.out.println("평균 대기 시간은 " + avgWaitingTime + "밀리 초입니다.");
    }
}
