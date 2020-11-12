package algorithm.baekjoon;
import java.io.*;
import java.util.*;

public class _16235_나무재테크_ArrayList풀이 {
	static int[] di={-1,-1,-1,0,1,1,1,0};//좌상,상,우상,우,우하,하,좌하,좌
	static int[] dj={-1,0,1,1,1,0,-1,-1};
	static int N,M,K;
	static int[][] Y,A;//Y초기양분5,A추가양분
	static List<int[]> T,C,D;//T나무정보,C증가나무,D죽은나무

	static void year(){
		//봄
		Collections.sort(T,new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {//i,j,age
				return Integer.compare(o1[2],o2[2]);
			}
		});
		for(int[] t:T){
			if(Y[t[0]][t[1]]>=t[2]){ 
			   Y[t[0]][t[1]]-=t[2]; 
			   t[2]++;  
			   C.add(t);
			}else{
			   D.add(t);
			}
		}
		T.clear();
		T.addAll(C);
		C.clear();
		//여름
		for(int i=D.size()-1; i>=0; i--){
			int[] t=D.remove(i);
			Y[t[0]][t[1]]+=(t[2]/2);
		}
		//가을
		int size=T.size();//주의
		for(int s=0; s<size; s++){
			int[] t=T.get(s);
			if(t[2]%5==0){
				for(int d=0; d<8; d++){
					int ni=t[0]+di[d]; 
					int nj=t[1]+dj[d];
					if(1<=ni&&ni<=N && 1<=nj&&nj<=N) T.add(new int[]{ni,nj,1});//i,j,age
				}
			}
		}
		//겨울
		for(int i=1; i<=N; i++){
			for(int j=1; j<=N; j++){
				Y[i][j]+=A[i][j];
			}
		}
	}
	public static void main(String[] args) throws Exception{
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();//땅크기
		M=sc.nextInt();//심은 나무정보 갯수
		K=sc.nextInt();//K년이 지난 후 살아남은 나무의 수를 출력
		A=new int[N+1][N+1];
		Y=new int[N+1][N+1];
		for(int i=1; i<=N; i++){
			for(int j=1; j<=N; j++){
				Y[i][j]=5;//초기양분5
				A[i][j]=sc.nextInt();//추가양분
			}
		}
		T=new ArrayList<int[]>();//나무정보
		C=new ArrayList<int[]>();//봄에 나이증가 나무정보
		D=new ArrayList<int[]>();//봄에 나이증가 못해 죽은 나무정보
		for(int i=0; i<M; i++) T.add(new int[]{sc.nextInt(),sc.nextInt(),sc.nextInt()});//i,j,age
		for(int i=0; i<K; i++) year();
	    System.out.println(T.size());
	    sc.close();
	}
} 