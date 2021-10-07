package com.abc.shop.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product productId;

//    @Column(name = "product_id", nullable = false)
//    private long productId;

    @Column(name = "order_quantity", nullable = false)
    private int orderQuantity;

    @Column(name = "order_price", nullable = false)
    private double orderPrice;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    @CreationTimestamp
    private Date createdAt;

//    @Column(name = "created_by", nullable = false)
//    @CreatedBy
//    private int createdBy;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "updated_by")
    private int updatedBy;

    @Column(name = "deleted_at")
    private Date deletedAt;

    public Order() {
    }

//    public Order(long productId, int orderQuantity, double orderPrice, int createdBy, int updatedBy) {
//        this.productId = productId;
//        this.orderQuantity = orderQuantity;
//        this.orderPrice = orderPrice;
//        this.createdBy = createdBy;
//        this.updatedBy = updatedBy;
//    }


    public Order(Product productId, int orderQuantity, double orderPrice, User createdBy, int updatedBy) {
        this.productId = productId;
        this.orderQuantity = orderQuantity;
        this.orderPrice = orderPrice;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    public long getProductId() {
//        return productId;
//    }
//
//    public void setProductId(long productId) {
//        this.productId = productId;
//    }


    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

//    public int getCreatedBy() {
//        return createdBy;
//    }
//
//    public void setCreatedBy(int createdBy) {
//        this.createdBy = createdBy;
//    }


    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }
}
