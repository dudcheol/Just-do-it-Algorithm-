package _unsolvedProblems;

import java.util.*;
/*
 * 20:30 시작 ~ 22:00 마무리 -- 틀림 // 1시간 30분
 * 다음날 23:00 시작 ~ 02:00 못품..
 * 다음날 18:30 시작 ~ 19:30 풀었따!
 * 
 * 20200126 시도
 * 시작 : 10:50
 * 끝 : 
 * 시간 : 
 */

public class programmers_courses30_lessons43164 {
	private static ArrayList<String> answerL;

	private static class Ticket {
		String start;
		String dest;
		boolean visited;

		Ticket(String[] ticket) {
			this.start = ticket[0];
			this.dest = ticket[1];
			this.visited = false;
		}
	}

	private static void dfs(ArrayList<Ticket> tl, Ticket now) {
		if (now.visited) {
			return;
		}
		// 현재 티켓을 방문처리함
		now.visited = true;
		answerL.add(now.dest);

		for (int i = 0; i < tl.size(); i++) {
			if (tl.get(i).visited) {
				continue;
			}
			// 현재 티켓의 도착지와 다음 티켓의 출발지가 같은 티켓을 dfs함
			if (now.dest.equals(tl.get(i).start)) {
				dfs(tl, tl.get(i));
			}
		}

		// 주어진 항공권은 모두 사용해야 합니다.
		for (Ticket t : tl) {
			if (!t.visited) { // 티켓중 방문하지 않은 곳이 하나라도 있다면
				answerL.remove(now.dest); // 정답에서 해당 티켓을 지우고
				now.visited = false; // 티켓리스트에서 해당 티켓을 비방문 처리 한다
				return;
			}
		}
		// 티켓중 방문하지 않은 곳이 없다면
		return;
	}

	public static String[] solution(String[][] tickets) {
		answerL = new ArrayList<>();
		int tSize = tickets.length;
		ArrayList<Ticket> tl = new ArrayList<>();
		
		String[][] sorted;
		sorted = Arrays.stream(tickets)
				.map(v -> v[0] + "," + v[1])
				.distinct()
				.sorted()
				.map(v -> v.split(","))
				.toArray(String[][]::new);

		// tickets를 Ticket에 담는다
		for (int i = 0; i < sorted.length; i++) {
			tl.add(new Ticket(sorted[i]));
		}

		// 만일 가능한 경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로를 return 합니다.
//		Collections.sort(tl, new Comparator<Ticket>() {
//			@Override
//			public int compare(Ticket o1, Ticket o2) {
//				if (o1.start.equals(o2.start)) {
//					return o1.dest.compareTo(o2.dest);
//				} else {
//					return 0;
//				}
//			}
//		});

		// 첫 출발지 찾기
		// 항상 ICN 공항에서 출발합니다.
		answerL.add("ICN");
		for (int i = 0; i < tl.size(); i++) {
			if (tl.get(i).start.equals("ICN")) {
				dfs(tl, tl.get(i));
			}
		}

		return answerL.toArray(new String[answerL.size()]);
	}

	public static void main(String[] args) {
//		String[][] tickets = { { "ICN", "JFK" }, { "HND", "IAD" }, { "JFK", "HND" } };
//		String[][] tickets = { { "ICN", "JFK" }, { "JFK", "HND" } };
//		String[][] tickets = { { "ICN", "SFO" }, { "ICN", "ATL" }, { "SFO", "ATL" }, { "ATL", "ICN" },
//				{ "ATL", "SFO" } };
//		String[][] tickets = { { "COO", "ICN" },{ "COO", "ICN" }, { "ICN", "COO" }, { "ICN", "COO" }, { "ICN", "BOO" }, { "BOO", "DOO" } };
		String[][] tickets = { { "COO", "ICN" }, { "COO", "ICN" }, { "ICN", "COO" } };

		for (String sol : solution(tickets)) {
			System.out.println(sol);
		}
	}
}
