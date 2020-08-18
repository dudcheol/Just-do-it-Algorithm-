package algorithm.javaStudy.thread;

// Thread 클래스를 상속받아 thread를 사용하는 방법
// run(): callback method(사용자가 직접적으로 호출하지는 않지만 특정 상황이 되면 호출되는 메소드)
class Tiger extends Thread {

    // thread가 수행해야 할 작업내용을 갖는 메소드
    public void run() {
        System.out.println("thread is running..." + getName());
    }
}

public class TigerTest {

    public static void main(String[] args) {
        Tiger t1 = new Tiger(); // t1이 곧 thread
        t1.start(); // thread의 작업 시작을 알리는 메소드. by main thread << start를 하면 t1이 run함
//        t1.run(); // by main thread << 여기서 run을 해버리면 main thread가 run을 실행하는 것임.

        Tiger t2 = new Tiger();
        t2.start();

        Tiger t3 = new Tiger();
        t3.start();
    }
}
