package com.example.sherlock_chan_car_rental_service.controller;

import com.example.sherlock_chan_car_rental_service.dto.*;
import com.example.sherlock_chan_car_rental_service.service.VehicleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/vehicle")

public class VehicleController {
    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @ApiOperation(value = "Get all vehicles")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "What page number you want", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Number of items to return", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping
    public ResponseEntity<Page<VehicleDto>> findAll(@ApiIgnore Pageable pageable){
        return new ResponseEntity<>(vehicleService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/listById/{id}")
    public ResponseEntity<VehicleDto> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(vehicleService.findById(id), HttpStatus.OK);
    }
    @GetMapping("/listByType/{type_name}")
    public ResponseEntity<List<VehicleDto>> getVehiclesByType (@PathVariable("type_name") String type_name) {
        return new ResponseEntity<>(vehicleService.getVehiclesByType(type_name), HttpStatus.OK);
    }

    @GetMapping("/listByModel/{model_name}")
    public ResponseEntity<List<VehicleDto>> getVehiclesByModel (@PathVariable("model_name") String model_name) {
        return new ResponseEntity<>(vehicleService.getVehiclesByModel(model_name), HttpStatus.OK);
    }

    @GetMapping("/listByCompany/{company_name}")
    public ResponseEntity<List<VehicleDto>> getVehiclesByCompany(@PathVariable("company_name") String company_name){
        return new ResponseEntity<>(vehicleService.getVehiclesByCompany(company_name),HttpStatus.OK);
    }
//
//
//    @GetMapping("/listByCompanyAndType/{company_name}/{type_name}")
//    public ResponseEntity<List<VehicleDto>> getVehiclesByCompanyAndType(@PathVariable("company_name")String company_name,
//                                                                        @PathVariable("type_name")String type_name){
//
//    }

    @PostMapping("/createVehicle")
    public ResponseEntity<VehicleDto> createVehicle(@RequestBody @Valid VehicleCreateDto vehicleCreateDto){
        return new ResponseEntity<>(vehicleService.createVehicle(vehicleCreateDto),HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<VehicleDto> updateVehicle(@PathVariable("id")Long id, @RequestBody @Valid VehicleUpdateDto vehicleUpdateDto){
        return new ResponseEntity<>(vehicleService.updateVehicle(id,vehicleUpdateDto),HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    void deleteById(@PathVariable("id") Long id){
        vehicleService.deleteById(id);
    }
}
