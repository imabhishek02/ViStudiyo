package com.vistudiyo.registration.mapper;

import com.vistudiyo.registration.dtos.UpdateCustomerDto;
import com.vistudiyo.registration.entity.Editors;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-18T01:33:06+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class UpdateUserMapperImpl implements UpdateUserMapper {

    @Override
    public void updateUserFromDto(UpdateCustomerDto dto, Editors user) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getUserName() != null ) {
            user.setUserName( dto.getUserName() );
        }
        if ( dto.getName() != null ) {
            user.setName( dto.getName() );
        }
        if ( dto.getExperience() != null ) {
            user.setExperience( dto.getExperience() );
        }
        if ( dto.getCategory() != null ) {
            user.setCategory( dto.getCategory() );
        }
        if ( dto.getPassword() != null ) {
            user.setPassword( dto.getPassword() );
        }
    }
}
