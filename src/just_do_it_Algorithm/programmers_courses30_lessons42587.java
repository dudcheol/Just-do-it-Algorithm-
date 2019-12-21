package just_do_it_Algorithm;

import java.util.*;
/* 5:00 ~
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
	}

	static class DocPriorityComparator implements Comparator<Doc> {
		@Override
		public int compare(Doc o1, Doc o2) {
			if (o1.priority > o2.priority) {
				return -1;
			} else {
				return 1;
			}
		}
	}

	static class DocLocationComparator implements Comparator<Doc> {
		@Override
		public int compare(Doc o1, Doc o2) {
			if (o1.location > o2.location) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	static int solution(int[] priorities, int location) {
		LinkedList<Doc> q = new LinkedList<>();
		LinkedList<Doc> sq = new LinkedList<>();
		Doc myDoc = new Doc(priorities[location], location);
		int answer = 0;

		// 큐에 문서 삽입
		for (int i = 0; i < priorities.length; i++) {
			q.offer(new Doc(priorities[i], i));
		}

		// 큐에 있는 문서들을 우선순위로 정렬
		q.sort(new DocPriorityComparator());

		// 정렬된 큐에서 내 문서보다 우선순위가 높은 문서들을 출력(poll)하고
		// 출력된 문서보다 location이 낮았던 문서들의 location은 맨뒤로 (location += 큐의 크기+1)보냄
		while (!(q.peek().priority == myDoc.priority)) {
			Doc polled = q.poll();
			for (Doc d : q) {
				if (d.location < polled.location) {
					d.location += (q.size() + 1); 
				}
			}
			if(myDoc.location < polled.location) myDoc.location += (q.size() + 1);
			answer++;
		}

		// 내 문서와 우선순위가 같은 문서들만 sq에 넣음
		while (!q.isEmpty()) {
			if (q.peek().priority == myDoc.priority) {
				sq.offer(q.poll());
			} else
				break;
		}

		// sq에 있는 문서들을 location을 기준으로 오름차순 정렬
		sq.sort(new DocLocationComparator());
	
		// 내 문서가 몇번째인지 구한다
		while (!sq.isEmpty()) {
			if (sq.peek().location == myDoc.location) {
				answer++;
				break;
			} else {
				sq.poll();
				answer++;
			}
		}

		return answer;
	}

	public static void main(String[] args) {
		int[] priorities = { 1, 2, 3, 4, 1, 1, 1, 2, 2, 5};
		int location  = 5;

		System.out.println("----------");
		System.out.println(solution(priorities, location));
	}
}
