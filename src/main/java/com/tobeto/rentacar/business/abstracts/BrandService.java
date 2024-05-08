package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.requests.brand.CreateBrandRequest;
import com.tobeto.rentacar.business.dtos.requests.brand.UpdateBrandRequest;
import com.tobeto.rentacar.business.dtos.responses.brand.CreatedBrandResponse;
import com.tobeto.rentacar.business.dtos.responses.brand.GetAllBrandResponse;
import com.tobeto.rentacar.business.dtos.responses.brand.GetBrandByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.brand.UpdateBrandResponse;

import java.util.List;

public interface BrandService {

    CreatedBrandResponse addBrand(CreateBrandRequest createBrandRequest);

    List<GetAllBrandResponse> getAllBrands();

    void deleteBrandById(int id);

    UpdateBrandResponse updateBrandById(int id, UpdateBrandRequest updateBrandRequest);

    GetBrandByIdResponse getBrandById(int id);
}
