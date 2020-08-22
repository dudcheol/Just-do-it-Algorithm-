package com.test;
// 사용자 정의 예외클래스 사용하기

//1. 사용자 정의 예외 클래스 -> extends Exception
class MyException extends Exception {
    String message;

    public MyException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MyException{" +
                "message='" + message + '\'' +
                '}';
    }
}

class YourException extends Exception {
    String message;

    public YourException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "YourException{" +
                "message='" + message + '\'' +
                '}';
    }
}

// 2. 예외 발생 메소드 사용하기
public class UserExceptionTest {

    public static void main(String[] args) {
        int result = 0;

        UserExceptionTest uet = new UserExceptionTest();

        try {
            result = uet.check(-1);
        } catch (MyException e) {
            System.out.println(e);
        }

        System.out.println(result);

        try {
            uet.doJob(8, 9);
        } catch (MyException | YourException e) { // 동시에 두개가 발생하지 않으므로 multi catch 사용
            System.out.println(e);
        }

        try {
            uet.doJob(8, 8);
        } catch (MyException | YourException e) {
            System.out.println(e);
        }

        try {
            uet.doJob(8, 7);
        } catch (MyException | YourException e) {
            System.out.println(e);
        }
    }

    // num1이 num2보다 크면 num1-num2 return
    // num1이 num2와 같으면 MyException 발생
    // num1이 num2보다 작으면 YourException 발생
    private void doJob(int num1, int num2) throws MyException, YourException {
        if (num1 > num2)
            System.out.println(num1 - num2);

        else if (num1 == num2)
            throw new MyException("결과값이 0입니다.");

        else
            throw new YourException("결과값이 음수입니다.");
    }

    private int check(int num) throws MyException {
        if (num > 0)
            return ++num;

        else
            throw new MyException("파라메터는 양수여야만 합니다.");// 예외 발생
    }
}
