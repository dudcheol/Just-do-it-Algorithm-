package algorithm.etc;

public class 능숙함Test2 {
    // Q. 연속된 N개 구간에서, 어떤 알파벳이 가장 많이 등장하고 몇 번 등장하는가?
    public static void main(String[] args) {
        int N = 5;
        String input = "SSADADAAASADAAAS";
        char[] alpha = input.toCharArray();

        int[] cnt = new int['Z'+1];
        int max = 0;
        char answer = 0;
        // 완전탐색
//        for (int i = 0; i < alpha.length-4; i++) {
//            for (int j = 0; j < 5; j++) {
//                cnt[alpha[i+j]]++;
//                if (max < cnt[alpha[i+j]]){
//                    max = cnt[alpha[i+j]];
//                    answer = (char)alpha[i+j];
//                }
//            }
//            Arrays.fill(cnt,0);
//        }

        // 슬라이딩 윈도우
        for (int i = 0; i < 5; i++) {
            cnt[alpha[i]]++;
            if (max < cnt[alpha[i]]){
                max = cnt[alpha[i]];
                answer = alpha[i];
            }
        }

        int start = 0;
        int end = 4;
        for (int i = 5; i < alpha.length-4; i++) {
            cnt[alpha[start++]]--;
            cnt[alpha[++end]]++;
            if (max < cnt[alpha[end]]){
                max = cnt[alpha[end]];
                answer = alpha[end];
            }
        }
        System.out.println(answer + "," + max);
    }
}
