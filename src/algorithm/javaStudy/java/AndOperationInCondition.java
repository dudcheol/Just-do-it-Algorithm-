    package algorithm.javaStudy.java;

    public class AndOperationInCondition {
        public static void main(String[] args) {
            /* 조건문에서 &와 &&의 차이 */
            int a = 3;
            // & 앞의 조건문이 거짓이더라도 실행된다.
            if (a > 5 & printTest()){
                System.out.println("test1");
            }

            // && 앞의 조건문이 거짓이면 뒤에 조건문은 더이상 확인하지 않는다.
            if (a > 5 && printTest()){
                System.out.println("test2");
            }
        }

        static boolean printTest(){
            System.out.println("this is test text.");
            return true;
        }
    }
