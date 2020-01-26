package com.matiasjuarez.IntercorpTest.model.client;

import com.matiasjuarez.IntercorpTest.model.AbstractTransformer;
import org.springframework.stereotype.Component;

@Component
public class ClientTransformer extends AbstractTransformer<ClienteDTO, ClienteEntity> {
    public ClienteDTO convertToDTO(ClienteEntity clienteEntity) {
        ClienteDTO DTO = new ClienteDTO();
        DTO.setId(clienteEntity.getId());
        DTO.setNombre(clienteEntity.getNombre());
        DTO.setApellido(clienteEntity.getApellido());
        DTO.setEdad(clienteEntity.getEdad());
        DTO.setFechaNacimiento(transformToMillis(clienteEntity.getFechaNacimiento()));

        return DTO;
    }

    @Override
    public ClienteEntity convertToEntity(ClienteDTO dto) {
        ClienteEntity entity = new ClienteEntity();
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setEdad(dto.getEdad());
        entity.setFechaNacimiento(transformToLocalDate(dto.getFechaNacimiento()));

        return entity;
    }
}
