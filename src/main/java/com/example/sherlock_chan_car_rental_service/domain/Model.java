package com.example.sherlock_chan_car_rental_service.domain;

import javax.persistence.*;

@Entity
@Table(name="MODEL")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    private String name;

    private Float price;
    @OneToOne(mappedBy = "model", cascade = CascadeType.ALL)
    private Vehicle vehicle;
    public Model(){

    }

    public Model(Long id, String name,Float price){
        this.id=id;
        this.name=name;
        this.price=price;
    }
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

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}