package jpabook.jpashop.service;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    // 주문
    @Transactional
    public void order(Long ) {

    }

    // 주문 내역 조회

    // 주문 취소
}
