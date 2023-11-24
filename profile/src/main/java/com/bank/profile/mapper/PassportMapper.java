package com.bank.profile.mapper;

import com.bank.profile.dto.request.PassportRequestDto;
import com.bank.profile.dto.response.PassportResponseDto;
import com.bank.profile.entity.Passport;
import com.bank.profile.entity.Registration;
import com.bank.profile.repository.RegistrationRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = { RegistrationMapper.class })
public interface PassportMapper {
    PassportResponseDto toDto(Passport passport);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registration", source = "registrationId", qualifiedByName = "idToRegistration")
    Passport toEntity(PassportRequestDto passportRequestDto, @Context RegistrationRepository registrationRepository);
    List<PassportResponseDto> toDTOList(List<Passport> list);
    @Named("idToRegistration")
    default Registration idToRegistration(Long id, @Context RegistrationRepository registrationRepository) {
        return (id != null && registrationRepository != null) ? registrationRepository.findById(id).orElse(null) : null;

    }
}
