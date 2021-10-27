package com.abc.shop.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "promotions")
@EntityListeners(AuditingEntityListener.class)
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "promotion_name", nullable = false)
    private String promotionName;

    @Column(name = "promotion_description", nullable = false)
    private String promotionDescription;

    @Column(name = "promotion_price", nullable = false)
    private double promotionPrice;

    public Promotion() {
    }

    public Promotion(String promotionName, String promotionDescription, double promotionPrice) {
        this.promotionName = promotionName;
        this.promotionDescription = promotionDescription;
        this.promotionPrice = promotionPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public String getPromotionDescription() {
        return promotionDescription;
    }

    public void setPromotionDescription(String promotionDescription) {
        this.promotionDescription = promotionDescription;
    }

    public double getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(double promotionPrice) {
        this.promotionPrice = promotionPrice;
    }
}
