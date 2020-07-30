package algorithm.programmers.summerWinterCoding;

public class Square {

    static long solution(long w, long h) {
        if (w == h) { // 같으면 둘 중 하나 만큼이 대각선임
            return w * h - w;
        }
        if (w == 1 || h == 1) { // 둘 중 하나라도 1이면 전부 사용 못함
            return 0;
        }

        long gcd = gcd(w, h);
        return (w * h) - (w + h - gcd);
    }

    static long gcd(long a, long b) {
        return a % b == 0 ? b : gcd(b, a % b);
    }

    public static void main(String[] args) {
        int w = 8;
        int h = 12;

        System.out.println(solution(w, h));
    }
}
