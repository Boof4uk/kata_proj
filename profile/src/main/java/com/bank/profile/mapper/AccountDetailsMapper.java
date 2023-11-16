package com.bank.profile.mapper;

import com.bank.profile.dto.request.AccountDetailsRequestDto;
import com.bank.profile.dto.response.AccountDetailsResponseDto;
import com.bank.profile.entity.AccountDetails;
import com.bank.profile.entity.Profile;
import com.bank.profile.repository.ProfileRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = { ProfileMapper.class })
public interface AccountDetailsMapper {
    @Mapping(target = "profileResponseDto", source = "profile")
    AccountDetailsResponseDto toDto(AccountDetails accountDetails);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "profile", source = "profileId", qualifiedByName = "idToProfile")
    AccountDetails toEntity(AccountDetailsRequestDto accountDetailsRequestDto, @Context ProfileRepository profileRepository);

    List<AccountDetailsResponseDto> toDTOList(List<AccountDetails> list);
    @Named("idToProfile")
    default Profile idToProfile(Long id, @Context ProfileRepository profileRepository) {
        return (id != null && profileRepository != null) ? profileRepository.findById(id).orElse(null) : null;

    }
}


