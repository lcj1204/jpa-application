package jpabook.jpashop.web;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderForm {

    private Member members;
    private Item items;
    private int count;
}
