package me.dio.service;

import me.dio.domain.model.Invoice;

import java.util.List;

public interface InvoiceService {

    List<Invoice> getAll();
    Invoice findById(Long id);
    void insert(Invoice invoice);
    void update(Long id, Invoice invoice);
    void delete(Long id);

}
