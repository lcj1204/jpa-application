package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    /**
     * ItemService 는 단순히 ItemRepository 에 위임만 하는 클래스 이다.
     */

    private final ItemRepository itemRepository;

    // 상품 등록 -> 핵심 비즈니스 로직은 엔티티에 구현하자!
    @Transactional
    public void savedItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item item = itemRepository.findOne(itemId);
        item.change(name, price, stockQuantity);
//        item.setName(name);
//        item.setPrice(price);
//        item.setStockQuantity(stockQuantity);
    }
    // 전체 상품 목록 조회
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    // 상품 조회
    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

    // 상품 수정 -> 얘는 왜 안해? 아 기능이 없구나!
}
