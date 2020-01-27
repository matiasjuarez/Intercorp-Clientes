package com.matiasjuarez.IntercorpTest.dao;

import com.matiasjuarez.IntercorpTest.model.client.ClienteEntity;
import com.matiasjuarez.IntercorpTest.model.client.ClienteHelper;
import com.matiasjuarez.IntercorpTest.model.kpicliente.KpiClienteDTO;
import com.matiasjuarez.IntercorpTest.service.DateHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import({DateHandler.class, ClienteHelper.class})
public class ClientRepositoryTest {
    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void calculateClientsKPI() {
        double delta = 0.0001;

        ClienteEntity client1 = new ClienteEntity();
        client1.setNombre("matias");
        client1.setApellido("juarez");
        client1.setFechaNacimiento(LocalDate.of(1991, Month.APRIL, 12));
        client1.setEdad(28);

        clientRepository.save(client1);

        KpiClienteDTO kpi = clientRepository.calculateClientsKPI();

        assertNotNull(kpi);
        assertEquals(28, kpi.getPromedioEdad(), delta);
        assertEquals(0, kpi.getDesviacionEstandarEdad(), delta);

        ClienteEntity client2 = new ClienteEntity();
        client2.setNombre("leonardo");
        client2.setApellido("martinez");
        client2.setFechaNacimiento(LocalDate.of(1989, Month.APRIL, 12));
        client2.setEdad(30);

        clientRepository.save(client2);

        kpi = clientRepository.calculateClientsKPI();

        assertNotNull(kpi);
        assertEquals(29, kpi.getPromedioEdad(), delta);
        assertEquals(1.0, kpi.getDesviacionEstandarEdad(), delta);
    }
}
