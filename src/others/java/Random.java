package others.java;

public class Random {
    public static void main(String[] args) {
        /* 랜덤의 사용 */
        // 범위가 정해지는 랜덤한 숫자를 출력하는 3가지 방법
        // 범위는 1~6이라고 하자.
        int range = 6;
        // 1 - Math.random()
        System.out.printf("%d\n", (int) (Math.random() * range) + 1);

        // 2 - java.util.Random
        java.util.Random generator = new java.util.Random();
        System.out.printf("%d\n", generator.nextInt(range) + 1);

        // 3 - %
        System.out.printf("%d\n", ((int) (Math.random() * 100)) % range + 1);
    }
}
