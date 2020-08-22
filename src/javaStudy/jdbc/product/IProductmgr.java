package javaStudy.jdbc.product;

import java.util.List;

// 구현해야 되는 기능들 추상 메소드로 표시
public interface IProductmgr {
    // 1. 상품정보를 저장
    int add(Product p);

    // 2. 상품 전체 정보 검색
    List<Product> findAll();

    // 3. 상품명 검색 (상품명의 일부를 입력하면 그 문자열을 포함한 모든 상품정보 를 보여줌)
    List<Product> findByName(String name);

    // 4. 상품가격검색(상품가격을 입력하면 그 가격 이하의 모든 상품 정보를 보여줌)
    List<Product> findByPrice(int price);

    // 5. 제품번호로 검색
    Product findByNum(int num);

    // 6. 제품번호로 삭제
    boolean removeByNum(int num);

    // 7. 제품번호와 가격으로 같은 번호를 찾아 가격 업데이트 기능
    boolean updatePrice(int num, int price);

    // 8.
    List<Product> findByPrice(String type, int price1, int price2);
}
