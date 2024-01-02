package com.cleanhub.restapi.dto;

import com.cleanhub.restapi.entity.Contract;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CompanyHistory {
    private String companyName;
    private String landingPageRoute;
    private String state;
    private double quantity;
    private List<Contract> contracts;

}
