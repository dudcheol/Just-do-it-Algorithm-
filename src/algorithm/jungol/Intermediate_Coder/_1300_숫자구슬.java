package algorithm.jungol.Intermediate_Coder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1300_숫자구슬 {
    private static int N,M, marble[];
    private static boolean[] visited;
    private static int min, cnts[];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N= Integer.parseInt(st.nextToken());
        M= Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine()," ");
        marble = new int[N];
        for (int i = 0; i < N; i++) {
            marble[i] = Integer.parseInt(st.nextToken());
        }

        int low=1, high=N*100; // N개의 구슬이 있고, 구슬은 100이하의 자연수이므로 최대값은 N*100
        while(low<=high){
            int mid = (low+high)/2;
            if (isPossible(mid)){ // mid를 최댓값으로 할 때, M개 그룹 이내로 나눌 수 있는가?
                high = mid-1; // 그렇다면 더 작은 값도 확인해본다
            } else { // 나눌 수 없는가?
                low = mid+1; // 그렇다면 더 큰 값도 확인해본다
            }
        }

        System.out.println(low);

        int sum=0, cnt=0, idx=0;
        int[] cnts = new int[M];
        for (int i = 0; i < N; i++) {
            sum+=marble[i];
            if(sum > low){
                sum=marble[i];
                cnts[idx++]=cnt;
                cnt=0;
            }
            cnt++;
        }
        cnts[idx] = cnt;

        for (int i = 0; i < M; i++) {
            System.out.print(cnts[i]+" ");
        }

    }

    /**
     * [실수]
     * 나는 나누는 구간을 일일이 구하고, 나눠진 구간을 기준으로 그룹의 합의 최댓값을 구했다
     * => 구간 나누기 실패 + 거의 모든 경우의 수 확인해야 함 = 오답 및 시간초과
     *
     * [풀이]
     * M개 그룹으로 나눴을 때의 최댓값 val을 미리 정해놓고,
     * val을 최댓값으로 했을 때, M개 이하의 그룹이 되는지 확인한다 (아래 isPossible 함수 참고)
     * 된다면? val의 크기를 줄여가며 더 작은 수여도 M개 이하 그룹이 되는지 확인
     * 안된다면? val의 크기를 키워가며 M개 이하 그룹의 최댓값으로 val이 되는 것을 찾는다
     * 그러다가 low>high가 되는 시점에서의 low값이 찾으려던 수가 된다.
     *
     * 왜 val의 크기를 줄이나?
     * val이 작아질수록 만들어질 수 있는 그룹의 수가 많아진다.
     * 1개 그룹만으로 val이 최댓값이 가능해진다면 M개 그룹으로 했을 때 더 작은 최댓값을 구해보아야 한다.
     */
    // 여러개 그룹으로 나누는 유형을 풀 때 고려해볼 방법
    // 그룹의 합의 최댓값을 val로 할 때, M개 그룹 이내로 나눌 수 있는지 구하는 함수
    private static boolean isPossible(int val){
        int cnt=1, sum=0;
        for (int i = 0; i < N; i++) {
            if (marble[i] > val) return false;
            if (sum + marble[i] <= val){
                sum += marble[i];
            } else {
                cnt++;
                sum = marble[i];
            }
        }
        return cnt <= M;
    }
}
