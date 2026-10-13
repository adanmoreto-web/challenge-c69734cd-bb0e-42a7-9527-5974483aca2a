package com.banco.prestamos.infrastructure.controllers;

import com.banco.prestamos.application.usecases.CreateLoanRequestUseCase;
import com.banco.prestamos.domain.model.LoanRequest;
import com.banco.prestamos.domain.model.LoanRequest.LoanStatus;
import com.banco.prestamos.domain.ports.IdempotencyService;
import com.banco.prestamos.domain.ports.LoanRequestRepository;
import com.banco.prestamos.infrastructure.exceptions.LoanRequestAlreadyExistsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/loan-requests")
@Tag(name = "Loan Requests", description = "API para la gestión de solicitudes de préstamos")
public class LoanRequestController {

    private final CreateLoanRequestUseCase createLoanRequestUseCase;
    private final LoanRequestRepository loanRequestRepository;
    private final IdempotencyService idempotencyService;

    public LoanRequestController(
            CreateLoanRequestUseCase createLoanRequestUseCase,
            LoanRequestRepository loanRequestRepository,
            IdempotencyService idempotencyService) {
        this.createLoanRequestUseCase = createLoanRequestUseCase;
        this.loanRequestRepository = loanRequestRepository;
        this.idempotencyService = idempotencyService;
    }

    public record CreateLoanRequestRequest(
            @NotNull(message = "El ID del cliente es obligatorio")
            @Positive(message = "El ID del cliente debe ser positivo")
            Long customerId,

            @NotNull(message = "El monto es obligatorio")
            @Positive(message = "El monto debe ser mayor a cero")
            BigDecimal amount,

            @NotNull(message = "El plazo en meses es obligatorio")
            @Positive(message = "El plazo debe ser mayor a cero")
            Integer termMonths,

            @NotBlank(message = "El propósito del préstamo es obligatorio")
            String purpose,

            @NotBlank(message = "La clave de idempotencia es obligatoria")
            String idempotencyKey
    ) {}

    public record LoanRequestResponse(
            UUID requestId,
            Long customerId,
            BigDecimal amount,
            Integer termMonths,
            String purpose,
            LoanStatus status,
            String rejectionReason,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        public static LoanRequestResponse fromDomain(LoanRequest loanRequest) {
            return new LoanRequestResponse(
                    loanRequest.requestId(),
                    loanRequest.customerId(),
                    loanRequest.amount(),
                    loanRequest.termMonths(),
                    loanRequest.purpose(),
                    loanRequest.status(),
                    loanRequest.rejectionReason(),
                    loanRequest.createdAt(),
                    loanRequest.updatedAt()
            );
        }
    }

    @PostMapping
    @Operation(summary = "Crear una nueva solicitud de préstamo", description = "Crea una nueva solicitud de préstamo con validación de idempotencia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Solicitud de préstamo creada exitosamente",
                    content = @Content(schema = @Schema(implementation = LoanRequestResponse.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "409", description = "La solicitud ya existe (violación de idempotencia)"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<LoanRequestResponse> createLoanRequest(
            @Valid @RequestBody CreateLoanRequestRequest request) {

        if (idempotencyService.isIdempotentKeyProcessed(request.idempotencyKey())) {
            throw new LoanRequestAlreadyExistsException(
                    "Ya existe una solicitud procesada con la clave de idempotencia: " + request.idempotencyKey()
            );
        }

        LoanRequest createdLoanRequest = createLoanRequestUseCase.execute(
                request.customerId(),
                request.amount(),
                request.termMonths(),
                request.purpose()
        );

        idempotencyService.markIdempotentKeyAsProcessed(
                request.idempotencyKey(),
                createdLoanRequest.requestId()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(LoanRequestResponse.fromDomain(createdLoanRequest));
    }

    @GetMapping("/{requestId}")
    @Operation(summary = "Obtener una solicitud de préstamo por ID", description = "Recupera los detalles de una solicitud de préstamo específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud encontrada",
                    content = @Content(schema = @Schema(implementation = LoanRequestResponse.class))),
            @ApiResponse(responseCode = "404", description = "Solicitud no encontrada")
    })
    public ResponseEntity<LoanRequestResponse> getLoanRequestById(
            @Parameter(description = "ID de la solicitud de préstamo", required = true)
            @PathVariable UUID requestId) {

        return loanRequestRepository.findByRequestId(requestId)
                .map(loanRequest -> ResponseEntity.ok(LoanRequestResponse.fromDomain(loanRequest)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Obtener solicitudes de préstamo por ID de cliente", description = "Lista todas las solicitudes de préstamo de un cliente específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de solicitudes del cliente",
                    content = @Content(schema = @Schema(implementation = LoanRequestResponse.class)))
    })
    public ResponseEntity<List<LoanRequestResponse>> getLoanRequestsByCustomerId(
            @Parameter(description = "ID del cliente", required = true)
            @PathVariable Long customerId) {

        List<LoanRequest> loanRequests = loanRequestRepository.findByCustomerId(customerId);
        List<LoanRequestResponse> responses = loanRequests.stream()
                .map(LoanRequestResponse::fromDomain)
                .toList();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/customer/{customerId}/request/{requestId}")
    @Operation(summary = "Obtener solicitud específica de un cliente", description = "Verifica si existe una solicitud específica para un cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud encontrada"),
            @ApiResponse(responseCode = "404", description = "Solicitud no encontrada para este cliente")
    })
    public ResponseEntity<Void> getLoanRequestByCustomerAndRequestId(
            @Parameter(description = "ID del cliente", required = true)
            @PathVariable Long customerId,
            @Parameter(description = "ID de la solicitud", required = true)
            @PathVariable UUID requestId) {

        boolean exists = loanRequestRepository.existsByCustomerIdAndRequestId(customerId, requestId);

        if (exists) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}