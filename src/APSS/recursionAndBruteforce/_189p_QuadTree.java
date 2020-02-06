package APSS.recursionAndBruteforce;

/*
 * 알고리즘 문제해결 전략 - 189p - 쿼드 트리 뒤집기
 * 시작~끝: 14:00
 * 걸린시간:
 * - 맞았다! -
 *	[간단한 해법]
	
 *	[어떤  방식으로 접근했나?]
	
 *	[해법을 찾는 데 결정적인 깨달음]
	
 *	[다른 해결 방법이 있다면?]
	
 * - 틀렸다! -
 *	[오답 원인]
	
 *	[다른 사람 코드 참고 -> 내가 취했던 접근 되돌아보기 -> 나는 왜 이 풀이를 떠올리지 못했나?] 
	
 */

import java.util.*;
import java.io.*;

public class _189p_QuadTree {
	static int originSize;
	static String str;
	static boolean[][] picture;

	static void getOriginSize(int depth) {
		if (str.length() == 0) {
			originSize = Math.max(originSize, depth);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (str.length() == 0)
				break;
			if (str.startsWith("x")) {
				str = str.substring(1);
				getOriginSize(depth + 1);
			} else {
				str = str.substring(1);
			}
		}
		originSize = Math.max(originSize, depth);
		return;
	}

	static void createPicture(int depth, int size, int[][] pos) {
		if (depth > originSize)
			return;
		for (int i = 0; i < 4; i++) {
			if (str.length() == 0)
				break;
			if (str.startsWith("x")) {
				str = str.substring(1);
				createPicture(depth + 1, size / 2, divPos(pos, size));
				mulPos(pos);
				continue;
			} else if (str.startsWith("w")) {
				str = str.substring(1);
				for (int k = 0; k < size; k++) {
					for (int l = 0; l < size; l++) {
						picture[pos[i][0]+k][pos[i][1]+l] = true;
					}
				}
			} else {
				str = str.substring(1);
				for (int k = 0; k < size; k++) {
					for (int l = 0; l < size; l++) {
						picture[pos[i][0]+k][pos[i][1]+l] = false;
					}
				}
			}
			
			for(int r=0;r<picture.length;r++) {
				for(int c=0;c<picture.length;c++) {
					System.out.print(picture[r][c]?0:1);
				}
				System.out.println();
			}
			System.out.println("----------------------");
		}
	}

	static int[][] divPos(int[][] pos, int size) {
		pos[1][1] = size / 2 ;
		pos[2][0] = size / 2 ;
		pos[3][0] = size / 2 ;
		pos[3][1] = size / 2 ;
		return pos;
	}

	static int[][] mulPos(int[][] pos) {
		pos[1][1] = (pos[1][1] ) * 2;
		pos[2][0] = (pos[2][0] ) * 2;
		pos[3][0] = (pos[3][0] ) * 2;
		pos[3][1] = (pos[3][1] ) * 2;
		return pos;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int C = Integer.parseInt(br.readLine());

		for (int c = 0; c < C; c++) {
			originSize = 0;
			String inputStr = br.readLine();
			str = inputStr;
			getOriginSize(0);
			System.out.println(originSize);
			int size = (int) (Math.sqrt(Math.pow(4, originSize)));
			picture = new boolean[size][size];
			str = inputStr;
			int[][] pos = new int[4][2];
			createPicture(0, size, pos);
		}
	}
}
