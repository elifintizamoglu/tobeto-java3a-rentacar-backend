package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.BrandService;
import com.tobeto.rentacar.business.constants.BrandMessages;
import com.tobeto.rentacar.business.dtos.requests.brand.CreateBrandRequest;
import com.tobeto.rentacar.business.dtos.requests.brand.UpdateBrandRequest;
import com.tobeto.rentacar.business.dtos.responses.brand.CreateBrandResponse;
import com.tobeto.rentacar.business.dtos.responses.brand.GetAllBrandResponse;
import com.tobeto.rentacar.business.dtos.responses.brand.GetBrandByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.brand.UpdateBrandResponse;
import com.tobeto.rentacar.business.rules.BrandBusinessRules;
import com.tobeto.rentacar.core.utilities.exceptions.types.ResourceNotFoundException;
import com.tobeto.rentacar.core.utilities.mapping.ModelMapperService;
import com.tobeto.rentacar.dataAccess.abstracts.BrandRepository;
import com.tobeto.rentacar.entities.concretes.Brand;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Primary
public class BrandManager implements BrandService {

    private BrandRepository brandRepository;
    private ModelMapperService modelMapperService;
    private BrandBusinessRules brandBusinessRules;

    @Override
    public CreateBrandResponse addBrand(CreateBrandRequest createBrandRequest) {

        brandBusinessRules.brandNameCanNotBeDuplicated(createBrandRequest.getName());

        Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
        brand.setCreatedDate(LocalDateTime.now());
        Brand createdBrand = this.brandRepository.save(brand);

        CreateBrandResponse createdBrandResponse = this.modelMapperService.forResponse().map(createdBrand, CreateBrandResponse.class);
        return createdBrandResponse;
    }

    @Override
    public List<GetAllBrandResponse> getAllBrands() {

        List<Brand> brands = brandRepository.findAllByOrderByName();
        List<GetAllBrandResponse> response = brands.stream().map(brand -> modelMapperService.forResponse()
                        .map(brand, GetAllBrandResponse.class)).collect(Collectors.toList());

        return response;
    }

    @Override
    public void deleteBrandById(int id) {

        brandBusinessRules.isBrandExists(id);
        brandRepository.deleteById(id);
    }


    @Override
    public UpdateBrandResponse updateBrandById(int id, UpdateBrandRequest updateBrandRequest) {

        Brand brand = brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(BrandMessages.BrandNotFound));

        brandBusinessRules.brandNameCanNotBeDuplicated(updateBrandRequest.getName());
        Brand updatedBrand = modelMapperService.forRequest().map(updateBrandRequest, Brand.class);

        brand.setName(updatedBrand.getName());
        brand.setUpdatedDate(LocalDateTime.now());
        brandRepository.save(brand);

        UpdateBrandResponse response = modelMapperService.forResponse().map(brand, UpdateBrandResponse.class);
        return response;
    }

    @Override
    public GetBrandByIdResponse getBrandById(int id) {

        Brand brand = brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(BrandMessages.BrandNotFound));

        GetBrandByIdResponse response = modelMapperService.forResponse().map(brand, GetBrandByIdResponse.class);
        return response;
    }
}
