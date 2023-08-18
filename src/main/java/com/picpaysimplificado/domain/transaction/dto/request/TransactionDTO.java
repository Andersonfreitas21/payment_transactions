package com.picpaysimplificado.domain.transaction.dto.request;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, Long senderId, Long receiveId) {
}
