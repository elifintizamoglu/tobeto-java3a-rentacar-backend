package com.tobeto.rentacar.api.controllers;

import com.tobeto.rentacar.business.abstracts.BrandService;
import com.tobeto.rentacar.business.dtos.requests.brand.CreateBrandRequest;
import com.tobeto.rentacar.business.dtos.requests.brand.UpdateBrandRequest;
import com.tobeto.rentacar.business.dtos.responses.brand.CreateBrandResponse;
import com.tobeto.rentacar.business.dtos.responses.brand.GetAllBrandResponse;
import com.tobeto.rentacar.business.dtos.responses.brand.GetBrandByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.brand.UpdateBrandResponse;
import com.tobeto.rentacar.core.utilities.results.Result;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/brands", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class BrandsController {

    private BrandService brandService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBrandResponse addBrand(@RequestBody @Valid CreateBrandRequest createBrandRequest) {
        return brandService.addBrand(createBrandRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllBrandResponse> getAllBrands() {
        return brandService.getAllBrands();
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Result> deleteBrandById(@PathVariable int id) {

        Result result = brandService.deleteBrandById(id);

        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }

    @PutMapping(path = "update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateBrandResponse updateBrandById(@PathVariable int id, @RequestBody @Valid UpdateBrandRequest updateBrandRequest) {
        return brandService.updateBrandById(id, updateBrandRequest);
    }

    @GetMapping(path = "getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetBrandByIdResponse getBrandById(@PathVariable int id) {
        return brandService.getBrandById(id);
    }

}
