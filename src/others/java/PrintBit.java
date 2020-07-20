    package others.java;

    public class PrintBit {
        public static void main(String[] args) {
            /* Byte값을 Bit로 출력하기 */
            byte target = 3;
            String trans;
            System.out.println(trans = Integer.toBinaryString(target));

            /* Bit로 출력한 결과를 8자리로 맞추기 */
            System.out.println(String.format("%08d", Integer.parseInt(trans)));
        }
    }
