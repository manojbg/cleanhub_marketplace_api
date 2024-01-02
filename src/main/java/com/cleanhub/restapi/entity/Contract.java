package com.cleanhub.restapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Getter
@Setter
@Table(name = "CONTRACTS")
public class Contract {
    @Id
    @Column(name = "UUID")
    private UUID UUID;
    @Column(name = "landingPageRoute")
    private String landingPageRoute;
    @Column(name = "createdAt")
    private Date createdAt;
    @Column(name = "startDate")
    private Date startDate;
    @Column(name = "endDate")
    private Date endDate;
    @Column(name = "period")
    private String period;
    @Column(name = "quantity")
    private double quantity;
    @Column(name = "recoveredQuantity")
    private double recoveredQuantity;
    @Column(name = "isFulfilled")
    private boolean isFulfilled;
    @Column(name = "type")
    private String type;

    public Contract() {

    }

    public Contract(UUID UUID, String landingPageRoute, double quantity,
                    double recoveredQuantity, String period, Date startDate, Date endDate,
                    Date createdAt, boolean isFulfilled, String type) {
        this.UUID = UUID;
        this.landingPageRoute = landingPageRoute;
        this.quantity = quantity;
        this.recoveredQuantity = recoveredQuantity;
        this.period = period;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
        this.isFulfilled = isFulfilled;
        this.type = type;
    }

}
