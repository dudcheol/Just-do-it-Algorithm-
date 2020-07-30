package algorithm.baekjoon.BruteForce;

/*
 * 2309 - 일곱난쟁이
 * 시작: 16:45
 * 끝: 18:18
 * 시간: 1시간 33분
 * 
 * [배운 것]
 * - 재귀로  완전탐색을 풀 때, 표시해두는 배열이 있고 재귀를 for문으로 돈다면 반드시 표시해둔 것을 제거하는 작업을 추가해야 한다!!
 * 
 * [실수한 것]
 * - 헷갈리지 않으려고 입력받은 값을 배열의 인덱스1부터 시작하게 하고싶다면
 *   반복문 사용시 부등호사용에 주의를 기울여야 한다. 이  문제에서  입력값은 9개이고 배열의 1부터 값을 삽입하는데 for문 범위가 i<9여서
 *   맨 마지막 배열은 for문이 돌지 않아 0이 저장되어 있는 상태로 진행해서 오류를 찾는데 너무 많은 시간을 버렸다. 조심하자!!
 * - 디버깅시 선언된 것들에 제대로 값이 담겼는지 확인하는 과정 필요하다.
 */

import java.util.*;

public class _2309_7dwarf {
	 static int[] arr,visited;
	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        arr = new int[9];
	        visited = new int[9];
	        for(int i=0;i<9;i++)
	            arr[i] = sc.nextInt();
	        solve(0,0,0);
	    }
	    
	    // 키의 합은 100, 하나만 출력하면 되므로 출력후 flag로 빠저나온다.
	    static boolean flag = false;
	    private static void solve(int idx,int cnt,int sum) {
	        if(flag) return;
	        // 7개를 모았을 때
	        if(cnt==7) {
	            // 합이 100이라면 
	            if(sum==100) {
	                int[] result = new int[7];
	                // 7개 체크된 값을 받아. 출력한다.
	                for(int i=0,index=0;i<9;i++)
	                    if(visited[i]==1) result[index++]=arr[i];
	                Arrays.sort(result);
	                for(int i:result)
	                    System.out.println(i);
	                flag = true;
	            }
	            return;
	        }
	        if(idx>=9) return;
	        // 현재 포함
	        visited[idx] = 1;
	        solve(idx+1,cnt+1,sum+arr[idx]);
	        visited[idx] = 0;
	        
	        // 현재 미포함
	        solve(idx+1,cnt,sum);
	    }

//	static List<Integer> answer = new ArrayList<>();
//
//	static boolean findDwarf(int tallSum, int cnt, int[] dwarfs, boolean[] visited) {
//		if (tallSum > 100 || cnt > 7)
//			return false;
//
//		if (cnt == 7) {
//			if (tallSum == 100) {
//				for (int i = 1; i < visited.length; i++) {
//					if (visited[i])
//						answer.add(dwarfs[i]);
//				}
//				return true;
//			}
//			return false;
//		}
//
//		for (int i = 1; i <= 9; i++) { // Q: 이부분에서 꼭 전부 순회해야 하는가??
//			if (visited[i])
//				continue;
//			tallSum += dwarfs[i];
//			visited[i] = true;
//			if(findDwarf(tallSum, cnt + 1, dwarfs, visited)) break;
//			tallSum -= dwarfs[i];
//			visited[i] = false;
//		}
//		
//		return false;
//	}
//
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		int[] dwarfs = new int[10]; // 1부터 시작 ~9까지
//		for (int i = 1; i <= 9; i++) {
//			dwarfs[i] = sc.nextInt();
//		}
//
//		findDwarf(0, 0, dwarfs, new boolean[10]);
//
//		Collections.sort(answer);
////		System.out.println("---------------");
//		for (int a : answer) {
//			System.out.println(a);
//		}
//	}
}
