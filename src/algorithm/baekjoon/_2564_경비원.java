package algorithm.baekjoon;

import java.io.*;
import java.util.*;

public class _2564_경비원 {
	
	static int R,C,cnt,sDir[],sDist[],answer;
	static int[] calDir = {0, 2, 1, 4, 3};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		cnt = Integer.parseInt(br.readLine());
		sDir = new int[cnt+1];
		sDist = new int[cnt+1];
		for (int i = 0; i <= cnt; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			sDir[i] = Integer.parseInt(st.nextToken());
			sDist[i] = Integer.parseInt(st.nextToken());
		}
		
		// 각 상점, 동근위치 좌표화하기 - 마지막은 동근 (sPos[cnt] = 동근)
		int[][] sPos = new int[cnt+1][2];
		for (int i = 0; i <= cnt; i++) {
			switch(sDir[i]) {
			case 1:
				sPos[i][0] = 0;
				sPos[i][1] = sDist[i];
				break;
			case 2:
				sPos[i][0] = R;
				sPos[i][1] = sDist[i];
				break;
			case 3:
				sPos[i][0] = sDist[i];
				sPos[i][1] = 0;
				break;
			case 4:
				sPos[i][0] = sDist[i];
				sPos[i][1] = C;
				break;
			}
		}
		
		// 동근위치
		int mr = sPos[cnt][0];
		int mc = sPos[cnt][1];
		int oppo = calDir[sDir[cnt]]; // 동근과 반대인 방향
		for (int i = 0; i < cnt; i++) {
			// 상점위치
			int sr = sPos[i][0];
			int sc = sPos[i][1];
			
			if(sDir[i] == oppo) { // 동근의 위치와 반대방향 => 좌,우(또는 상,하)로 가보면서 더 작은값 선택
				if(oppo == 1 || oppo == 2) {// 반대방향이 상하인 경우 - 좌우 비교
					
					int left = mc + R + sc;
					int right = C-mc + R + C-sc;
					answer += Math.min(left, right);
					
				} else { // 반대방향이 좌우인 경우 - 상하 비교
					
					int top = mr + C + sr;
					int bottom = R-mr + C + R-sr;
					answer += Math.min(top, bottom);
					
				}
			} else { // 나머지 방향 => 두 좌표 사이의 거리
				answer += Math.abs(mr-sr) + Math.abs(mc-sc);
			}
		}
		
		System.out.println(answer);
	}

}
