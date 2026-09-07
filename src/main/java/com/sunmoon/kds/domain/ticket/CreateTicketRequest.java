package com.sunmoon.kds.domain.ticket;

import jakarta.validation.constraints.NotBlank;

public record CreateTicketRequest(@NotBlank String orderId) {
}
