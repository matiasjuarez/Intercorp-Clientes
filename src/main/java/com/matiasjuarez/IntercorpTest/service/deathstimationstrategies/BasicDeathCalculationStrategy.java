package com.matiasjuarez.IntercorpTest.service.deathstimationstrategies;

import com.matiasjuarez.IntercorpTest.model.AbstractTransformer;
import com.matiasjuarez.IntercorpTest.model.client.ClienteDTO;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BasicDeathCalculationStrategy implements DeathCalculationStrategy {
    private static final double DAY_OF_DEATH_MULTIPLICATOR = 0.7;
    private static final int DAYS_IN_100_YEARS = 100 * 365 + (int) Math.round(100.0 / 4.0);

    @Override
    public Long calculateStimatedDeathDate(ClienteDTO clienteDTO) {
        LocalDate currentDate = LocalDate.now();
        LocalDate clientBirthdayDate = AbstractTransformer.transformToLocalDate(clienteDTO.getFechaNacimiento());
        long daysLivedByTheClient = ChronoUnit.DAYS.between(currentDate, clientBirthdayDate);

        long daysToBe100AgeOld = DAYS_IN_100_YEARS - daysLivedByTheClient;

        // If the client is 100 years old or older, he will day today
        if (daysToBe100AgeOld <= 0) return System.currentTimeMillis();

        LocalDate deathDate = currentDate.plusDays((long) (daysToBe100AgeOld * DAY_OF_DEATH_MULTIPLICATOR));

        return AbstractTransformer.transformToMillis(deathDate);
    }
}
