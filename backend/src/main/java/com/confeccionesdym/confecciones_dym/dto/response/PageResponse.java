package com.confeccionesdym.confecciones_dym.dto.response;

import java.util.List;

public record PageResponse<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages
) {
    public PageResponse {
        if (page < 0) throw new IllegalArgumentException("page no puede ser negativa");
    }
}
