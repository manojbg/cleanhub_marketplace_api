package com.cleanhub.restapi.entity;

import com.fasterxml.jackson.databind.JsonNode;

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
@Table(name = "COMPANY")
public class Company {
    @Id
    @Column(name = "UUID")
    private UUID UUID;
    @Column(name = "companyName")
    private String companyName;
    @Column(name = "landingPageRoute")
    private String landingPageRoute;
    @Column(name = "state")
    private String state;
    @Column(name = "quantity")
    private double quantity;
    @Column(name = "recoveredQuantity")
    private double recoveredQuantity;
    @Column(name = "contractStartDate")
    private Date contractStartDate;

    public Company() {
        // Default constructor
    }

    private Company(String uuid, String companyName, String landingPageRoute) {
        this.UUID = java.util.UUID.fromString(uuid);
        this.companyName = companyName;
        this.landingPageRoute = landingPageRoute;
    }

    public static Company create(JsonNode node) {
        JsonNode jnode = node.get("logo");
        String uuid = jnode.get("uuid").asText();
        String name = node.get("companyName").asText();
        String landingRoutePage = node.get("landingPageRoute").asText();
        return new Company(uuid, name, landingRoutePage);
    }


}
