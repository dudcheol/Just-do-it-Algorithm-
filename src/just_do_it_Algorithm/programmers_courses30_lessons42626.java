package just_do_it_Algorithm;

import java.util.*;

/*
 * 12:15 start
 * 섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
 * Leo는 모든 음식의 스코빌 지수가 K 이상이 될 때까지 반복하여 섞습니다.
 * 모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 섞어야 하는 최소 횟수를 return
 * 
 * 모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우에는 -1을 return 합니다.
 */

public class programmers_courses30_lessons42626 {
	static int solution(int[] scoville, int K) {
        int answer = 0;
        int cntK = 0;
        ArrayList<Integer> scovList = new ArrayList<>();
        
        // 가장 맵지 않은 순서로 정렬
        int size = scoville.length;
        
        // 리스트로 만듬
        for(int scov : scoville) {
        	scovList.add(scov);
        	if(scov < K) {
        		cntK++;
        	}
        }
        
        while(true) {
        	if(cntK==0) return -1;
        	if(scovList.size()<2) return -1;
			Collections.sort(scovList);
			if(scovList.get(0)>=K) break;
        	int resultScov = scovList.get(0) + scovList.get(1)*2;
        	answer++;
        	scovList.remove(0);
        	scovList.remove(0);
        	scovList.add(0, resultScov);
        	if(resultScov < K) {
        		continue;
        	} else {
        		if(scovList.get(1) < K) {
        			continue;
        		} else break;
        	}
        }
        
        return answer;
    }
	
	public static void main(String[] args) {
		int[] scoville = {1, 2, 3, 9, 10, 12};
		int K = 7;
		
		System.out.println(solution(scoville, K));
	}
}
