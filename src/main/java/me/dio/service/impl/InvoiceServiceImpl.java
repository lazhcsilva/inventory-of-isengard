package me.dio.service.impl;

import me.dio.domain.model.Invoice;
import me.dio.domain.repository.InvoiceRepository;
import me.dio.service.InvoiceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public List<Invoice> getAll() {
        List<Invoice> invoices = invoiceRepository.findAll();
        if (invoices.isEmpty()) {
            throw new RuntimeException("No invoice saved.");
        }
        return invoices;
    }

    @Override
    public Invoice findById(Long id) {
        return invoiceRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void insert(Invoice invoice) {
        invoiceRepository.save(invoice);
    }

    @Override
    public void update(Long id,Invoice invoice) {
        Optional<Invoice> invoiceDB = invoiceRepository.findById(id);
        if (invoiceDB.isEmpty()) {
            throw new RuntimeException("ID not found");
        }
        invoiceRepository.save(invoice);
    }

    @Override
    public void delete(Long id) {
        Invoice invoice = findById(id);
        invoiceRepository.delete(invoice);
    }
}
