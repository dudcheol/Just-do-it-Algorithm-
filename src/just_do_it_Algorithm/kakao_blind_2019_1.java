package just_do_it_Algorithm;

import java.util.*;

/*
 * 프로그래머스 - 코딩테스트 연습 - 2019 KAKAO BLIND RECRUITMENT - 오픈채팅방
 * 시작 23:00
 * 종료 23:25
 * 걸린시간 25분
 * 
 * [발전하기 위한 한마디]
 * 문제 길이가 길 뿐이지 쉬운문제였음
 * 하지만 처음 읽고나서 바로 코딩옮겼을때 실수할뻔했으니 자만하지말고 문제가 원하는게 뭔지 파악하는 것 잊지말것
 * 
 */
//채팅방에 누군가 들어오면 다음 메시지가 출력된다.[닉네임]님이 들어왔습니다.
//채팅방에서 누군가 나가면 다음 메시지가 출력된다. [닉네임]님이 나갔습니다.
//채팅방에서 닉네임을 변경하는 방법
//채팅방을 나간 후, 새로운 닉네임으로 다시 들어간다.
//채팅방에서 닉네임을 변경한다.
// 기존에 채팅방에 출력되어 있던 메시지의 닉네임도 전부 변경
//중복 닉네임을 허용
public class kakao_blind_2019_1 {
	static String[] solution(String[] record) {
		String[] answer = {};
		ArrayList<String> answerL = new ArrayList<String>();
		HashMap<String, String> map = new HashMap<>();
		int size = record.length;
		String[][] rec = new String[size][3];
		for (int i = 0; i < size; i++) {
			rec[i] = record[i].split(" ");
		}

		// 매칭
		for (int i = 0; i < size; i++) {
			switch (rec[i][0]) {
			case "Enter":
				map.put(rec[i][1], rec[i][2]);
				break;
			case "Change":
				map.put(rec[i][1], rec[i][2]);
				break;
			}
		}
		
		// 메시지 출력
		for (int i = 0; i < size; i++) {
			switch (rec[i][0]) {
			case "Enter":
				answerL.add(map.get(rec[i][1])+"님이 들어왔습니다.");
				break;
			case "Leave":
				answerL.add(map.get(rec[i][1])+"님이 나갔습니다.");
				break;
			}
		}

		return answerL.toArray(new String[answerL.size()]);
	}

	public static void main(String[] args) {
		String[] record = { "Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo",
				"Change uid4567 Ryan" };

		for (String s : solution(record))
			System.out.println(s);
	}
}
