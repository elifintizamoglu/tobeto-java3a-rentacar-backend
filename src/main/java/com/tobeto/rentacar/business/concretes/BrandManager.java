package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.BrandService;
import com.tobeto.rentacar.business.dtos.requests.brand.CreateBrandRequest;
import com.tobeto.rentacar.business.dtos.responses.brand.CreatedBrandResponse;
import com.tobeto.rentacar.business.dtos.responses.brand.GetAllBrandResponse;

import com.tobeto.rentacar.business.rules.BrandBusinessRules;
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
    public CreatedBrandResponse add(CreateBrandRequest createBrandRequest) {

        brandBusinessRules.brandNameCanNotBeDuplicated(createBrandRequest.getName());

        Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
        brand.setCreatedDate(LocalDateTime.now());
        Brand createdBrand = this.brandRepository.save(brand);

        CreatedBrandResponse createdBrandResponse = this.modelMapperService.forResponse().map(createdBrand, CreatedBrandResponse.class);
        return createdBrandResponse;
    }

    @Override
    public List<GetAllBrandResponse> getAll() {

        var result = brandRepository.findAll();
        List<GetAllBrandResponse> response = result.stream()
                .map(brand -> modelMapperService.forResponse()
                        .map(brand, GetAllBrandResponse.class)).collect(Collectors.toList());

        return response;
    }
}
