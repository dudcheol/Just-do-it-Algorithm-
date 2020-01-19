package programmers.stack_queue;

import java.util.*;
/* 1차 틀림 5:00 ~ 7:15
 * 2차 정답 7:16 ~ 8:02
 *  1. 인쇄 대기목록의 가장 앞에 있는 문서(J)를 대기목록에서 꺼냅니다.
	2. 나머지 인쇄 대기목록에서 J보다 중요도가 높은 문서가 한 개라도 존재하면 J를 대기목록의 가장 마지막에 넣습니다.
	3. 그렇지 않으면 J를 인쇄합니다.

 * 현재 대기목록에 있는 문서의 중요도가 순서대로 담긴 배열 priorities와 
 * 내가 인쇄를 요청한 문서가 현재 대기목록의 어떤 위치에 있는지를 알려주는 location이 매개변수로 주어질 때,
 *  내가 인쇄를 요청한 문서가 몇 번째로 인쇄되는지 return 하도록 solution 함수를 작성해주세요.
 *  
 *  <제한사항>
 *  인쇄 작업의 중요도는 1~9로 표현하며 숫자가 클수록 중요하다는 뜻입니다.
 *  location은 0부터 시작
 *  
 *  priorities	location	return
	[2, 1, 3, 2]		2	1
	[1, 1, 9, 1, 1, 1]	0	5
 */

public class programmers_courses30_lessons42587 {
	static class Doc {
		int priority;
		int location;

		Doc(int p, int l) {
			this.priority = p;
			this.location = l;
		}

		boolean compareP(Doc o2) {
			if (this.priority < o2.priority) {
				return true;
			} else
				return false;
		}
	}

	static int solution(int[] priorities, int location) {
		LinkedList<Doc> q = new LinkedList<>();
		int answer = 0;

		// 큐에 문서 삽입
		for (int i = 0; i < priorities.length; i++) {
			q.offer(new Doc(priorities[i], i));
		}

		// 인쇄 대기목록의 가장 앞에 있는 문서(J)를 대기목록에서 꺼냅니다.
		while (!q.isEmpty()) {
			Doc polled = q.poll();
			answer++;
			for (int i = 0; i < q.size(); i++) {
				// 큐에남아있는 문서들 중 큐에서 꺼낸 문서보다 우선순위가 높은 문서가 있다면
				// 꺼낸 문서를 다시 큐에 집어넣음
				if (polled.compareP(q.get(i))) {
					q.offer(polled);
					answer--;
					break;
				} else {
					// 꺼낸 문서가 나의 문서이고
					// 꺼낸 문서보다 우선순위가 큰 문서가 한번도 없었다면 큐를 모두 비운다
					if(i==q.size()-1 && polled.location == location) {
						q.clear();
					}
				}
			}
		}

		return answer;
	}

	public static void main(String[] args) {
		int[] priorities = { 1, 1, 9, 1, 1, 1 };
		int location = 0;

		System.out.println("----------");
		System.out.println(solution(priorities, location));
	}
}
