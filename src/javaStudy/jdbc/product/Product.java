package javaStudy.jdbc.product;

// VO : 값을 저장하기 위한 객체
// == DTO (Data Transfer Object)
public class Product {
    // 필드 선언
    // Field -> table 안의 컬럼과 동일하게 맞춰서 선언
    private int num;
    private String name;
    private String maker;
    private String type;
    private int price;
    private int qty;

    public Product() {
    }

    public Product(int num, String name, String maker, String type, int price, int qty) {
        this.num = num;
        this.name = name;
        this.maker = maker;
        this.type = type;
        this.price = price;
        this.qty = qty;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Product{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", maker='" + maker + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                '}';
    }
}
