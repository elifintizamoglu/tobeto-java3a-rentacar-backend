package com.tobeto.rentacar.business.rules;

import com.tobeto.rentacar.business.constants.ModelMessages;
import com.tobeto.rentacar.core.utilities.exceptions.types.BusinessException;
import com.tobeto.rentacar.core.utilities.exceptions.types.ResourceNotFoundException;
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

        String trimmedName = modelName.trim();
        Optional<Model> model = modelRepository.findByNameIgnoreCase(trimmedName);

        if (model.isPresent()) {
            throw new BusinessException(ModelMessages.ModelNameAlreadyExists);
        }
    }

    public void isModelExists(int modelId) {

        boolean isExists = modelRepository.existsById(modelId);
        if (!isExists) {
            throw new ResourceNotFoundException(ModelMessages.ModelNotFound);
        }
    }
}
