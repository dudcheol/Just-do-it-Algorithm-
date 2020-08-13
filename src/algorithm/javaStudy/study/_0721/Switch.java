package algorithm.javaStudy.study._0721;

public class Switch {
    public static void main(String[] args) {
        int score = 90;
        char c = 'x';

        switch (c) { // key로 가능한 타입 : String, byte, short, int, char (long을 제외한 정수형 가능, double 안됨!)
            case 'X':
            case 'x':
                System.out.println(1);
                break;
            case 'y':
                System.out.println(2);
                break;
            case 'z':
                System.out.println(3);
                break;
            default:
                System.out.println(4);
                break;
        }
    }
}
