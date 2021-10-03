package com.abc.shop.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

@Entity
@Table(name = "products")
@EntityListeners(AuditingEntityListener.class)
public class Product {
}
