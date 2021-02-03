package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 21:30~22:35
public class _20058_마법사_상어와_파이어스톰 {
    private static int N,Q,map[][],size;
    private static int[] dy = {0,1,0,-1};//우하좌상
    private static int[] dx = {1,0,-1,0};//우하좌상
    private static boolean[][] visited;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N=Integer.parseInt(st.nextToken()); // 격자크기
        Q=Integer.parseInt(st.nextToken()); // 시전횟수
        size = (int)Math.pow(2,N);
        map=new int[size][size];
        for (int i = 0; i < size; i++) {
            st=new StringTokenizer(br.readLine()," ");
            for (int j = 0; j < size; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        st=new StringTokenizer(br.readLine()," ");
        for (int q = 0; q < Q; q++) {
            int cmd =Integer.parseInt(st.nextToken());
            int l = (int)Math.pow(2,cmd);

            // l크기 만큼 격자로 나누기
            for (int i = 0; i < size; i+=l) {
                for (int j = 0; j < size; j+=l) {
                    rotate(i,j,l); // 나눈 격자만큼 회전
                }
            }

            // 얼음양줄이기
            melt();
        }

        // 남아있는 얼음의 합
        System.out.println(sumRestIce());

        // 가장 큰 덩어리가 차지하는 칸의 수
        int max=0;
        visited=new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (visited[i][j]||map[i][j]==0) continue;
                max=Math.max(max,dfs(i,j));
            }
        }

        System.out.println(max);
    }

    private static int dfs(int y, int x) {
        int ret = 1;
        visited[y][x]=true;
        for (int d = 0; d < 4; d++) {
            int ny=y+dy[d];
            int nx=x+dx[d];
            if (ny<0||nx<0||ny>=size||nx>=size||visited[ny][nx]||map[ny][nx]==0) continue;
            ret += dfs(ny,nx);
        }
        return ret;
    }

    private static int sumRestIce() {
        int cnt=0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j]>0)cnt+=map[i][j];
            }
        }
        return cnt;
    }

    private static void melt() {
        int[][] tmp = deepcopy(map);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tmp[i][j]==0)continue;
                int cnt=0;
                for (int d = 0; d < 4; d++) {
                    int ny=i+dy[d];
                    int nx=j+dx[d];
                    if (ny<0||nx<0||ny>=size||nx>=size||tmp[ny][nx]<=0) continue;
                    cnt++;
                }
                if (cnt<3){
                    map[i][j]--;
                }
            }
        }
    }

    private static int[][] deepcopy(int[][] map) {
        int[][] ret = new int[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(map[i],0,ret[i],0,size);
        }
        return ret;
    }

    /**
     * 너무 어렵게 회전했음
     * 잘보면 1행이 4열이 되고, 2행이 3열, 3행이 2열, 4행이 1열로 가는 것을 알 수 있음
     */
    private static void rotate(int y, int x, int size) {
        int[][] tmp=new int[size][size];
        int sy=y,sx=x-1,ey=-1,ex=size-1;
        int sd=0,ed=1;
        int cnt=size;
        int limit=1;
        while (cnt>0){
            if (limit%2==0){
                cnt--;
            }
            // cnt만큼 반복
            for (int r = 0; r < cnt; r++) {
                sy+=dy[sd];
                sx+=dx[sd];
                ey+=dy[ed];
                ex+=dx[ed];
                tmp[ey][ex] = map[sy][sx];
            }
            limit++;
            sd=(sd+1)%4;
            ed=(ed+1)%4;
        }

        // map에 반영
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[y+i][x+j]=tmp[i][j];
            }
        }
//        print();
    }

    private static void print() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
