package com.interest.calculator.api.db.entities;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.Instant;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @Column(nullable = false)
    private Double amount;
    @Column(nullable = false)
    private Integer paymentNumber;
    @Column(columnDefinition="DATETIME", nullable = false)
    @Type(type="org.hibernate.type.InstantType")
    private Instant date;
}
