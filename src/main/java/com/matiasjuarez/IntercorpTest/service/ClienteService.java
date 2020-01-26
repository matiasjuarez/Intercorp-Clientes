package com.matiasjuarez.IntercorpTest.service;

import com.matiasjuarez.IntercorpTest.dao.ClientRepository;
import com.matiasjuarez.IntercorpTest.model.client.ClienteDTO;
import com.matiasjuarez.IntercorpTest.model.client.ClienteEntity;
import com.matiasjuarez.IntercorpTest.model.client.ClientTransformer;
import com.matiasjuarez.IntercorpTest.model.kpicliente.KpiClienteDTO;
import com.matiasjuarez.IntercorpTest.service.deathstimationstrategies.DeathCalculationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {
    private ClientTransformer clientTransformer;
    private ClientRepository clientRepository;
    private DeathCalculationStrategy deathCalculationStrategy;
    private DateHandler dateHandler;

    @Autowired
    public ClienteService(ClientTransformer clientTransformer, ClientRepository clientRepository, DeathCalculationStrategy deathCalculationStrategy, DateHandler dateHandler) {
        this.clientTransformer = clientTransformer;
        this.clientRepository = clientRepository;
        this.deathCalculationStrategy = deathCalculationStrategy;
        this.dateHandler = dateHandler;
    }

    public ClienteDTO createNewClient(ClienteDTO clienteDTO) {
        ClienteEntity clienteEntity = clientTransformer.convertToEntity(clienteDTO);
        clienteEntity = clientRepository.save(clienteEntity);
        return clientTransformer.convertToDTO(clienteEntity);
    }

    public KpiClienteDTO calculateKpiClients() {
        return clientRepository.calculateClientsKPI();
    }

    public List<ClienteDTO> listAllClientsWithStimatedDeathDate() {
        Iterable<ClienteEntity> clienteEntities = clientRepository.findAll();

        List<ClienteDTO> clienteDTOList = new ArrayList<>();

        if (clienteEntities != null) {
            clienteEntities.forEach(entity -> {
                ClienteDTO clienteDTO = clientTransformer.convertToDTO(entity);
                clienteDTO.setFechaProbableMuerte(deathCalculationStrategy.calculateEstimatedDeathDate(clienteDTO));
                clienteDTO.setFechaProbableMuerteString(dateHandler.transformToFormattedString(clienteDTO.getFechaProbableMuerte()));
                clienteDTOList.add(clienteDTO);
            });
        }

        return clienteDTOList;
    }
}
