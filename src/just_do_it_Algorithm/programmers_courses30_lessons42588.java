package just_do_it_Algorithm;

import java.util.*;

/*  발사한 신호는 신호를 보낸 탑보다 높은 탑에서만 수신
 *  한 번 수신된 신호는 다른 탑으로 송신되지 않습니다.
 *  
 *  제한
 *  heights는 길이 2 이상 100 이하인 정수 배열입니다.
	모든 탑의 높이는 1 이상 100 이하입니다.
	신호를 수신하는 탑이 없으면 0으로 표시합니다.
	
	송신 탑(높이)	수신 탑(높이)
		5(4)	4(7)
		4(7)	2(9)
		3(5)	2(9)
		2(9)	-
		1(6)	-
 */

public class programmers_courses30_lessons42588 {
	static public int[] solution(int[] heights) {
		int[] answer = new int[heights.length];
		Stack<Integer> top = new Stack<>();
		
		for(int i=0;i<heights.length;i++) {
			top.push(heights[i]);
		}
			
		int k=heights.length-1;
		while(!top.isEmpty()) {
			int poped = top.pop();
			for(int i=top.size()-1;i>=0;i--) {
				if(poped < top.elementAt(i)) {
					answer[k] = i+1;
					break;
				}
			}
			k--;
		}
		
		return answer;
	}

	public static void main(String[] args) {
//		int[] answer = {4,7,5,9,6};
//		int[] answer = {6,9,5,7,4};
//		int[] answer = {3,9,9,3,5,7,2};
		int[] answer = {1,5,3,6,7,6,5};
		
		int[] solutions = solution(answer);
		for(int solution : solutions) System.out.print(solution+", ");
	}
}
