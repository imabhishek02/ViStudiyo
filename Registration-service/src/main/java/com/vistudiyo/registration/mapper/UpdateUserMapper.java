package com.vistudiyo.registration.mapper;

import com.vistudiyo.registration.dtos.UpdateCustomerDto;
import com.vistudiyo.registration.entity.Editors;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@Mapper(componentModel = "spring")
public interface UpdateUserMapper {

    @BeanMapping(
            nullValuePropertyMappingStrategy =
                    NullValuePropertyMappingStrategy.IGNORE
    )
    void updateUserFromDto(UpdateCustomerDto dto,
                           @MappingTarget Editors user);
}

