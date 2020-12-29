package algorithm.jungol.Beginner_Coder.여러가지;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1031_빙고 {
    private static int[][] map;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        map = new int[5][5];
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 5; j++) {
                if(play(Integer.parseInt(st.nextToken()))){
                    System.out.println(i*5+j+1);
                    return;
                }
            }
        }
    }

    private static boolean play(int num) {
        int bingo = 0;

        // 숫자 찾아서 빙고 표시하기
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (map[i][j]==num)map[i][j]=-1;
            }
        }

        // 가로 빙고 여부 확인
        loop1:for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (map[i][j]!=-1) continue loop1;
            }
            bingo++;
        }
        // 세로 빙고 여부 확인
        loop2:for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (map[j][i]!=-1) continue loop2;
            }
            bingo++;
        }
        // 왼오대각 빙고 여부 확인
        boolean daegak = true;
        loop3:for (int i = 0; i < 5; i++) {
            if (map[i][i]!=-1) {daegak=false; break;}
        }
        if (daegak) bingo++;
        // 오왼대각 빙고 여부 확인
        daegak = true;
        loop3:for (int i = 0; i < 5; i++) {
            if (map[i][4-i]!=-1) {daegak=false; break;}
        }
        if (daegak) bingo++;

        // 빙고 숫자 == 3 이면 return true
        if (bingo>=3) return true;
        return false;
    }
}
