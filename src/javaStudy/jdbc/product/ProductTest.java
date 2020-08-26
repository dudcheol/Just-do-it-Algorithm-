package javaStudy.jdbc.product;

import java.util.List;
import java.util.Scanner;

public class ProductTest {
    private static Scanner sc = new Scanner(System.in);
    // 타입은 인터페이스로 한다.
    // 왜? 인터페이스를 오버라이딩하지 않은 메소드들을 사용하지 못하게 하기 위해서
    // 클라이언트에게 사용가능한 메소드를 인터페이스에 담아서 보내주기 때문에 클라이언트는 인터페이스에 정의된 메소드만을 사용하도록 해야 함
    private static IProductmgr productMgr = ProductMgrImpl.getInstance(); // 인터페이스에 정의된 메소드만을 사용할 수 있음
//    private static ProductMgrImpl productMgr2 = ProductMgrImpl.getInstance();

    public static void main(String[] args) {

        while (true) {
            System.out.println("======================================================");
            System.out.println("명령어를 입력하세요.");
            System.out.println("1 상품정보 저장\n" +
                    "2 상품정보 전체를 검색\n" +
                    "3 상품명 검색 \n" +
                    "4 상품 가격 검색 \n" +
                    "5 제품번호로 검색\n" +
                    "6 제품번호로 삭제\n" +
                    "7 제품번호와 가격으로 같은 번호를 찾아 가격 업데이트\n" +
                    "8 제품종류와 원하는 가격대로 상품 검색");
            System.out.println("======================================================");
            System.out.print(" > ");

            switch (sc.nextInt()) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    printProducts(productMgr.findAll());
                    break;
                case 3:
                    System.out.print("찾을 제품의 이름을 입력하세요.\n > ");
                    printProducts(productMgr.findByName(sc.next()));
                    break;
                case 4:
                    System.out.print("찾을 제품의 가격을 입력하세요.\n > ");
                    printProducts(productMgr.findByPrice(sc.nextInt()));
                    break;
                case 5:
                    System.out.print("찾을 제품의 번호를 입력하세요.\n > ");
                    printProducts(productMgr.findByNum(sc.nextInt()));
                    break;
                case 6:
                    System.out.print("삭제할 제품의 번호를 입력하세요.\n > ");
                    printProducts(productMgr.removeByNum(sc.nextInt()));
                    break;
                case 7:
                    System.out.print("업데이트할 제품의 번호를 입력하세요.\n > ");
                    int num = sc.nextInt();
                    System.out.print("업데이트할 제품의 변경 가격을 입력하세요.\n > ");
                    int price = sc.nextInt();
                    printProducts(productMgr.updatePrice(num, price));
                    break;
                case 8:
                    System.out.print("찾고싶은 제품의 종류를 입력하세요. \n > ");
                    String type = sc.next();
                    System.out.print("원하는 가격대를 입력하세요. 얼마 이상을 원하십니까? \n > ");
                    int price1 = sc.nextInt();
                    System.out.print("원하는 가격대를 입력하세요. 얼마 이하를 원하십니까? \n > ");
                    int price2 = sc.nextInt();
                    printProducts(productMgr.findByPrice(type, price1, price2));
                    break;
                default:
                    sc.close();
                    return;
            }
        }
    }

    private static void addProduct() {
        System.out.println("요구사항에 맞게 정보를 입력하세요.");

        System.out.println("제품번호");
        System.out.print(" > ");
        int num = sc.nextInt();

        System.out.println("제품명");
        System.out.print(" > ");
        String name = sc.next();

        System.out.println("제조사");
        System.out.print(" > ");
        String maker = sc.next();

        System.out.println("제품종류");
        System.out.print(" > ");
        String type = sc.next();

        System.out.println("가격");
        System.out.print(" > ");
        int price = sc.nextInt();

        System.out.println("재고수량");
        System.out.print(" > ");
        int quantity = sc.nextInt();

        printProducts(productMgr.add(new Product(num, name, maker, type, price, quantity)));
    }

    private static void printProducts(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("제품을 찾을 수 없습니다.");
            return;
        }
        for (Product product : products)
            System.out.println(product);
    }

    private static void printProducts(Product product) {
        if (product == null) System.out.println("제품을 찾을 수 없습니다.");
        else System.out.println(product);
    }

    private static void printProducts(boolean b) {
        if (b) System.out.println("성공적으로 수행되었습니다.");
        else System.out.println("존재하지 않는 제품입니다.");
    }

    private static void printProducts(int n) {
        System.out.println("총 " + n + "개의 작업이 수행되었습니다.");
    }
}
