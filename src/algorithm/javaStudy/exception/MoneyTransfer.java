package com.test;

public class MoneyTransfer {

    public void deposit() throws Exception{ // 입금
        // 예외 발생
        throw new Exception();
    }

    public void withdraw(){ // 출금
        System.out.println("출금되었습니다.");
    }

    public static void main(String[] args) {
        MoneyTransfer t = new MoneyTransfer();
        try {
            t.transfer(); // 이체 메소드
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void transfer() throws Exception {
        withdraw(); // 출금
        deposit(); // 입금
    }
}
