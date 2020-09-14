package algorithm.swea.mockTest;
import java.io.*;
import java.util.*;

public class Solution_d9_2112_보호필름 {
    static int W; // 보호 필름 폭 W는 1이상 20이하의 정수(1≤W≤20)
    static int D; // 보호 필름의 두께 D는 3이상 13이하의 정수(3≤D≤13)
    static int K; // 합격기준 K는 1이상 D이하의 정수(1≤K≤D)
    static int Min; // 최소 투약 회수
    static int[] inject; // 약품의 투여 여부(-1:투여하지 않음, 0:A투여, 1:B투여)
    static int[][] map;

    static boolean check(){
        int cnt=0;// 연속된 셀을 세는 카운트
        for(int d=0; d<D; d++){
            cnt=1;
            for(int w=0; w<W-1; w++){// 다음 행까지 검사할 꺼다.
                // 검사할 현재 셀과 다음 셀,기본적으로는 맵에 있는 값을 가져다 쓴다.
                int curr=inject[w  ]==-1? map[w  ][d]:inject[w  ];
                int next=inject[w+1]==-1? map[w+1][d]:inject[w+1];// 다음 행과 비교
                if(curr==next){// 연속된 경우
                    cnt++;
                    if(cnt>=K) break; // K보가 같거나 크면 그만 체크
                }else{
                    cnt=1;
                }
            }
            if(cnt<K) return false; // 이번 컬럼에서 실패 --> 최종실패
        }
        return true;
    }
    public static void dfs(int row, int count){//row 행번호, count 약품 투여 회수
       if(count>=Min) return; // 최소값을 넘어가면 의미 없다.
       if(row==W){ // 맨 마지막에 도달하면 체크하기
          if(check()) Min=Math.min(Min,count);
          return;
       }
       for(int i=-1; i<2; i++){
          inject[row]=i;                 // 행이 그냥 일괄적으로 바뀌기 때문에 가능한 일
          if(i==-1) dfs(row+1, count  ); // 약품 주입을 안하는 경우 - 주입 회수는 유지
          else      dfs(row+1, count+1); // 약품 주입 처리하는 경우 - 주입 회수 증가
       }
    }
   public static void main(String[] args) throws Exception{
      System.setIn(new FileInputStream("res/input_d9_2112.txt"));
      BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
      StringBuilder sb=new StringBuilder();      
        int T=Integer.parseInt(br.readLine());
        
        for(int tc=1; tc<=T; tc++){
           StringTokenizer st=new StringTokenizer(br.readLine());
            W=Integer.parseInt(st.nextToken()); // 보호 필름의 가로크기(1≤W≤20)
            D=Integer.parseInt(st.nextToken()); // 보호 필름의 두께(3≤D≤13)
            K=Integer.parseInt(st.nextToken()); // 합격기준 K(1≤K≤D)
            map=new int[W][D];
            inject=new int[W];
            for(int w=0; w<W; w++){
                st=new StringTokenizer(br.readLine());
                for(int d=0; d<D; d++){
                    map[w][d]=Integer.parseInt(st.nextToken());
                }
            }
            Min=Integer.MAX_VALUE;
            dfs(0, 0);// 행과 주입 회수
            sb.append("#").append(tc).append(" ").append(Min).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
}