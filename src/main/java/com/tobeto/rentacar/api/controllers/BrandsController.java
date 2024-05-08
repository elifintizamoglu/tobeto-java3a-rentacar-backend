package com.tobeto.rentacar.api.controllers;

import com.tobeto.rentacar.business.abstracts.BrandService;
import com.tobeto.rentacar.business.dtos.requests.brand.CreateBrandRequest;
import com.tobeto.rentacar.business.dtos.requests.brand.UpdateBrandRequest;
import com.tobeto.rentacar.business.dtos.responses.brand.CreateBrandResponse;
import com.tobeto.rentacar.business.dtos.responses.brand.GetAllBrandResponse;
import com.tobeto.rentacar.business.dtos.responses.brand.GetBrandByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.brand.UpdateBrandResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/brands")
@AllArgsConstructor
public class BrandsController {

    private BrandService brandService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBrandResponse addBrand(@RequestBody @Valid CreateBrandRequest createBrandRequest) {
        return brandService.addBrand(createBrandRequest);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllBrandResponse> getAllBrands() {
        return brandService.getAllBrands();
    }

    @DeleteMapping("delete/{id}")
    public void deleteBrandById(@PathVariable int id) {
        brandService.deleteBrandById(id);
    }

    @PutMapping("update/{id}")
    public UpdateBrandResponse updateBrandById(@PathVariable int id, @RequestBody UpdateBrandRequest updateBrandRequest) {
        return brandService.updateBrandById(id, updateBrandRequest);
    }

    @GetMapping(path = "getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetBrandByIdResponse getBrandById(@PathVariable int id) {
        return brandService.getBrandById(id);
    }

}
