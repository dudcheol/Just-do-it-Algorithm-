package algorithm.baekjoon.im;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2527_직사각형 {
	static int x1, y1, x2, y2, tx1, ty1, tx2, ty2;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            tx1 = Integer.parseInt(st.nextToken());
            ty1 = Integer.parseInt(st.nextToken());
            tx2 = Integer.parseInt(st.nextToken());
            ty2 = Integer.parseInt(st.nextToken());
            
            if ((x2 == tx1 && y2 == ty1) || (x1 == tx2 && y2 == ty1) || (x2 == tx1 && y1 == ty2) || (x1 == tx2 && y1 == ty2)) {
                sb.append("c");
            } else if ((x2 == tx1 && y2 != ty1) || (x1 == tx2 && y2 != ty1) || (x2 != tx1 && y1 == ty2) || (x1 != tx2 && y1 == ty2)) {
                sb.append("b");
            } else if (x2 < tx1 || tx2 < x1 || y2 < ty1 || ty2 < y1) {
                sb.append("d");
            } else {
                sb.append("a");
            }
            
			sb.append('\n');
		}
		System.out.println(sb);
	}

}
