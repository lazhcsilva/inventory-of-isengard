package me.dio.domain.dto;

import java.util.List;

public record OrderDTO(Long id,
                       List<ItemDTO> items,
                       double totalOrderValue) {
}
