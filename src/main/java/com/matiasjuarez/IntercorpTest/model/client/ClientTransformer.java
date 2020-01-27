package com.matiasjuarez.IntercorpTest.model.client;

import com.matiasjuarez.IntercorpTest.exceptions.ErrorMessages;
import com.matiasjuarez.IntercorpTest.exceptions.ValidationException;
import com.matiasjuarez.IntercorpTest.model.AbstractTransformer;
import com.matiasjuarez.IntercorpTest.service.DateHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class ClientTransformer extends AbstractTransformer<ClienteDTO, ClienteEntity> {
    private DateHandler dateHandler;
    private ClienteHelper clienteHelper;
    private ErrorMessages errorMessages;

    @Autowired
    public ClientTransformer(DateHandler dateHandler, ClienteHelper clienteHelper, ErrorMessages errorMessages) {
        this.dateHandler = dateHandler;
        this.clienteHelper = clienteHelper;
        this.errorMessages = errorMessages;
    }

    public ClienteDTO convertToDTO(ClienteEntity clienteEntity) {
        ClienteDTO DTO = new ClienteDTO();
        DTO.setId(clienteEntity.getId());
        DTO.setNombre(clienteEntity.getNombre());
        DTO.setApellido(clienteEntity.getApellido());
        DTO.setEdad(clienteEntity.getEdad());
        DTO.setFechaNacimiento(dateHandler.transformToMillis(clienteEntity.getFechaNacimiento()));
        DTO.setFechaNacimientoString(dateHandler.transformToFormattedString(DTO.getFechaNacimiento()));

        return DTO;
    }

    @Override
    public ClienteEntity convertToEntity(ClienteDTO dto) {
        ClienteEntity entity = new ClienteEntity();

        validateClienteDTO(dto);

        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setFechaNacimiento(clienteHelper.getBirthdayDate(dto));
        entity.setEdad(calculateAge(entity.getFechaNacimiento()));

        return entity;
    }

    private void validateClienteDTO(ClienteDTO dto) {
        if (StringUtils.isEmpty(dto.getNombre())) {
            throw new ValidationException(errorMessages.getClienteNameRequired());
        }

        if (StringUtils.isEmpty(dto.getApellido())) {
            throw new ValidationException(errorMessages.getClienteLastnameRequired());
        }

        if (dto.getFechaNacimiento() == null && StringUtils.isEmpty(dto.getFechaNacimientoString())) {
            throw new ValidationException(errorMessages.getClienteBirthdayRequired());
        }

        if (dto.getFechaNacimiento() != null && StringUtils.isNotEmpty(dto.getFechaNacimientoString())) {
            throw new ValidationException(errorMessages.getClienteAmbiguousBirthdayInformation());
        }
    }

    private int calculateAge(LocalDate birthday) {
        Period diff = Period.between(birthday, LocalDate.now());

        return diff.getYears();
    }
}
