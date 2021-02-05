package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _17143_낚시왕 {
    private static int R,C,M;
    private static int[] dy= {0,-1,1,0,0};//상하우좌
    private static int[] dx= {0,0,0,1,-1};//상하우좌
    private static int[] convertDir= {0,2,1,4,3};//상하우좌
    private static Shark[][] map;

    private static class Shark{
        int s;//속력 1234 위아래우좌
        int d;//이동방향
        int z;//크기
        public Shark(int s, int d, int z) {
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    // 9:30~
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R= Integer.parseInt(st.nextToken());
        C= Integer.parseInt(st.nextToken());
        M= Integer.parseInt(st.nextToken());
        map = new Shark[R+1][C+1];

        for (int i = 0; i < M; i++) {
            st=new StringTokenizer(br.readLine()," ");
            int r=Integer.parseInt(st.nextToken());
            int c=Integer.parseInt(st.nextToken());
            int s=Integer.parseInt(st.nextToken());
            int d=Integer.parseInt(st.nextToken());
            int z=Integer.parseInt(st.nextToken());
            map[r][c] = new Shark(s,d,z);
        }

        int move=0;
        int answer=0;
        while(++move<=C) {

//			printmap();

            // 상어 잡음
            for (int i = 1; i <= R; i++) {
                if(map[i][move]!=null) {
                    answer+=map[i][move].z;
                    map[i][move]=null;
                    break;
                }
            }

            // 상어이동
            Shark[][] tmp = new Shark[R+1][C+1];
            for (int i = 1; i <= R; i++) {
                for (int j = 1; j <= C; j++) {
                    if(map[i][j]!=null) {

                        Shark shark = map[i][j];
                        int ni=i;
                        int nj=j;
                        int size;
                        if(shark.d==1||shark.d==2) {
                            size=(R-1)*2;
                        }else {
                            size=(C-1)*2;
                        }
                        size = shark.s%size;
                        for (int r = 0; r < size; r++) {
                            ni+=dy[shark.d];
                            nj+=dx[shark.d];
                            if(ni<1||nj<1||ni>R||nj>C) {
                                //격자판의 경계를 넘는 경우에는 방향을 반대로 바꿔서
                                //속력을 유지한채로 이동한다.
                                ni-=dy[shark.d];
                                nj-=dx[shark.d];
                                shark.d=convertDir[shark.d];
                                ni+=dy[shark.d];
                                nj+=dx[shark.d];
                            }
                        }
                        if(tmp[ni][nj]==null) {
                            tmp[ni][nj]= shark;
                        } else {
                            if(tmp[ni][nj].z < shark.z) {
                                tmp[ni][nj] = shark;
                            }
                        }
                    }
                }
            }

            map=tmp;
        }

        System.out.println(answer);

    }
}
