package javaStudy.thread;

public class SyncTest implements Runnable {

    public static void main(String[] args) {
        new SyncTest();
    }

    SyncTest() {
        Thread tom = new Thread(this, "tom");
        Thread jerry = new Thread(this, "jerry");

        tom.start();
        jerry.start();
    }

    @Override
    public void run() { // <여기에 synchronized를 붙여도 통하지 않음. Runnable에서 이미 지정된 메소드이기 때문.
        kitchen();
        bedroom();
    }

    private synchronized void bedroom() { // 침실은 같이 들어올 수 없음
        String name = Thread.currentThread().getName();
        System.out.println(name + ", 침실에 들어 옴");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(name + ", 침실에서 나감");
    }

    private void kitchen() {
        String name = Thread.currentThread().getName();
        System.out.println(name + ", 부엌에 들어 옴");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(name + ", 부엌에서 나감");
    }
}
