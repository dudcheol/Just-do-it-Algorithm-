package programmers.greedy;

/*
 * 프로그래머스 - 코딩테스트 연습 - 탐욕법(Greedy) - 조이스틱
 * 시작:
 * 끝:
 * 시간:
 * [배운 것]
 * - 값을 넣어주는 위치 하나하나에 이유가 있어야 한다.
 * - 중간중간에 고칠때 막 써넣으면 오히려 시간을  더 잡아먹을 수 있으니 분명히 하고 고치자!
 */

import java.util.*;

public class _42860_joystick {
	static int circle(int max, int d){
        if(d<0){
           d=max-1;
        }
        else if(d>=max){
           d=0;
        }
        return d;
    }
	
	static int solution(String name) {
        int answer = 0;
        int size = name.length();
        boolean[] isA = new boolean[size];
        // A인 요소 미리 체크 + target 만들기
        for(int i=0;i<size;i++){
            if(name.charAt(i)=='A') isA[i] = true;
        }
        // System.out.println(target);
        
        int k=0;
        int direction = 1;
        while(true){
           char c = name.charAt(k);
           System.out.print(c+"는 "+(int)c+"이므로 ");
//           answer += ('Z'- c + 1) > c - 'A' ? c - 'A' : ('Z' - c + 1);
            if(c<=78){
                // A에서 위로 올라가는게 더 이득일때
                answer += c-65;
                System.out.print((int)c-65+"번 이동");
            } else {
                answer += 90-c+1;
                System.out.print((int)90-c+1+"번 이동");
            }
            System.out.println();
            isA[k] = true; // 얘를 해주는 위치도 매우 중요하다
            
            int end=0;
            for(int i=0;i<size;i++){
                if(isA[i]) end++;
                else break;
            }
            if(end==size) break;
            
            int rightA=k, leftA=k;
            int rightTry=0,leftTry=0;
            int endF=0;
            while(!(endF==size)) {
            	endF++;
            	if(name.charAt(rightA)=='A') {
            		rightA = circle(size,rightA+1);
            		rightTry++;
            		continue;
            	}
            }
            endF=0;
            while(!(endF==size)) {
            	endF++;
            	if(name.charAt(leftA)=='A') {
            		leftA = circle(size,leftA-1);
            		leftTry++;
            		continue;
            	}
            }
            
            if(rightTry > leftTry && !isA[circle(size,k-direction)]) {
            	direction = -1;
            } else {
            	direction = 1;
            }
            
//            direction = rightTry > leftTry ? -1 : 1;
            k=circle(size,k+direction);
            answer++;
            System.out.println(direction>0?"오른쪽으로 1번 이동":"왼쪽으로 1번 이동");
        }
        
        return answer;
    }
	
	public static void main(String[] args) {
//		String name = "JEROEN";
		String name = "JAN";
//		String name = "AAB";
		
		System.out.println(solution(name));
	}
}
