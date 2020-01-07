package just_do_it_Algorithm;

import java.util.*;

/*
 * 20:30 시작
 * 방문하는 공항 경로를 배열에 담아 return 하도록 solution 함수를 작성
 * tickets의 각 행 [a, b]는 a 공항에서 b 공항으로 가는 항공권이 있다는 의미
 * - 모든 공항은 알파벳 대문자 3글자로 이루어집니다
 * - 주어진 항공권은 모두 사용
 * - 만일 가능한 경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로를 return 
 * 모든 도시를 방문할 수 없는 경우는 주어지지 않습니다
 */

public class programmers_courses30_lessons43164 {
	static class Ticket {
		String start;
		String dest;
		boolean visited;
		Ticket(String[] ticket) {
			this.start = ticket[0];
			this.dest = ticket[1];
			this.visited = false;
		}
	}

	static String[] solution(String[][] tickets) {
		String[] answer = {};
		int size = tickets.length;
		Ticket[] tAry = new Ticket[size];
		ArrayList<String> answerL = new ArrayList<>();
		Queue<Ticket> q = new LinkedList<>();
		Ticket bestWay = null; // 가능한 경로가 2개 이상일 경우 알파벳순서 앞서는 경로 채택
		Ticket target = null; // 다음 티켓

		// 티켓 객체로 티켓 새롭게 저장
		for (int i = 0; i < size; i++) {
			tAry[i] = new Ticket(tickets[i]);
			// 출발티켓 선정
			if (tAry[i].start.equals("ICN")) {
				if (target == null || tAry[i].dest.compareTo(target.dest) < 0) { // 초기화
					target = tAry[i];
				}
			}
		}
		
		Arrays.sort(tickets);
		System.out.println(tickets);
		
		target.visited = true;
		answerL.add(target.start);
		answerL.add(target.dest);
		while (true) {
			// 티켓을 큐에 모두 삽입
			for (int i = 0; i < size; i++) {
				if (!tAry[i].visited) // 이미 방문했으면 큐에 삽입하지 않음
					q.offer(tAry[i]);
			}

			if (q.isEmpty())
				return answerL.toArray(new String[answerL.size()]);

			while (!q.isEmpty()) {
				Ticket p = q.poll();
				// 만일 가능한 경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로 선택
				if (p.start.equals(target.dest)) { // 다음 출발지와 같을 경우
					if (bestWay == null)
						bestWay = p;
					else {
						if (bestWay.start.compareTo(p.start) < 0) { // 가능한경로 2개 이상인데 새로 뽑은 애가 알파벳순서가 더 앞에있다면
							bestWay = p;
						}
					}
				}
			}
			if(bestWay == null) {
				
			}
			answerL.add(bestWay.dest);
			bestWay.visited = true; // 방문한 곳으로 처리
			target = bestWay;
			bestWay = null;
		}
	}

	public static void main(String[] args) {
//		String[][] tickets = { { "ICN", "JFK" }, { "HND", "IAD" }, { "JFK", "HND" } };
//		String[][] tickets = { { "ICN", "JFK" }, { "JFK", "HND" } };
		String[][] tickets = { { "ICN", "SFO" }, { "ICN", "ATL" }, { "SFO", "ATL" }, { "ATL", "ICN" },{ "ATL", "SFO" } };
//		String[][] tickets = { { "ICN", "COO" }, { "ICN", "BOO" }, { "COO", "ICN" }, { "BOO", "DOO" } };

		for (String sol : solution(tickets)) {
			System.out.println(sol);
		}
	}
}
