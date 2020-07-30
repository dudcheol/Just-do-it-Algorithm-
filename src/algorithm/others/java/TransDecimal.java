    package algorithm.others.java;

    public class TransDecimal {
        public static void main(String[] args) {
            /* 진수변환 10진수 -> 2,8,16진수 */
            int num = 50;
            String decToBin = Integer.toBinaryString(num);
            String decToOct = Integer.toOctalString(num);
            String decToHex = Integer.toHexString(num);

            System.out.println(num);
            System.out.println(decToBin);
            System.out.println(decToOct);
            System.out.println(decToHex);

            /* 진수변환 2,8,16 진수 -> 10진수 */
            int binToDec = Integer.parseInt(decToBin, 2);
            int octToDec = Integer.parseInt(decToOct, 8);
            int hexToDec = Integer.parseInt(decToHex, 16);

            System.out.println(binToDec);
            System.out.println(octToDec);
            System.out.println(hexToDec);
        }
    }
