package com.example.sherlock_chan_car_rental_service.service;

import com.example.sherlock_chan_car_rental_service.dto.ReservationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {

    Page<ReservationDto> findAll(Pageable pageable);
    List<ReservationDto> findByCity(String city_name);
    List<ReservationDto> findByCompany(String company_name);

    List<ReservationDto> findByDate(LocalDate start_date, LocalDate end_date);

}
