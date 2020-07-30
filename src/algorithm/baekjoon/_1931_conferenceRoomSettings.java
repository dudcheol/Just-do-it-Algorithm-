package algorithm.baekjoon;

/*
 * 백준 - 그리디 - 1931 - 회의실배정
 * 시작: 11:45
 * 끝: 14:07
 * 시간: 2시간 22분
 * [배운 것]
 * - sort 쓸 때, compare사용하면 좋다. 그냥은 오름차순, -붙이면 내림차순 된다
 * - Comparator쓸 때, 만약 특정 클래스에 대해서 정렬하고 싶다면 해당 클래스를 implements Comparable해서 써도됨
 * 
 * [주의해야할 것]
 * - 값에 의한 호출이 아닌 "참조에 의한 호출" 반드시 조심하자!! 이런 실수 때문에 시간 너무 많이 잡아먹는다.
 * 	 참조에 의해 호출해버리면 해당 값을 변경할 때 참조한 곳까지 값이 변경되므로 나중에 발견하기 힘든 에러가 될 수 있다.
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
		
/*		실수해서 1시간 넘게 잡아먹은 부분
 * 
		// 가장 빨리 끝나는 회의 찾기
		Meeting fe = m.get(0);
		for (int i = 1; i < n; i++) {
			if (fe.endTime > m.get(i).endTime) {
				fe = m.get(i); // <- fe는 현재 m.get(i)의 값을 저장한게 아니라 참조하고 있는 것이다
							   //	 따라서, fe의 값을 바꾸면 m.get(i)의 값도 함께 바뀐다.
			}
		}
		System.out.println("가장빨리끝나는회의는:" + fe.endTime);
*
*/

		// 빨리 끝나는 순서로 정렬하되, 그 안에는 시작시간에 따른 오름차순으로 정렬
		Collections.sort(m, new Comparator<Meeting>() {
			@Override
			public int compare(Meeting o1, Meeting o2) {
				if(o1.endTime == o2.endTime) {
					return Integer.compare(o1.startTime, o2.startTime);
				} else {
					return Integer.compare(o1.endTime, o2.endTime);
				}
			}
		});
		
		int answer=0;
		int endTime = -1;
		for(int i=0;i<m.size();i++) {
			if(endTime <= m.get(i).startTime) {
				endTime = m.get(i).endTime;
				answer++;
			}
		}

		System.out.println(answer);
	}
}
