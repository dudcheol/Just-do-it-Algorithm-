package just_do_it_Algorithm;

import java.util.*;

/*
 * 프로그래머스 - 코딩테스트 연습 - 2019 KAKAO BLIND RECRUITMENT - 후보키
 * 시작 : 14:40
 * 끝 : 16:00 모르겠음
 * 걸린시간 : 
 * 
 * "완전탐색" 공부하고 풀자
 * "가능한 모든 조합만들기" 공부하고 풀자 - https://bcp0109.tistory.com/15
 */

//유일성(uniqueness) : 릴레이션에 있는 모든 튜플에 대해 유일하게 식별되어야 한다.
//최소성(minimality) : 유일성을 가진 키를 구성하는 속성(Attribute) 중 하나라도 제외하는 경우 유일성이 깨지는 것을 의미한다. 
//					 즉, 릴레이션의 모든 튜플을 유일하게 식별하는 데 꼭 필요한 속성들로만 구성되어야 한다.

public class kakao_blind_2019_3 {
	static int solution(String[][] relation) {
		int answer = 0;
		int totalCardi = relation[0].length;
		int totalTuple = relation.length;
		List[] minimality = new ArrayList[totalCardi];

		for (int i = 0; i < totalCardi; i++) {
			// 몇 개로 찾을 건지 정하고, 쌍 만들기
			int findSame = 0;
			for (int j = 0; j < totalCardi; j++) {
				for(int k=0;k<totalTuple;k++) {
					if(relation[j][0].equals(relation[k][j])) {
						findSame++;
					}
				}
			}
			if(findSame==1) {
				
			}
		}

		return answer;
	}

	public static void main(String[] args) {
		String[][] relation = { { "100", "ryan", "music", "2" }, { "200", "apeach", "math", "2" },
				{ "300", "tube", "computer", "3" }, { "400", "con", "computer", "4" }, { "500", "muzi", "music", "3" },
				{ "600", "apeach", "music", "2" } };

		System.out.println(solution(relation));
	}
}
