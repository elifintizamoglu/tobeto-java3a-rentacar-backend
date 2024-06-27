package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.requests.brand.CreateBrandRequest;
import com.tobeto.rentacar.business.dtos.requests.brand.UpdateBrandRequest;
import com.tobeto.rentacar.business.dtos.responses.brand.CreateBrandResponse;
import com.tobeto.rentacar.business.dtos.responses.brand.GetAllBrandResponse;
import com.tobeto.rentacar.business.dtos.responses.brand.GetBrandByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.brand.UpdateBrandResponse;
import com.tobeto.rentacar.core.utilities.results.Result;

import java.util.List;

public interface BrandService {

    CreateBrandResponse addBrand(CreateBrandRequest createBrandRequest);

    List<GetAllBrandResponse> getAllBrands();

    Result deleteBrandById(int id);

    UpdateBrandResponse updateBrandById(int id, UpdateBrandRequest updateBrandRequest);

    GetBrandByIdResponse getBrandById(int id);
}
