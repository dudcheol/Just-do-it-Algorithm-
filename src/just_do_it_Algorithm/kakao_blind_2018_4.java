package just_do_it_Algorithm;

import java.util.*;

/*
 * 시작: 18:10
 * 멈춤: 20:07
 * 끝:
 * 걸린시간:
 * 
 * 시간을 포맷화하는 방법
 * 
 */

public class kakao_blind_2018_4 {
	static class Time {
		int hour;
		int minute;

		Time(String timetable) {
			String hour = "";
			String minute = "";
			hour += timetable.charAt(0);
			hour += timetable.charAt(1);
			minute += timetable.charAt(3);
			minute += timetable.charAt(4);

			this.hour = Integer.parseInt(hour);
			this.minute = Integer.parseInt(minute);
		}

		void add(int t) {
			this.minute += t;
			if (this.minute >= 60) {
				this.minute = 60 - this.minute;
				this.hour++;
			}
		}

		void sub(int t) {
			if (this.minute == 0) {
				this.minute = 59;
				this.hour--;
			} else if (this.minute - t < 0) {
				this.minute = 60 + (this.minute - t);
				this.hour--;
			} else {
				this.minute -= t;
			}
		}

		// 양수면 this가 t보다 큼, 음수면 작음
		int compareTo(Time t) {
			if (this.hour > t.hour) {
				return 1;
			} else if (this.hour == t.hour) {
				if (this.minute >= t.minute) {
					return 1;
				} else
					return -1;
			} else {
				return -1;
			}
		}

		String convertString() {
			return String.format("%02d", this.hour) + ":" + String.format("%02d", this.minute);
		}
	}

	static String solution(int n, int t, int m, String[] timetable) {
		// timetable
		/*
		 * 셔틀은 09:00부터 총 n회 t분 간격으로 역에 도착하며, 하나의 셔틀에는 최대 m명의 승객이 탈 수 있다. 셔틀은 도착했을 때 도착한
		 * 순간에 대기열에 선 크루까지 포함해서 대기 순서대로 태우고 바로 출발한다. 예를 들어 09:00에 도착한 셔틀은 자리가 있다면 09:00에
		 * 줄을 선 크루도 탈 수 있다. 같은 시각에 도착한 크루 중 대기열에서 제일 뒤에 선다. 모든 크루는 잠을 자야 하므로 23:59에 집에
		 * 돌아간다 -> 어떤 크루도 다음날 셔틀을 타는 일은 없다.
		 */
		String answer = "";
		int tSize = timetable.length;
		Time[] times = new Time[tSize];
		for (int i = 0; i < tSize; i++) {
			times[i] = new Time(timetable[i]);
		}
		
		Arrays.sort(timetable);
		System.out.println(timetable);

		// 타임테이블 정렬
		Arrays.sort(times, new Comparator<Time>() {
			@Override
			public int compare(Time o1, Time o2) {
				if (o1.hour > o2.hour) {
					return 1;
				} else if (o1.hour == o2.hour) {
					if (o1.minute > o2.minute) {
						return 1;
					} else {
						return -1;
					}
				} else
					return -1;
			}
		});

		// 대기열 큐 생성
		Queue<Time> wq = new LinkedList<>();
		for (Time time : times) {
			wq.offer(time);
		}

		// 현재 셔틀버스에 타고있는 사람
		Stack<Time> cs = new Stack<>();

		// 시뮬레이션 시작
		boolean me = true; // 내가 탔으면 끝내도됨
		Time currentTime = new Time("09:00");
		Time ans = null;
		// 셔틀 운행 횟수 n, 셔틀 운행 간격 t, 한 셔틀에 탈 수 있는 최대 크루 수 m, 크루가 대기열에 도착하는 시각을 모은 배열
		while (me) {
			cs.clear();
			for (int i = 0; i < m; i++) {
				if (wq.isEmpty()) {
					break;
				}
				if (wq.peek().compareTo(currentTime) < 0) {
					if (cs.size() < m) {
						cs.push(wq.poll());
					}
				}
			}

			if (n == 1) { // 마지막 셔틀버스
				if (cs.isEmpty()) { // 크루들이 다 못탐
					ans = currentTime;
					break;
				}
				ans = cs.peek();
				if (ans.compareTo(new Time("23:59")) > 0) { // 23:59에 모두 집에 돌아감		
					break;
				}
				if (ans.compareTo(new Time("09:00")) < 0) {
					if (cs.size() == m) { // 크루들이 첫 차에 다 탐
						// 마지막 사람보다 1분 빨리오면됨
						ans.sub(1);
					} else { // 첫 차에 자리가 남음
						ans = new Time("09:00");
					}
				} else {
					if (cs.size() == m) {
						// 마지막 사람보다 1분 빨리오면됨
						ans.sub(1);
					}
				}
				break;
			}

			currentTime.add(t);
			n--;
		}

		return ans.convertString(); // 콘이 셔틀을 타고 사무실로 갈 수 있는 도착 시각 중 제일 늦은 시각을 구하여라.
	}

	public static void main(String[] args) {
//		int n = 1;
//		int t = 1;
//		int m = 5;
//		String[] timetable = { "08:00", "08:01", "08:02", "08:03" };

		int n = 2;
		int t = 10;
		int m = 2;
		String[] timetable = { "09:10", "09:09", "08:00" };

//		int n = 2;
//		int t = 1;
//		int m = 2;
//		String[] timetable = { "09:00", "09:00", "09:00", "09:00" };

//		int n = 16;
//		int t = 60;
//		int m = 16;
//		String[] timetable = { "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59",
//				"23:59", "23:59", "23:59", "23:59", "23:59", "23:59" };

		System.out.println(solution(n, t, m, timetable));
	}
}
