package com.matiasjuarez.IntercorpTest.controllers;

import com.matiasjuarez.IntercorpTest.model.client.ClienteDTO;
import com.matiasjuarez.IntercorpTest.model.kpicliente.KpiClienteDTO;
import com.matiasjuarez.IntercorpTest.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {
    private ClienteService clienteService;

    @Autowired
    public ClientController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/creacliente")
    public ResponseEntity<ClienteDTO> createClient(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO createdClient = clienteService.createNewClient(clienteDTO);

        return ResponseEntity.ok(createdClient);
    }

    @GetMapping("/kpideclientes")
    public ResponseEntity<KpiClienteDTO> getKpiClientes() {
        KpiClienteDTO kpiClienteDTO = clienteService.calculateKpiClients();

        return ResponseEntity.ok(kpiClienteDTO);
    }

    @GetMapping("/listclientes")
    public ResponseEntity<List<ClienteDTO>> listClientes() {
        List<ClienteDTO> clienteDTOList = clienteService.listAllClientsWithStimatedDeathDate();

        return ResponseEntity.ok(clienteDTOList);
    }
}
