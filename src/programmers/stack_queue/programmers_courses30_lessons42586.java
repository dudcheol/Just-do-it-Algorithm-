package programmers.stack_queue;

import java.util.*;

/* 2019 12 21 16:00 ~ 16:40
 * 먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 정수 배열 progresses와 
 * 각 작업의 개발 속도가 적힌 정수 배열 speeds가 주어질 때 
 * 각 배포마다 몇 개의 기능이 배포되는지를 return 하도록 solution 함수를 완성하세요.
 * 
 * progresses	speeds	return
	[93,30,55]	[1,30,5]	[2,1]
 */

public class programmers_courses30_lessons42586 {
	static class Work {
		int progress;
		int speed;

		Work(int p, int s) {
			this.progress = p;
			this.speed = s;
		}
	}

	public static int[] solution(int[] progresses, int[] speeds) {
		ArrayList<Integer> ansList = new ArrayList<>();
		int completedWork = 0;
		Queue<Work> q = new LinkedList<Work>();

		while (true) {
			// 일이 시작됨 -> 그날 한 만큼의 작업진도를 큐에 넣음
			if (q.isEmpty()) {
				for (int i = 0; i < progresses.length; i++) {
					q.add(new Work(progresses[i], speeds[i]));
				}
				continue;
			}

			// 다음날 저녁이 되고, 작업진도를 늘림
			for (Work work : q) {
				work.progress += work.speed;
			}

			// 진도를 검사해서 완료된 것을 배포함
			if (q.peek().progress >= 100) {
				q.poll();
				completedWork++;
				while (!q.isEmpty()) {
					if (q.peek().progress >= 100) {
						q.poll();
						completedWork++;
					} else
						break;
				}
			} else
				continue;

			ansList.add(completedWork);
			completedWork = 0;
			if (q.isEmpty())
				break;
		}

		int[] answer = new int[ansList.size()];
		for (int i = 0; i < ansList.size(); i++) {
			answer[i] = ansList.get(i);
		}

		return answer;
	}

	public static void main(String[] args) {
		int[] progresses = { 93, 30, 55 };
		int[] speeds = { 1, 30, 5 };

		int[] answers = solution(progresses, speeds);
		for (int answer : answers) {
			System.out.println(answer);
		}
	}
}
