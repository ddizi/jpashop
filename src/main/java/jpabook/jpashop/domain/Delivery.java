package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery",fetch = LAZY)
    private Order order;

    @Embedded
    private Address address;

    //ORDINAL을 쓰면 안됨. 중간에 상태가 생기면 망함.
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //READY,COMP
}
