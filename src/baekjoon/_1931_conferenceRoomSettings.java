package baekjoon;

/*
 * 백준 - 그리디 - 1931 - 회의실배정
 * 시작: 11:45
 * 끝:
 * 시간:
 */

import java.util.*;

public class _1931_conferenceRoomSettings {
	static class Meeting {
		int startTime;
		int endTime;
		int runningTime;

		Meeting(int s, int e) {
			this.startTime = s;
			this.endTime = e;
			this.runningTime = endTime - startTime;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		ArrayList<Meeting> m = new ArrayList<>();
//		Meeting[] m = new Meeting[n];
//		int[] st = new int[n];
//		int[] ed = new int[n];
		for (int i = 0; i < n; i++) {
//			st[i] = sc.nextInt();
//			ed[i] = sc.nextInt();
			m.add(new Meeting(sc.nextInt(), sc.nextInt()));
		}

		// 가장 빨리 끝나는 회의 찾기
		Meeting fe = m.get(0);
		for (int i = 1; i < n; i++) {
			if (fe.endTime > m.get(i).endTime) {
				fe = m.get(i);
			}
		}
		System.out.println("가장빨리끝나는회의는:" + fe.endTime);
		m.remove(fe);

		// fe와 가장 근접한 시간에 시작하는 회의를 찾되, 회의시간이 가장 짧은 회의가 더 우선순위
		// 우선, 회의시간이 짧은 순서로 정렬
		Collections.sort(m, new Comparator<Meeting>() {
			@Override
			public int compare(Meeting o1, Meeting o2) {
				if (o1.startTime > o2.startTime) {
					return 1;
				} else if (o1.startTime < o2.startTime) {
					return -1;
				} else {
					if (o1.runningTime > o2.runningTime) {
						return 1;
					} else if (o1.runningTime < o2.runningTime) {
						return -1;
					} else
						return 0;
				}
			}
		});

		int answer = 1;
		Meeting cur = fe;
		ArrayList<Meeting> can = new ArrayList<>();
		for (int k = 0; k < m.size(); k++) {
			for (int i = 0; i < m.size(); i++) {
				if (cur.endTime <= m.get(i).startTime) {
					// 가능한애들
					can.add(m.get(i));
					System.out.println("가능한애는 " + m.get(i).startTime + "," + m.get(i).endTime);
				}
			}
			// 가능한애들 중에 회의시간이 가장 짧은애 선택
			if(can.size()==0) break;
			Meeting _fe = can.get(0);
			for (int i = 1; i < can.size(); i++) {
				if (_fe.runningTime > can.get(i).runningTime) {
					_fe = can.get(i);
				}
			}
			System.out.println("선택:"+ _fe.startTime + "," + _fe.endTime);
			can.clear();
			m.remove(_fe);
			answer++;
			k=-1;
			cur=_fe;
			System.out.println("------------");
		}

//		cur=m.get(i);
//		answer++;
//		m.remove(cur);
//		i=-1;

		System.out.println(answer);
	}
}
