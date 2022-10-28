package com.challenge.models.raw.githubusercontent.com;

import java.util.List;

import lombok.Data;

@Data
public class Country {
    private String cca3;
    private List<String> borders;
}
