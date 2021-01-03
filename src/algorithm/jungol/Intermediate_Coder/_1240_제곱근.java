package algorithm.jungol.Intermediate_Coder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _1240_제곱근 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());

        if (N==1){
            System.out.println(1);
            return;
        }

        long low = 1;
        long high = N/2;
        long mid = (low+high)/2;
        while(low<=high){
            long val = mid*mid;

            if (val == N) {
                System.out.println(mid);
                return;
            }

//            while (val<=0){
//                high = mid-1;
//                mid = (low+high)/2;
//                val=mid*mid;
//            }

            if (val > N || val <= 0) high = mid-1;
            else low = mid + 1;

            mid = (low+high)/2;
        }
        System.out.println(mid);
    }
}

// 9223372036854775807
// 3037000499