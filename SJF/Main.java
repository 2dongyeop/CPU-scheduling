package SJF;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

        //COMMENT 예제 삽입
        Process p1 = new Process("p1", 6);
        Process p2 = new Process("p2", 8);
        Process p3 = new Process("p3", 7);
        Process p4 = new Process("p4", 3);

        //COMMENT 솔루션
        nonpreferredSjf(p1, p2, p3, p4);
//        preOccupancySjf(p1, p2, p3, p4);
    }

    /**
     * 비선점형 SJF
     */
    private static void nonpreferredSjf(Process... processes) {
        //COMMENT 버스트 타임이 짧은 순(오름차순)으로 정렬
        Arrays.sort(processes, new Comparator<Process>() {
            @Override
            public int compare(Process p1, Process p2) {
                return p1.getBurstTime() - p2.getBurstTime();
            }
        });

        for (int i = 1; i < processes.length; i++) {
            for (int j = 0; j < i; j++) {
                processes[i].addWaitingTime(processes[j].getBurstTime());
            }
            System.out.println(processes[i].toString());
        }

        int waitingTime = 0;
        for (Process p : processes) {
            waitingTime += p.getWaitingTime();
        }

        int avgWaitingTime = waitingTime / processes.length;

        System.out.println("평균 대기 시간은 " + avgWaitingTime + "밀리 초입니다.");
    }

    /**
     * 선점형 SJF
     */
    private static void preOccupancySjf(Process... processes) {

    }
}
