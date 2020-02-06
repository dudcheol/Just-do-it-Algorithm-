package APSS.recursionAndBruteforce;

/*
 * 알고리즘 문제해결 전략 - 189p - 쿼드 트리 뒤집기
 * 시작~끝: 14:00
 * 걸린시간:
 *	[어떤  방식으로 접근했나?]
	압축된 문자열을 압축해제하여 원본사진을 구하고, 원본사진을 상하반전 시키는 작업을 해준 후, 다시 압축하려고 했다.
 * - 틀렸다! -
 *	[오답 원인]
	1. 문제의 제약조건을 제대로 확인하지 않았다. 확인하지 않아서 잘못된 방향으로 풀이를 진행했다...
	2. '분할정복'적인 생각을 하지 못했다. 그리고 'x'인 경우에 4등분인데 w,b인 경우에도 4등분 해버렸다.
 *	[다른 사람 코드 참고 -> 내가 취했던 접근 되돌아보기 -> 나는 왜 이 풀이를 떠올리지 못했나?] 
	'분할정복'의 개념을 확실히 하지못해서, 전체문제와 부분문제가 뭔지 확실히 몰랐음.
	또, 문제의 제약조건을 확인하지 않아서 2^20x2^20의 감당할 수 없는 크기를 고려하지 않았음.
	--> 애초에 '무식한방법'이 통할 줄 알고 시도하려고 했음 ㅠㅠ .. 무턱대로 코드로 옮기는 것은 위험함
 */

import java.util.*;
import java.io.*;

public class _189p_QuadTree {
//	static int originSize;
	static String str;
	static int idx;
//	static boolean[][] picture;

//	static void getOriginSize(int depth) {
//		if (str.length() == 0) {
//			originSize = Math.max(originSize, depth);
//			return;
//		}
	/* 필요없는 부분이지만 애초에 이부분 잘못됐다!!!! x인 경우에만 4등분되는데 왜 모든 경우에서 4등분을 해버리냐!!! */
//		for (int i = 0; i < 4; i++) {
//			if (str.length() == 0)
//				break;
//			if (str.startsWith("x")) {
//				str = str.substring(1);
//				getOriginSize(depth + 1);
//			} else {
//				str = str.substring(1);
//			}
//		}
//		originSize = Math.max(originSize, depth);
//		return;
//	}

	/*
	 * 압축된 문자열을 다시 사진으로 만드는 작업... 이 작업은 매우 위험하다. 사진의 최대 크기가 2^20x2^20인데 그걸 압축 해제하려고
	 * 하다니... 압축을 해제하지 않고 풀 수 있는 방법을 찾아야 한다
	 */
//	static void createPicture(int depth, int size, int[][] pos) {
	/* 하지만 매개변수로 size와 pos를 주어 4등분했을때 각자의 위치가 어떻게될지 생각해서 넘겨준 것은 좋은 시도였다. */
//		if (depth > originSize)
//			return;
//		for (int i = 0; i < 4; i++) {
//			if (str.length() == 0)
//				break;
//			if (str.startsWith("x")) {
//				str = str.substring(1);
//				createPicture(depth + 1, size / 2, divPos(pos, size));
//				mulPos(pos);
//				continue;
//			} else if (str.startsWith("w")) {
//				str = str.substring(1);
//				for (int k = 0; k < size; k++) {
//					for (int l = 0; l < size; l++) {
//						picture[pos[i][0]+k][pos[i][1]+l] = true;
//					}
//				}
//			} else {
//				str = str.substring(1);
//				for (int k = 0; k < size; k++) {
//					for (int l = 0; l < size; l++) {
//						picture[pos[i][0]+k][pos[i][1]+l] = false;
//					}
//				}
//			}
//			
//			for(int r=0;r<picture.length;r++) {
//				for(int c=0;c<picture.length;c++) {
//					System.out.print(picture[r][c]?0:1);
//				}
//				System.out.println();
//			}
//			System.out.println("----------------------");
//		}
//	}
//
//	static int[][] divPos(int[][] pos, int size) {
//		pos[1][1] = size / 2 ;
//		pos[2][0] = size / 2 ;
//		pos[3][0] = size / 2 ;
//		pos[3][1] = size / 2 ;
//		return pos;
//	}
//
//	static int[][] mulPos(int[][] pos) {
//		pos[1][1] = (pos[1][1] ) * 2;
//		pos[2][0] = (pos[2][0] ) * 2;
//		pos[3][0] = (pos[3][0] ) * 2;
//		pos[3][1] = (pos[3][1] ) * 2;
//		return pos;
//	}

	static String reverse() {
		/* 문자열을 순회하기 위한 인덱스 */
		idx += 1;
		/* BaseCase */
		char currentStr = str.charAt(idx);
		if (currentStr == 'w' || currentStr == 'b') {
			/*
			 * 현재 조회중인 문자가 w(흰색)나 b(검정색)라면 전부 색칠해야 하지만 우리가 원하는 답은 상하반전 한 후 압축된 결과물이다. 전부
			 * 흰색,검정색인 NxN인 사각형은 상하반전을 하나 안하나 모양이 같으므로 있는 그대로 리턴한다
			 */
			return currentStr + "";
		} else {
			/*
			 * 현재 조회중인 문자가 x인 경우 4등분 되어야 한다. 현재문제를 4등분해서 만들어진 부분 문제들은 모두 현재문제와 동일한 작업을 한다.
			 * 따라서 4등분된 부분문제들도 reverse를 사용할 수 있다. 하지만 여기서 달라지는 것이 있는데...! 그것은 
			 	1. 4등분됨으로써작아지는 사이즈 
			 	2. 4등분됨으로써 차지하는 부분이 달라진다는 것
			 */
			String upLeft = reverse();
			String upRight = reverse();
			String downLeft = reverse();
			String downRight = reverse();

			return "x" + downLeft + downRight + upLeft + upRight; // 상하반전을 위해 down먼저
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int C = Integer.parseInt(br.readLine());

		for (int c = 0; c < C; c++) {
			String inputStr = br.readLine();
			str = inputStr;
			idx = -1;
			bw.write(reverse()+"\n");
		}
		
		bw.flush();
		br.close();
		bw.close();

//		for (int c = 0; c < C; c++) {
//			originSize = 0;
//			String inputStr = br.readLine();
//			str = inputStr;
//			getOriginSize(0);
//			System.out.println(originSize);
//			int size = (int) (Math.sqrt(Math.pow(4, originSize)));
//			picture = new boolean[size][size];
//			str = inputStr;
//			int[][] pos = new int[4][2];
//			createPicture(0, size, pos);
//		}
	}
}
