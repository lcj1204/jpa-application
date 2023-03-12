package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        // 만약 상품이 이미 있으면..?
        if (item.getId() == null) { // id가 없으면 신규로 보고 persist() 실행
            em.persist(item);
        } else {                    // id가 있으면 DB에 저장된 엔티티를 수정한다고 보고, merge()를 실행. 자세한 내용은 다음에..
            em.merge(item); // 이게 뭐누
        }

    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() { // 쿼리를 직접 날려줘야댐..
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

}
