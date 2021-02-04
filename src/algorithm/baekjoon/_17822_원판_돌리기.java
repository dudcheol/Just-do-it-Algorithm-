package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _17822_원판_돌리기 {

    private static int N,M,T;
    private static int[][] map;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        T=Integer.parseInt(st.nextToken());
        map=new int[N+1][M];
        for (int i = 1; i <= N; i++) {
            st=new StringTokenizer(br.readLine()," ");
            for (int j = 0; j < M; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int x=Integer.parseInt(st.nextToken());//x배수 원판선택
            int d=Integer.parseInt(st.nextToken());//방향
            int k=Integer.parseInt(st.nextToken());//이동칸수

            int mul=1;
            while((x*mul)<=N) {
                map[x*mul] = rotate(x*mul, d, k); // 회전시키기
                mul++;
            }

            if(hasNum()) {
                // 인접하면서 같은 수 찾아서 지우기
                if(!removeSameAdj()) {
                    double avg = getAvg();
                    addSubAvg(avg);
                }
            }
        }

//		while(removeSameAdj());

        System.out.println(getSum());

    }

    private static int getSum() {
        int sum=0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j]==0) continue;
                sum+=map[i][j];
            }
        }
        return sum;
    }

    private static void addSubAvg(double avg) {
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j]==0) continue;

                if(map[i][j] > avg) {
                    map[i][j]-=1;
                } else if(map[i][j]<avg) {
                    map[i][j]+=1;
                }
            }
        }
    }

    private static double getAvg() {
        int sum=0;
        double cnt=0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j]==0) continue;
                sum+=map[i][j];
                cnt++;
            }
        }
        return sum/cnt;
    }

    private static boolean removeSameAdj() {
        int[][] tmp = deepcopy(map);
        boolean find=false;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                int cur = map[i][j];
                if(cur==0) continue;
                if(cur==map[i][(j-1<0)?M-1:j-1]) {
                    tmp[i][j]=tmp[i][(j-1<0)?M-1:j-1]=0;
                    find=true;
                }
                if(cur==map[i][(j+1)%M]) {
                    tmp[i][j]=tmp[i][(j+1)%M]=0;
                    find=true;
                }
                if(cur==map[(i-1)<0?N:i-1][j]) {
                    tmp[i][j]=tmp[(i-1)<0?N:i-1][j]=0;
                    find=true;
                }
                if(cur==map[(i+1)%(N+1)][j]) {
                    tmp[i][j]=tmp[(i+1)%(N+1)][j]=0;
                    find=true;
                }
            }
        }
        map=tmp;
        return find;
    }

    private static int[][] deepcopy(int[][] origin) {
        int[][] ret = new int[N+1][M];
        for (int i = 1; i <= N; i++) {
            System.arraycopy(origin[i], 0, ret[i], 0, M);
        }
        return ret;
    }

    private static boolean hasNum() {
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j]!=0) return true;
            }
        }
        return false;
    }

    private static int[] rotate(int x, int d, int k) {
        int[] tmp = new int[M];
        if(d==0) { //시계 우
            for (int i = 0; i < M; i++) {
                tmp[(i+k)%M] = map[x][i];
            }
        }else {// 반시계 좌
            for (int i = 0; i < M; i++) {
                tmp[(i+M-k)%M] = map[x][i];
            }
        }
        return tmp;
    }

}