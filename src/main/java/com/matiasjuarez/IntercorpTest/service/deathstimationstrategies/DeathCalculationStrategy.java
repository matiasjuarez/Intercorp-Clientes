package com.matiasjuarez.IntercorpTest.service.deathstimationstrategies;

import com.matiasjuarez.IntercorpTest.model.client.ClienteDTO;

public interface DeathCalculationStrategy {
    Long calculateStimatedDeathDate(ClienteDTO clienteDTO);
}
