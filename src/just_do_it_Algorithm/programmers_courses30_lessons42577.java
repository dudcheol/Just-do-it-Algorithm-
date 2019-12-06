package just_do_it_Algorithm;

import java.util.ArrayList;
import java.util.Arrays;

public class programmers_courses30_lessons42577 {
	public static void main(String[] args) {
		String[] phone_book = { "119", "1219", "97674223", "13195524421" ,"9767"};

		System.out.println(solution(phone_book));
	}

	public static boolean solution(String[] phone_book) {
		boolean answer = true;
		
		if(phone_book.length==1) {
			return true;
		}

		// compareNumSize : 가장 짧은 번호의 길이
		String compareNum = phone_book[0];
		int compareNumSize = compareNum.length();
		for (String num : phone_book) {
			if (compareNum.length() > num.length()) {
				compareNumSize = num.length();
			}
		}

		// compareNumSize와 길이가 같은 번호 찾아서 compareNums 리스트에 넣기
		ArrayList<String> compareNums = new ArrayList<>();
		for (String num : phone_book) {
			if (num.length() == compareNumSize) {
				compareNums.add(num);
			}
		}
		
		// phone_book에 모든 번호 compareNumSize길이 만큼 자르기
		for (int i=0;i<phone_book.length;i++) {
			phone_book[i] = phone_book[i].substring(0, compareNumSize);
		}
		
		// compareNums 리스트에서 하나씩 꺼내서 비교
		// sameCnt : 1초과면 같은 번호 존재하므로 무조건 true
		for(int i=0;i<compareNums.size();i++) {
			int sameCnt=0;
			for(int j=0; j<phone_book.length;j++) {
				if(compareNums.get(i) != null && compareNums.get(i).equals(phone_book[j])) {
					sameCnt++;
					if(sameCnt>1) {
						answer = false;
						return answer;
					}
				}
			}
		}

		return answer;
	}
}
