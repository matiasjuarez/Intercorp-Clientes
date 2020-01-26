package com.matiasjuarez.IntercorpTest.model.client;

import com.matiasjuarez.IntercorpTest.model.AbstractTransformer;
import com.matiasjuarez.IntercorpTest.service.DateHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ClientTransformer extends AbstractTransformer<ClienteDTO, ClienteEntity> {
    private DateHandler dateHandler;
    private ClienteHelper clienteHelper;

    @Autowired
    public ClientTransformer(DateHandler dateHandler, ClienteHelper clienteHelper) {
        this.dateHandler = dateHandler;
        this.clienteHelper = clienteHelper;
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
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());

        entity.setFechaNacimiento(clienteHelper.getBirthdayDate(dto));
        if (entity.getFechaNacimiento() == null) {
            throw new IllegalStateException("Se debe especificar la fecha de nacimiento del Cliente al momento de su creacion");
        }

        entity.setEdad(calculateAge(entity.getFechaNacimiento()));

        return entity;
    }

    private int calculateAge(LocalDate birthday) {
        return LocalDate.now().getYear() - birthday.getYear();
    }
}
