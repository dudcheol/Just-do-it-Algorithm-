package algorithm.baekjoon;

import java.util.*;
import java.io.*;

public class _20055_컨베이어벨트위의로봇 {

	private static int N;
	private static int K;
	private static Belt[] belt;
	private static Queue<Integer> robotPos;
	
	private static class Belt{
		int power;
		boolean robot;
		public Belt(int power, boolean robot) {
			this.power = power;
			this.robot = robot;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		belt = new Belt[N*2];
		robotPos = new LinkedList<Integer>();
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N*2; i++) {
			belt[i] = new Belt(Integer.parseInt(st.nextToken()), false);
		}
		
		int time = 0;
		while(true) {
			if(zeroCnt() >= K) {
				break;
			}
			
			// 1 벨트회전
			Belt tmp = new Belt(belt[N*2-1].power, belt[N*2-1].robot);
			for (int i = N*2-1; i >= 1; i--) {
				belt[i].power = belt[i-1].power;
				belt[i].robot = belt[i-1].robot;
			}
			belt[0].power = tmp.power;
			belt[0].robot = tmp.robot;
			
			belt[N].robot = false; // 로봇떨어짐
			
			// 2
			belt[N-1].robot = false;
			for (int i = N-2; i >= 0; i--) {
				if(belt[i].robot) {
					if(!belt[i+1].robot && belt[i+1].power >= 1) {
						belt[i].robot = false;
						belt[i+1].robot = true;
						belt[i+1].power -= 1;
					}
				}
			}
			
			// 3
			if(!belt[0].robot && belt[0].power >= 1) {
				belt[0].robot = true;
				belt[0].power -= 1;
			}
			
			time++;
		}
		
		System.out.println(time);
	}

	private static int zeroCnt() {
		int cnt = 0;
		for (int i = 0; i < N*2; i++) {
			if(belt[i].power == 0) {
				cnt++;
			}
		}
		return cnt;
	}

}
