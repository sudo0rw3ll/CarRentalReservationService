package com.example.sherlock_chan_car_rental_service.mapper;

import com.example.sherlock_chan_car_rental_service.domain.Model;
import com.example.sherlock_chan_car_rental_service.dto.ModelCreateDto;
import com.example.sherlock_chan_car_rental_service.dto.ModelDto;
import com.example.sherlock_chan_car_rental_service.exception.NotFoundException;
import com.example.sherlock_chan_car_rental_service.repository.VehicleRepository;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {

    private VehicleMapper vehicleMapper;
    private VehicleRepository vehicleRepository;

    public ModelMapper(VehicleMapper vehicleMapper, VehicleRepository vehicleRepository){
        this.vehicleMapper = vehicleMapper;
        this.vehicleRepository = vehicleRepository;
    }

    public ModelDto modelToModelDto(Model model){
        ModelDto modelDto=new ModelDto();
        modelDto.setId(model.getId());
        modelDto.setName(model.getName());
        modelDto.setPrice(model.getPrice());
        return modelDto;
    }

    public Model modelCreateDtoToModel(ModelCreateDto modelCreateDto){
        Model model=new Model();
        model.setId(modelCreateDto.getId());
        model.setName(modelCreateDto.getName());
        model.setPrice(modelCreateDto.getPrice());

        model.setVehicle(vehicleRepository
                .findById(modelCreateDto.getVehicle_id())
                .orElseThrow(() -> new NotFoundException(String.format("Vehicle with provided id %d has not been found", modelCreateDto.getVehicle_id()))));

        return model;
    }
}