package algorithm.baekjoon;
import java.io.*;
import java.util.*;

public class _14500_테트로미노_다른풀이 {
	static int[] di={-1,0,1,0};//상0,우1,하2,좌3
	static int[] dj={0,1,0,-1};
	static int N,M,max;
	static int[][] map;
	static boolean[][] visit;
	
	static void dfs(int i, int j, int cnt, int sum){
		if(cnt==4) {
			max=Math.max(max,sum);
			return;
		}
		for(int d=0; d<4; d++){
			int ni=i+di[d];
			int nj=j+dj[d];
			if(0<=ni&&ni<N && 0<=nj&&nj<M && !visit[ni][nj]){
				visit[ni][nj]=true;
				dfs(ni,nj,cnt+1,sum+map[ni][nj]);
				visit[ni][nj]=false;
			}
		}
	}
	static void ㅗㅏㅜㅓ(int i, int j){
		if(0<=i-1        && 0<=j-1&&j+1<M) max=Math.max(max,map[i][j-1]+map[i][j]+map[i][j+1] +map[i-1][j]);//ㅗ
		if(0<=i-1&&i+1<N &&         j+1<M) max=Math.max(max,map[i-1][j]+map[i][j]+map[i+1][j] +map[i][j+1]);//ㅏ
		if(        i+1<N && 0<=j-1&&j+1<M) max=Math.max(max,map[i][j-1]+map[i][j]+map[i][j+1] +map[i+1][j]);//ㅜ
		if(0<=i-1&&i+1<N && 0<=j-1       ) max=Math.max(max,map[i-1][j]+map[i][j]+map[i+1][j] +map[i][j-1]);//ㅓ
	}
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_14500.txt"));
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		M=sc.nextInt();
		map=new int[N][M];
		visit=new boolean[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j]=sc.nextInt();
			}
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				visit[i][j]=true;
				dfs(i,j,1,map[i][j]);
				ㅗㅏㅜㅓ(i,j);
				visit[i][j]=false;
			}
		}
		System.out.println(max);
		sc.close();
	}
}
