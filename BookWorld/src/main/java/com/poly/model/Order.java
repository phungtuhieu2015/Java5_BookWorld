package com.poly.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_date")
    Date orderDate;

    Integer status;

    Integer payment_method;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "cancellation_date")
    Date cancellationDate;

    String cancellation_reason;


    @OneToMany(mappedBy = "order", cascade=CascadeType.ALL)
    List<OrderDetail> orderDetail;

}
