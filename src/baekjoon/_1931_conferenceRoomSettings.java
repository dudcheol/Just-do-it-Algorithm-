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

		Meeting(int s, int e) {
			this.startTime = s;
			this.endTime = e;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		ArrayList<Meeting> m = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			m.add(new Meeting(sc.nextInt(), sc.nextInt()));
		}

		// 가장 빨리 끝나는 회의 찾기
		Meeting fe = m.get(0);
		for (int i = 1; i < n; i++) {
			if (fe.endTime > m.get(i).endTime) {
				fe = m.get(i);
			}
		}
		System.out.println("가장빨리끝나는회의는:" + fe.startTime+","+fe.endTime);
		m.remove(fe);

		// fe가 끝나는 시간에 시작하는 회의 중 가장 빨리 끝나는 시간 찾기
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
			// 가능한애들 중에 회의시간이 가장 짧은애 선택이 아니고!!!!!!!!!!!!!!
			// 가장 빨리 끝나는  회의를 선택한다!!!!!!!!!!!!!!!!!!!!!!!!!!!
			if(can.size()==0) break;
			Meeting _fe = can.get(0);
			for (int i = 1; i < can.size(); i++) {
				if (_fe.endTime > can.get(i).endTime) {
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

		System.out.println(answer);
	}
}
