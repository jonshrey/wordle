package org.gap.cycleshop.model;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class CustomerCycle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bookingId;

    @ManyToOne
    private Customer user;
    @ManyToOne
    private Cycle cycle;

    @CreationTimestamp
    private Date borrowedAt;
    public Integer getBookingId() {
        return bookingId;
    }
    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }
    public Customer getUser() {
        return user;
    }
    public void setUser(Customer user) {
        this.user = user;
    }
    public Cycle getCycle() {
        return cycle;
    }
    public void setCycle(Cycle cycle) {
        this.cycle = cycle;
    }
    public Date getBorrowedAt() {
        return borrowedAt;
    }
    public void setBorrowedAt(Date borrowedAt) {
        this.borrowedAt = borrowedAt;
    }

}
