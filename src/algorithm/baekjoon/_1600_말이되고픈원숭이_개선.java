package algorithm.baekjoon;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class _1600_말이되고픈원숭이_개선 {
	static int[] di={-1,0,1,0, -2,-1,1,2,2,1,-1,-2};//상우하좌
	static int[] dj={0,1,0,-1, 1,2,2,1,-1,-2,-2,-1};
	static int K,W,H,map[][];
	static boolean[][][] visit;
	
	static int bfs(){
		Queue<int[]> q=new LinkedList<>();
		visit[0][0][0]=true;
		q.offer(new int[]{0,0,0});//i,j,cnt
		int time=0;
		while(!q.isEmpty()) {
			int size=q.size();
			for(int s=0; s<size; s++) {
				int[] cur=q.poll();
				int i=cur[0], j=cur[1], c=cur[2];
				if(i==H-1 && j==W-1) return time;
				for(int d=0; d<12; d++){
					int ni=i+di[d];
					int nj=j+dj[d];
					if(!(0<=ni&&ni<H && 0<=nj&&nj<W && map[ni][nj]==0)) continue;
					if(d<4){
						if(!visit[ni][nj][c]){
							visit[ni][nj][c]=true;
							q.offer(new int[]{ni,nj,c});
						}
					}else{
						if(c+1<=K && !visit[ni][nj][c+1]){
							visit[ni][nj][c+1]=true;
							q.offer(new int[]{ni,nj,c+1});
						}
					}
				}
			}
			time++;
		}
		return -1;
	}
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_1600.txt"));
		Scanner sc=new Scanner(System.in);
		K=sc.nextInt();
		W=sc.nextInt();
		H=sc.nextInt();
		map=new int[H][W];
		visit=new boolean[H][W][K+1];
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				map[i][j]=sc.nextInt();
			}
		}
		System.out.println(bfs());
		sc.close();
	}
}
