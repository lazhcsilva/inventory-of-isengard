package me.dio.controller;

import io.swagger.v3.oas.annotations.Operation;
import me.dio.domain.model.Invoice;
import me.dio.service.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @Operation(summary = "Get all invoice")
    @GetMapping
    public ResponseEntity<List<Invoice>> findAll() {
        return ResponseEntity.ok(invoiceService.getAll());
    }

    @Operation(summary = "Get invoice by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getById(@PathVariable Long id) {
        return ResponseEntity.ok(invoiceService.findById(id));
    }

    @Operation(summary = "Insert new Invoice")
    @PostMapping
    public ResponseEntity<Invoice> insert(@RequestBody Invoice invoice) {
        invoiceService.insert(invoice);
        return ResponseEntity.ok(invoice);
    }

    @Operation(summary = "Update invoice")
    @PutMapping("/{id}")
    public ResponseEntity<Invoice> update(@PathVariable Long id, @RequestBody Invoice invoice) {
        invoiceService.update(id, invoice);
        return ResponseEntity.ok(invoice);
    }

    @Operation(summary = "Delete invoice")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        invoiceService.delete(id);
        return ResponseEntity.ok().build();
    }

}
