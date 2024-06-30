package com.tobeto.rentacar.entities.concretes;

import com.tobeto.rentacar.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "cards")
public class Card extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(name = "cardNumber")
    private String cardNumber;

    @Column(name = "expirationMonth")
    public String expirationMonth;

    @Column(name = "expirationYear")
    public String expirationYear;

    @Column(name = "cvv")
    public String cvv;

    @Column(name = "cardHolderFullName")
    public String cardHolderFullName;
}
