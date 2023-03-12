package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockExceptioin;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // ???
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
public abstract class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<Category>(); // 여기에 <> 안에 category는 왜 집어 넣어?

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockExceptioin("need more stock");
        }
        this.stockQuantity = restStock;
    }

    // 이건 안하네? 얜 뭐지
//    @Enumerated(value = EnumType.STRING)
//    private Dtype dtype;


    //== 비즈니스 로직 ==//

    // 상품 등록

    // ** 재고 추가

    // 주문했을 때, 재고 감소

    // 취소 했을 때, 재고 원상복귀
}
