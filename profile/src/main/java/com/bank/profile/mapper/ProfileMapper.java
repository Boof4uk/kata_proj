package com.bank.profile.mapper;

import com.bank.profile.dto.request.ProfileRequestDto;
import com.bank.profile.dto.response.ProfileResponseDto;
import com.bank.profile.entity.ActualRegistration;
import com.bank.profile.entity.Passport;
import com.bank.profile.entity.Profile;
import com.bank.profile.repository.ActualRegistrationRepository;
import com.bank.profile.repository.PassportRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = { PassportMapper.class })
public interface ProfileMapper {
    ProfileResponseDto toDto(Profile profile);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passport", source = "passportId", qualifiedByName = "idToPassport")
    @Mapping(target = "actualRegistration", source = "actualRegistrationId", qualifiedByName = "idToActualRegistration")
    Profile toEntity(ProfileRequestDto profileRequestDto, @Context PassportRepository passportRepository, @Context ActualRegistrationRepository actualRegistrationRepository);
    List<ProfileResponseDto> toDTOList(List<Profile> list);
    @Named("idToPassport")
    default Passport idToPassport(Long id, @Context PassportRepository passportRepository) {
        return (id != null && passportRepository != null) ? passportRepository.findById(id).orElse(null) : null;

    }
    @Named("idToActualRegistration")
    default ActualRegistration idToActualRegistration(Long id, @Context ActualRegistrationRepository actualRegistrationRepository) {
        return (id != null && actualRegistrationRepository != null) ? actualRegistrationRepository.findById(id).orElse(null) : null;
    }
}
