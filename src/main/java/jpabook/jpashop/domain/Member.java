package jpabook.jpashop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.swing.text.StyleContext;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // default 값인 AUTO를 사용해도 됨.
    @Column(name = "member_id")
    private Long id;

//    @Column(name = "member_name") 얘는 왜 안하지. name 도 겹치는데..
    private String name;

    @Embedded // 양쪽중에 한쪽만 해두면 됨
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    public Member() {
    }

    //== 빌더 타입 ==//
    @Builder
    public Member(Long id, String name, Address address, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.orders = orders;
    }
}
