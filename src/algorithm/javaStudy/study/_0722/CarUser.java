package algorithm.javaStudy.study._0722;

public class CarUser {
    public static void main(String[] args) {
        // jdk가 제공해주는 클래스를 이용해서 필요한 객체를 생성해서 사용
//        Button b = new Button();
//        Frame f = new Frame(); // 창
//        f.add(f);
//        f.setSize(200, 300);
//        f.setVisible(true);


        int age; // main꺼. 메소드 안에 있는 변수 => 지역 변수. 기본값을 가지지 않음
        Car c1 = new Car();
        Car c2 = new Car();
        Car c3 = new Car();
        Car c4 = new Car();

        c1.run();
        c1.stop();
        c1.num = 1234;
        c1.model = "avante";
        c1.info();

        Car.hiCar();
    }
}
