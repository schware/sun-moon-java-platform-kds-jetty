package com.sunmoon.kds.transport.http;

import com.sunmoon.kds.domain.ticket.CreateTicketRequest;
import com.sunmoon.kds.domain.ticket.Ticket;
import com.sunmoon.kds.domain.ticket.TicketRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
@Tag(name = "Tickets", description = "Kitchen ticket intake")
public class TicketController {

    private final TicketRepository ticketRepository;

    public TicketController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @PostMapping
    @Operation(
            summary = "Create a kitchen ticket",
            description = "Called when an order is received; creates a ticket in RECEIVED status "
                    + "for the kitchen to work from.")
    @ApiResponse(responseCode = "201", description = "Ticket created")
    @ApiResponse(responseCode = "400", description = "Validation failed (blank orderId)")
    public ResponseEntity<Ticket> create(@Valid @RequestBody CreateTicketRequest request) {
        Ticket saved = ticketRepository.save(new Ticket(request.orderId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
