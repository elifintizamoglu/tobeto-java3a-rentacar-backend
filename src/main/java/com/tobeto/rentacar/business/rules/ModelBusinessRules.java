package com.tobeto.rentacar.business.rules;

import com.tobeto.rentacar.core.utilities.exceptions.types.BusinessException;
import com.tobeto.rentacar.dataAccess.abstracts.ModelRepository;
import com.tobeto.rentacar.entities.concretes.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ModelBusinessRules {

    ModelRepository modelRepository;

    public void modelNameCanNotBeDuplicated(String modelName) {
        Optional<Model> model = modelRepository.findByNameIgnoreCase(modelName);
        if (model.isPresent()) {
            throw new BusinessException("ModelExists");
        }
    }
}
