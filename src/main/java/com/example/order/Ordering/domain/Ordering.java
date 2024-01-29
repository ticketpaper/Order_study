package com.example.order.Ordering.domain;

import com.example.order.Item.domain.Item;
import com.example.order.Member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class Ordering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Member member;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    LocalDateTime createdTime;
    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    LocalDateTime updatedTime;

    public void orderUpdate(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void stockManage(Ordering ordering, Item item, int quantity) {
        int stockQuantity = item.getStockQuantity();
        if (ordering.orderStatus.equals(OrderStatus.ORDERED)) {
            if (stockQuantity - quantity < 0) {
                throw new NullPointerException();
            } else {
                item.setStockQuantity(stockQuantity - quantity);
            }
        } else if (ordering.orderStatus.equals(OrderStatus.CANCELED)) {
            item.setStockQuantity(stockQuantity + quantity);
        }
    }
}
