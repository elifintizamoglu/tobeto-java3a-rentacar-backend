package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.CardService;
import com.tobeto.rentacar.business.constants.CardMessages;
import com.tobeto.rentacar.business.dtos.requests.card.CreateCardRequest;
import com.tobeto.rentacar.core.utilities.mapping.ModelMapperService;
import com.tobeto.rentacar.core.utilities.results.Result;
import com.tobeto.rentacar.dataAccess.abstracts.CardRepository;
import com.tobeto.rentacar.entities.concretes.Card;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CardManager implements CardService {

    private CardRepository cardRepository;
    private ModelMapperService modelMapperService;

    @Override
    public Result addCard(CreateCardRequest createCardRequest) {

        try {
            Card card = this.modelMapperService.forRequest().map(createCardRequest, Card.class);
            card.setId(0);
            card.setCreatedDate(LocalDateTime.now());
            cardRepository.save(card);
            return new Result(true, CardMessages.CardSaved);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
