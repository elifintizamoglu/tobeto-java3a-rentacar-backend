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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
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

        var result = brandRepository.findAll();
        List<GetAllBrandResponse> response = result.stream()
                .map(brand -> modelMapperService.forResponse()
                        .map(brand, GetAllBrandResponse.class)).collect(Collectors.toList());

        return response;
    }

    @Override
    public void deleteBrandById(int id) {
        Brand brand = brandRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(BrandMessages.BrandNotFound));
        brand.setDeletedDate(LocalDateTime.now());
        brandRepository.delete(brand);
    }

    @Override
    public UpdateBrandResponse updateBrandById(int id, UpdateBrandRequest updateBrandRequest) {
        Brand brand = brandRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(BrandMessages.BrandNotFound));
        Brand updatedBrand = modelMapperService.forRequest().map(updateBrandRequest, Brand.class);

        brand.setUpdatedDate(LocalDateTime.now());
        brand.setName(updatedBrand.getName() != null ? updatedBrand.getName() : brand.getName());
        brandRepository.save(brand);

        UpdateBrandResponse response = modelMapperService.forResponse().map(brand, UpdateBrandResponse.class);

        return response;
    }

    @Override
    public GetBrandByIdResponse getBrandById(int id) {
        Brand brand = brandRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(BrandMessages.BrandNotFound));

        GetBrandByIdResponse response = modelMapperService.forResponse()
                .map(brand, GetBrandByIdResponse.class);

        return response;
    }
}
