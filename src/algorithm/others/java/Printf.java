    package algorithm.others.java;

    public class Printf {
        public static void main(String[] args) {
            /* print의 비밀 */
            // 지금까지 %d를 사용했던 방법
            int num = 123;
            // String.format을 사용했다.
            System.out.println(String.format("%d", num));

            // 하지만! printf를 지원한다는 사실
            System.out.printf("%d\n", num);

            // 주의 - double형은 %lf가 아니라 %f임
            double num2 = Math.PI;
            System.out.printf("%f\n", num2);
        }
    }
