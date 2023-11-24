package com.bank.profile.mapper;

import com.bank.profile.dto.request.ActualRegistrationRequestDto;
import com.bank.profile.dto.response.ActualRegistrationResponseDto;
import com.bank.profile.entity.ActualRegistration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ActualRegistrationMapper {
   ActualRegistrationResponseDto toDto(ActualRegistration actualRegistration);
    @Mapping(target = "id", ignore = true)
    ActualRegistration toEntity(ActualRegistrationRequestDto actualRegistrationRequestDto);
    List<ActualRegistrationResponseDto> toDTOList(List<ActualRegistration> list);
}
