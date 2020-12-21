package algorithm.jungol.Beginner_Coder.수학2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2813_소수의_개수 {
    private static int[] prime;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        prime = new int[2000005];

        eratos(N);

        int cnt=0;
        for (int i = M; i <= N; i++) {
            if (prime[i]==0) cnt++;
        }
        System.out.println(cnt);
    }

    private static void eratos(int n) {
        int i,j;
        // prime[i]==1이면 소수가 아님
        prime[0]=prime[1]=1; // 0은 애초에 제외, 1은 정의상 소수가 아님
        for(i=2 ; i*i<=n ; i++){
            if (prime[i]==0){
                for(j=i*i ; j<=n ; j+=i){ // i의 제곱부터 n까지 i씩 증가
                    prime[j] = 1; // i의 배수 제거하기
                }
            }
        }
    }
}
