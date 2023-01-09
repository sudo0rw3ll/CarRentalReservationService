package com.example.sherlock_chan_car_rental_service.dto;

import com.example.sherlock_chan_car_rental_service.domain.Company;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ScheduleDto {

    private Long id;

    private LocalDate starting_date;
    private LocalDate ending_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStarting_date() {
        return starting_date;
    }

    public void setStarting_date(LocalDate starting_date) {
        this.starting_date = starting_date;
    }

    public LocalDate getEnding_date() {
        return ending_date;
    }

    public void setEnding_date(LocalDate ending_date) {
        this.ending_date = ending_date;
    }
}
