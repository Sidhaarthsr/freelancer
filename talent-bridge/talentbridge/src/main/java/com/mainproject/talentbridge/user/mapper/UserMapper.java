package com.mainproject.talentbridge.user.mapper;

import org.mapstruct.Mapper;

import com.mainproject.talentbridge.user.dto.UserDTO;
import com.mainproject.talentbridge.user.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDTO(final User user);
}
