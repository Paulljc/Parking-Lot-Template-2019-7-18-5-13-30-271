package com.thoughtworks.parking_lot.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "park_order")
public class ParkOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "car_lisence", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "parkinglot_id", nullable = false)
    private ParkingLot parkingLot;

    @NotNull
    private String orderNumber;

    @NotNull
    private Date parkStartAt;

    @NotNull
    private Date parkEndAt;

    @NotNull
    private Boolean orderStatus;

    public ParkOrder(Car car, ParkingLot parkingLot, @NotNull String orderNumber, @NotNull Date parkStartAt, @NotNull Date parkEndAt, @NotNull Boolean orderStatus) {
        this.car = car;
        this.parkingLot = parkingLot;
        this.orderNumber = orderNumber;
        this.parkStartAt = parkStartAt;
        this.parkEndAt = parkEndAt;
        this.orderStatus = orderStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getParkStartAt() {
        return parkStartAt;
    }

    public void setParkStartAt(Date parkStartAt) {
        this.parkStartAt = parkStartAt;
    }

    public Date getParkEndAt() {
        return parkEndAt;
    }

    public void setParkEndAt(Date parkEndAt) {
        this.parkEndAt = parkEndAt;
    }

    public Boolean getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Boolean orderStatus) {
        this.orderStatus = orderStatus;
    }
}
