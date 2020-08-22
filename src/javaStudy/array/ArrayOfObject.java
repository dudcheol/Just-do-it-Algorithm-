package javaStudy.array;

public class ArrayOfObject {
    public static void main(String[] args) {
        Car[] cars = new Car[5];

        for (int i = 0; i < cars.length; i++) {
            /* 주의! Object로 Array를 만들 때 Array 요소별로 Object를 생성해주어야 함 */
            cars[i] = new Car();
            cars[i].setPrice(i * 2000);
        }

        for (Car car : cars) {
            System.out.println(car.getPrice());
        }
    }
}

class Car {
    int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
