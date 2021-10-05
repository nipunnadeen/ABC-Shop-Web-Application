package com.abc.shop.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role_id", nullable = false)
    private int roleId;

//    @Column(name = "created_at", nullable = false)
//    @CreatedDate
//    @CreationTimestamp
//    private Date createdAt;
//
//    @Column(name = "created_by", nullable = false)
//    @CreatedBy
//    private int createdBy;
//
//    @Column(name = "is_deleted", nullable = false)
//    @SQLDelete(sql = "UPDATE users SET is_Deleted=true WHERE id=?")
//    @Where(clause = "is_deleted = false")
//    private boolean isDeleted;
//
//    @Column(name = "updated_at", nullable = false)
//    private Date updatedAt;
//
//    @Column(name = "updated_by", nullable = false)
//    private int updatedBy;

    public User() {
    }

    public User(String name, int age, String email, String password) {
        super();
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

//    /**
//     * Gets created at.
//     *
//     * @return the created at
//     */
//    public Date getCreatedAt() {
//        return createdAt;
//    }
//
//    /**
//     * Sets created at.
//     *
//     * @param createdAt the created at
//     */
//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    /**
//     * Gets created by.
//     *
//     * @return the created by
//     */
//    public int getCreatedBy() {
//        return createdBy;
//    }
//
//    /**
//     * Sets created by.
//     *
//     * @param createdBy the created by
//     */
//    public void setCreatedBy(int createdBy) {
//        this.createdBy = createdBy;
//    }
//
//    /**
//     * Gets updated at.
//     *
//     * @return the updated at
//     */
//    public Date getUpdatedAt() {
//        return updatedAt;
//    }
//
//    /**
//     * Sets updated at.
//     *
//     * @param updatedAt the updated at
//     */
//    public void setUpdatedAt(Date updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//
//    /**
//     * Gets updated by.
//     *
//     * @return the updated by
//     */
//    public int getUpdatedBy() {
//        return updatedBy;
//    }
//
//    /**
//     * Sets updated by.
//     *
//     * @param updatedBy the updated by
//     */
//    public void setUpdatedBy(int updatedBy) {
//        this.updatedBy = updatedBy;
//    }
//
//    public boolean isDeleted() {
//        return isDeleted;
//    }
//
//    public void setDeleted(boolean deleted) {
//        isDeleted = deleted;
//    }
}
