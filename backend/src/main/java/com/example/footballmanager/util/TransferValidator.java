package com.example.footballmanager.util;

import com.example.footballmanager.exception.TransferException;
import com.example.footballmanager.model.Team;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class TransferValidator {
    public void validateTransfer(Team teamFrom, BigDecimal cost) {
        if (teamFrom.getBalance().compareTo(cost) < 0) {
            throw new TransferException("Can't make transfer because not enough money on team "
                    + teamFrom.getName() + " balance");
        }
    }
}
