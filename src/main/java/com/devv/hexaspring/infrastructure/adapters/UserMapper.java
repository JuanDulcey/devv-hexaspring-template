package com.devv.hexaspring.infrastructure.adapters;

import com.devv.hexaspring.domain.model.User;
import com.devv.hexaspring.infrastructure.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UserEntity toEntity(User user);

    User toDomain(UserEntity entity);
}
