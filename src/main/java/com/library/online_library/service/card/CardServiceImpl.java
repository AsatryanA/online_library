package com.library.online_library.service.card;

import com.library.online_library.exeption.CommonValidationException;
import com.library.online_library.exeption.ResourceNotFoundException;
import com.library.online_library.mapper.card.CardMapper;
import com.library.online_library.model.dto.card.BaseCardDto;
import com.library.online_library.model.dto.card.CardCreateRequest;
import com.library.online_library.model.dto.card.CardDTO;
import com.library.online_library.model.dto.card.CardUpdateRequest;
import com.library.online_library.repository.card.CardRepository;
import com.library.online_library.service.find_one.FindOne;
import com.library.online_library.utils.CurrentUser;
import com.library.online_library.utils.constants.EntityName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final FindOne findOne;
    private final CardMapper cardMapper;
    private final CardRepository cardRepository;

    @Override
    @Transactional
    public CardDTO create(CardCreateRequest cardCreateRequest) {
        validateCard(cardCreateRequest);
        var cardEntity = cardMapper.toEntity(cardCreateRequest);
        cardEntity.setUser(findOne.getUserById(CurrentUser.getId()));
        cardRepository.save(cardEntity);
        return cardMapper.toDto(cardEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CardDTO> getAll() {
        var cardEntities = cardRepository.findAllByUserId(CurrentUser.getId());
        return cardEntities.stream().map(cardMapper::toDto).toList();
    }

    @Override
    @Transactional
    public void delete(Long cardId) {
        cardRepository.deleteById(cardId);
    }


    private void validateCard(BaseCardDto cardDto) {
        var cardNumber = cardDto.getCardNumber().trim().replace(" ", "");

        if (cardNumber.length() != 16) {
            throw new CommonValidationException("Card number must contain 16 symbols");
        }

        try {
            Long.parseLong(cardNumber);
        } catch (NumberFormatException e) {
            throw new CommonValidationException("Card number should contain only digits");
        }
/*        String expireDate = cardDto.getExpireMonth() + "/" + cardDto.getExpireYear();
        if (expireDate.matches("(0[1-9]|1[0-2])/[0-9][0-9]"))
            throw new CommonValidationException("Card expiration date incorrect");*/

        cardDto.setCardNumber(cardNumber);

    }


}
