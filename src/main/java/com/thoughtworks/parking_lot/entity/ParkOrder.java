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

    @Column(nullable = false)
    private String carLisence;

    @Column(nullable = false)
    private String parkingLotName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parking_lot_id")
    private ParkingLot parkingLot;

    @NotNull
    private Date parkStartAt;

    @NotNull
    private Date parkEndAt;

    @NotNull
    private Boolean status;

    public ParkOrder(String carLisence, String parkingLotName, ParkingLot parkingLot, @NotNull Date parkStartAt, @NotNull Date parkEndAt, @NotNull Boolean status) {
        this.carLisence = carLisence;
        this.parkingLotName = parkingLotName;
        this.parkingLot = parkingLot;
        this.parkStartAt = parkStartAt;
        this.parkEndAt = parkEndAt;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarLisence() {
        return carLisence;
    }

    public void setCarLisence(String carLisence) {
        this.carLisence = carLisence;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }

    public void setParkingLotName(String parkingLotName) {
        this.parkingLotName = parkingLotName;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
