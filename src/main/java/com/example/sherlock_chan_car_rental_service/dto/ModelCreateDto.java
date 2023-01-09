package com.example.sherlock_chan_car_rental_service.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ModelCreateDto {

    private Long id;
    @NotEmpty(message = "Text cant be empty")
    private String name;
    @NotNull
    private Float price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
