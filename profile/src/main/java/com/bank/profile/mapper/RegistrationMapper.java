package com.bank.profile.mapper;

import com.bank.profile.dto.request.RegistrationRequestDto;
import com.bank.profile.dto.response.RegistrationResponseDto;
import com.bank.profile.entity.Registration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {
    RegistrationResponseDto toDto(Registration registration);
    @Mapping(target = "id", ignore = true)
    Registration toEntity(RegistrationRequestDto registrationRequestDto);
    List<RegistrationResponseDto> toDTOList(List<Registration> list);
}
