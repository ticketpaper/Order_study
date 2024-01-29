package com.example.order.OrderItem.dto.responseDto;

import com.example.order.Item.domain.Item;
import com.example.order.Ordering.domain.Ordering;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
public class OrderItemsResDto {
    private Long ordering_id;
    private Long item_id;
    private int quantity;
}
