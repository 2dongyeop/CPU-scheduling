# CPU-scheduling
CPU 스케줄링을 순수 자바 코드로 구현해봅니다.

<br/>

<br/>

## 💡 선입 선처리 스케줄링 (`FCFS`: First come, First served Scheduling)

- 가장 간단한 CPU 스케줄링 알고리즘은 **선입 선처리(FCFS) 스케줄링 알고리즘**이다.
    - CPU를 먼저 요청하는 프로세스가 CPU를 먼저 할당받는다.
    - 이 정책의 구현은 FIFO 큐로 쉽게 관리할 수 있다!
    - FCFS 스케줄링은 ***비선점형***이라는 것을 기억하자!

<br/>

> 💡 추가로 동적 상황에서 선입 선처리 스케줄링의 성능을 고려해보자!
> 1. CPU 중심 프로세스가 CPU를 점유하는 동안, 다른 프로세스들은 준비 큐에서 대기한다.
> 2. 자신의 작업을 끝낸 프로세스는 I/O 장치로 이동한다.
> 3. 모든 I/O 중심 프로세스들은 매우 짧은 CPU 버스트를 가지기에 신속히 끝낸다.
> 4. 이후, 준비 큐에서 새로운 CPU 중심 프로세스가 긴 작업을 진행하는 동안 수많은 I/O 프로세스들은 대기한다.
>
> 
> → 이처럼 **모든 다른 프로세스들이 하나의 긴 프로세스가 CPU를 양도하기를 기다리는 것**을 **호위 효과**라고 한다.

<br/>

<br/>

## 💡 SJF 스케줄링

- 최단 작업 우선(SJF) 알고리즘은 각 프로세스에 CPU 버스트 길이를 연관시킨다.
    - CPU가 이용 가능해지면, **가장 작은 CPU 버스트를 가진 프로세스에 할당**한다.

<br/>

- SJF 알고리즘은 선점형이거나 비선점형일 수 있다.
    - **선점형** : 현재 실행하는 프로세스보다 새로운 프로세스의 버스트가 짧으면 프로세스를 선점
    - **비선점형** : 현재 실행하는 프로세스가 자신의 CPU 버스트를 끝내도록 허용
    - SJF 알고리즘은 때때로 **최소 잔여 시간 우선**(`shortest remaining time first`) 스케줄링이라 부른다.

<br/>

- SJF 스케줄링 알고리즘은 주어진 프로세스 집합에 대해 최소 평균대기 시간을 줄인다. → ***최적임을 증명!***
    - 짧은 프로세스를 긴 프로세스의 앞으로 이동할 수록 평균 대기 시간을 줄일 수 있다.
    - **다만, 다음 CPU 버스트의 길이를 알 방법이 없기 때문에 구현할 수 없다.**

<br/>

<br/>