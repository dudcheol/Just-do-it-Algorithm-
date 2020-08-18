package algorithm.javaStudy.thread;

import java.util.Date;

public class ThreadClock implements Runnable {

    public static void main(String[] args) {
        new ThreadClock();
    }

    ThreadClock() {
        // thread 생성, start
        Thread t = new Thread(this, "나 시계");
        t.start();
        System.out.println(t.getName());
    }

    @Override
    public void run() { // thread가 수행할 작업 내용. 화면에 1초 간격으로 시간을 표시
        while (true) {
            Date d = new Date(); // 년 월 일 시분초...
            System.out.println(d);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
