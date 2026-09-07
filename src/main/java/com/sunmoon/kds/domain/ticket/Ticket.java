package com.sunmoon.kds.domain.ticket;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.Instant;

@RedisHash("tickets")
public class Ticket {

    @Id
    private String id;

    @Indexed
    private String orderId;

    private TicketStatus status;
    private Instant createdAt;

    protected Ticket() {
        // for Spring Data Redis
    }

    public Ticket(String orderId) {
        this.orderId = orderId;
        this.status = TicketStatus.RECEIVED;
        this.createdAt = Instant.now();
    }

    public String getId() {
        return id;
    }

    public String getOrderId() {
        return orderId;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
