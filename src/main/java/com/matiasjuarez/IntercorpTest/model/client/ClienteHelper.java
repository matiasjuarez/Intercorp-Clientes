package com.matiasjuarez.IntercorpTest.model.client;

import com.matiasjuarez.IntercorpTest.service.DateHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ClienteHelper {
    private DateHandler dateHandler;

    @Autowired
    public ClienteHelper(DateHandler dateHandler) {
        this.dateHandler = dateHandler;
    }

    public LocalDate getBirthdayDate(ClienteDTO dto) {
        if (dto.getFechaNacimiento() != null) {
            return dateHandler.transformToLocalDate(dto.getFechaNacimiento());
        } else if (StringUtils.isNotEmpty(dto.getFechaNacimientoString())) {
            return dateHandler.transformToLocalDate(dto.getFechaNacimientoString());
        } else {
            return null;
        }
    }
}
