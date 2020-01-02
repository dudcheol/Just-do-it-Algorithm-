package just_do_it_Algorithm;

import java.util.*;

/*
 * 23:00 시작! -> 1:10 못풀었음
 * 디스크문제
 * 작업의 요청부터 종료까지 걸린 시간의 평균을 가장 줄이는 방법으로 처리하면 평균이 얼마가 되는지 return 하도록 solution 함수를 작성해주세요. (단, 소수점 이하의 수는 버립니다)
 * 
 * jobs의 각 행은 하나의 작업에 대한 [작업이 요청되는 시점, 작업의 소요시간] 입니다.
 * 
 */

public class programmers_courses30_lessons42627 {
	static class Job {
		int reqTime;
		int progTime;
		int endTime;

		Job(int rT, int pT) {
			this.reqTime = rT; // 요청시점
			this.progTime = pT; // 작업하는데 걸리는 시간
		}

		int getRuntime() { // 요청부터 종료까지 걸린 시간
			return endTime - reqTime;
		}

		void setEndTime(int prevEndTime) {
			this.endTime = prevEndTime + progTime; // job이 끝난 시점
		}
	}

	static int solution(int[][] jobs) {
//		int answer = 0;
//		PriorityQueue<Job> pQ = new PriorityQueue<>(new Comparator<Job>() {
//			@Override
//			public int compare(Job o1, Job o2) {
//				if (o1.progTime > o2.progTime) {
//					return 1;
//				} else return -1;
//			}
//		});
//
//		// 시뮬레이션을 하되, 큐에 들어오는 시점마다 계산하는 방식 해보자
//		int time = 0;
//		int i = 1;
//		pQ.offer(new Job(jobs[0][0], jobs[0][1]));
//		pQ.peek().setEndTime(jobs[0][1]);
//		while (!pQ.isEmpty()) {
//			if (jobs[i][0] == time) {
//				pQ.offer(new Job(jobs[i][0], jobs[i][1]));
//				if(i < jobs.length-1) i++;
//			}
//			if (time ) { // 흐른 시간과 큐에있는 작업의 끝나는 시간이 일치하면
//				pQ.poll();
//			}
//
//			time++;
//		}
//
//		return answer;

//		큐에 넣으면서 비교해서 넣으려고 한 방법
		int answer = 0;
		PriorityQueue<Job> pQ = new PriorityQueue<>(new Comparator<Job>() {
			@Override
			public int compare(Job o1, Job o2) {
				if (o1.progTime > o2.progTime) {
					return 1;
				} else {
					return -1;
				}
			}
		});

		for (int i = 1; i < jobs.length; i++) {
			// 첫번째 작업은 무조건 진행되어야함
			// 따라서 두번째 작업부터 큐에 넣음
			if (pQ.isEmpty()) {
				pQ.offer(new Job(jobs[1][0], jobs[1][1]));
				continue;
			}
			pQ.offer(new Job(jobs[i][0], jobs[i][1]));
		}
		
		boolean firstTry = true;
		while (!pQ.isEmpty()) {
			if(firstTry) {
				Job firstJob = new Job(jobs[0][0],jobs[0][1]);
				firstJob.setEndTime(jobs[0][0]);
				pQ.peek().setEndTime(firstJob.endTime);
				firstTry=false;
				answer += firstJob.getRuntime();
				continue;
			}
			Job polled = pQ.poll();
			if (!pQ.isEmpty()) {
				pQ.peek().setEndTime(polled.endTime);
			}
			answer += polled.getRuntime();
		}

		return answer / jobs.length;
	}

	public static void main(String[] args) {
		int[][] jobs = { { 0, 3 }, { 2, 5 }, { 2, 6 } };
		System.out.println(solution(jobs));
	}
}
