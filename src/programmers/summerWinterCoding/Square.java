package programmers.summerWinterCoding;

public class Square {

    static long solution(int w, int h) {
        long answer = 1;

        // w,h 중 더 긴 것, 더 작은 것 찾기
        long greater = Math.max(w, h);
        long smaller = Math.min(w, h);

        long mok = greater % smaller == 0 ? greater / smaller : greater / smaller + 1;

        return (w * h) - (smaller * mok);
    }

    public static void main(String[] args) {
        int w = 8;
        int h = 12;

        System.out.println(solution(w, h));
    }
}
