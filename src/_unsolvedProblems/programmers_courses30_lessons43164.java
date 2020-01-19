package _unsolvedProblems;

import java.util.*;

/*
 * 20:30 시작 ~ 22:00 마무리 -- 틀림 // 1시간 30분
 * 다음날 23:00 시작 ~ 02:00 못품..
 * 다음날 18:30 시작 ~ 19:30 풀었따!
 * 방문하는 공항 경로를 배열에 담아 return 하도록 solution 함수를 작성
 * tickets의 각 행 [a, b]는 a 공항에서 b 공항으로 가는 항공권이 있다는 의미
 * - 모든 공항은 알파벳 대문자 3글자로 이루어집니다
 * - 주어진 항공권은 모두 사용
 * - 만일 가능한 경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로를 return 
 * 모든 도시를 방문할 수 없는 경우는 주어지지 않습니다
 */

public class programmers_courses30_lessons43164 {
	static Ticket[] Ts;
	static ArrayList<String> answerL;

	static class Ticket {
		String start;
		String dest;
		boolean visited;
		ArrayList<Ticket> next;

		Ticket(String[] ticket) {
			this.start = ticket[0];
			this.dest = ticket[1];
			this.visited = false;
			this.next = new ArrayList<>();
		}

		void setNext(Ticket t) {
			this.next.add(t);
		}
	}

	static void dfs(Ticket now) {
		if (now.visited)
			return;
		for (int i = 0; i < now.next.size(); i++) {
			if (now.next.get(i).visited)
				continue;
			now.visited = true;
			dfs(now.next.get(i));
			for (int j = 0; j < Ts.length; j++) {
				if (!Ts[j].visited) {
					now.visited = false;
					return;
				}
			}
		}
		now.visited = true;
		answerL.add(now.dest);
	}

	static String[] solution(String[][] tickets) {
		String[] answer = new String[tickets.length + 1];
		int tSize = tickets.length;
		answerL = new ArrayList<>();
		Ts = new Ticket[tSize];

		for (int i = 0; i < tSize; i++) {
			Ts[i] = new Ticket(tickets[i]);
		}

		// 티켓을 문자열을 기준으로 오름차순 정렬
		Arrays.sort(Ts, new Comparator<Ticket>() {
			@Override
			public int compare(Ticket o1, Ticket o2) {
				if (o1.start.compareTo(o2.start) > 0)
					return 1;
				else if (o1.start.compareTo(o2.start) < 0)
					return -1;
				else {
					if (o1.dest.compareTo(o2.dest) >= 0)
						return 1;
					else
						return -1;
				}
			}
		});

		// 트리화하기
		for (int i = 0; i < tSize; i++) {
			for (int j = 0; j < tSize; j++) {
				if (Ts[i].dest == Ts[j].start) {
					Ts[i].setNext(Ts[j]);
				}
			}
		}

		// 첫번째 티켓을 찾고 그것으로 dfs실행
		for (Ticket t : Ts) {
			if (t.start.equals("ICN")) {
				dfs(t);
			}
		}

		// 답
		answer[0] = "ICN";
		for (int i = 1; i <= answerL.size(); i++) {
			answer[i] = answerL.get(answerL.size() - i);
		}

		return answer;
	}

	public static void main(String[] args) {
		String[][] tickets = { { "ICN", "JFK" }, { "HND", "IAD" }, { "JFK", "HND" } };
//		String[][] tickets = { { "ICN", "JFK" }, { "JFK", "HND" } };
//		String[][] tickets = { { "ICN", "SFO" }, { "ICN", "ATL" }, { "SFO", "ATL" }, { "ATL", "ICN" },
//				{ "ATL", "SFO" } };
//		String[][] tickets = { { "ICN", "COO" }, { "ICN", "BOO" }, { "COO", "ICN" }, { "BOO", "DOO" } };

		for (String sol : solution(tickets)) {
			System.out.println(sol);
		}
	}
}
