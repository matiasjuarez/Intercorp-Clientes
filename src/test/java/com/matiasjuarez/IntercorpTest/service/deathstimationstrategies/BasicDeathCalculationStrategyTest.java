package com.matiasjuarez.IntercorpTest.service.deathstimationstrategies;

import com.matiasjuarez.IntercorpTest.model.client.ClienteDTO;
import com.matiasjuarez.IntercorpTest.model.client.ClienteHelper;
import com.matiasjuarez.IntercorpTest.service.DateHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BasicDeathCalculationStrategyTest {
    private static DateHandler dateHandler;
    private static ClienteHelper clienteHelper;
    private static BasicDeathCalculationStrategy basicDeathCalculationStrategy;

    @BeforeAll
    public static void setup() {
        dateHandler = new DateHandler();
        clienteHelper = new ClienteHelper(dateHandler);
        basicDeathCalculationStrategy = new BasicDeathCalculationStrategy(dateHandler, clienteHelper);
    }

    @Test
    public void testCalculateStimatedDeathDate() {
        ClienteDTO young = new ClienteDTO();
        young.setNombre("Young");
        young.setApellido("One");
        young.setFechaNacimientoString("23/12/2015");

        ClienteDTO old = new ClienteDTO();
        old.setNombre("Matias");
        old.setApellido("Juarez");
        old.setFechaNacimientoString("12/04/1991");

        Long youngDeath = basicDeathCalculationStrategy.calculateEstimatedDeathDate(young);
        Long oldDeath = basicDeathCalculationStrategy.calculateEstimatedDeathDate(old);

        assertNotNull(youngDeath);
        assertNotNull(oldDeath);
        assertTrue(oldDeath < youngDeath);
    }
}
