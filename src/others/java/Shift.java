package others.java;

public class Shift {
    public static void main(String[] args) {
        /* 시프트 연산 */
        // a<<b 이면 a를 b번 곱하기 2
        // a>>b 이면 a를 b번 나누기 2
        System.out.println(1 << 2); // 4
        System.out.println(3 << 3); // 24
        System.out.println(1 >> 2); // 0
        System.out.println(-16 >> 2); // -4

        // >>> : Unsigned Bit
        // 기존의 비트체계에서 맨 앞의 비트가 1인 경우 음수를 나타내도록 설계되어 있어서
        // 시프트시, >>인 경우에는 맨 앞 비트를 제외한 나머지 비트를 이동시켰지만
        // >>>는 맨 앞 비트를 포함해 모든 비트를 이동시킨다.
        System.out.println(-5 >>> 25); // 127

        // 부호비트 사용시 주의점
        // 1101 이면,
        // 앞의 부호비트가 1이므로 -8
        // 나머지 101은 5이므로
        // -8 + 5 = -3
    }
}
