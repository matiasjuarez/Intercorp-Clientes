package com.matiasjuarez.IntercorpTest.service.deathstimationstrategies;

import com.matiasjuarez.IntercorpTest.model.client.ClienteDTO;
import com.matiasjuarez.IntercorpTest.model.client.ClienteHelper;
import com.matiasjuarez.IntercorpTest.service.DateHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BasicDeathCalculationStrategy implements DeathCalculationStrategy {
    private static final double DAY_OF_DEATH_MULTIPLICATOR = 0.7;
    private static final int DAYS_IN_100_YEARS = 100 * 365 + (int) Math.round(100.0 / 4.0);
    private DateHandler dateHandler;
    private ClienteHelper clienteHelper;

    @Autowired
    public BasicDeathCalculationStrategy(DateHandler dateHandler, ClienteHelper clienteHelper) {
        this.dateHandler = dateHandler;
        this.clienteHelper = clienteHelper;
    }

    @Override
    public Long calculateEstimatedDeathDate(ClienteDTO clienteDTO) {
        LocalDate currentDate = LocalDate.now();

        LocalDate clientBirthdayDate = clienteHelper.getBirthdayDate(clienteDTO);
        if (clientBirthdayDate == null) {
            throw new IllegalArgumentException("Birthday information is needed to calculate the estimated death date");
        }

        long daysLivedByTheClient = ChronoUnit.DAYS.between(clientBirthdayDate, currentDate);

        long daysToBe100AgeOld = DAYS_IN_100_YEARS - daysLivedByTheClient;

        // If the client is 100 years old or older, he will die today
        if (daysToBe100AgeOld <= 0) return System.currentTimeMillis();

        LocalDate deathDate = currentDate.plusDays((long) (daysToBe100AgeOld * DAY_OF_DEATH_MULTIPLICATOR));

        return dateHandler.transformToMillis(deathDate);
    }
}
