package org.laurieandthegang.parkshark.api.dto;

import com.sun.istack.NotNull;


public record CreateDivisionDto(
        @NotNull String name,
        @NotNull String originalName,
        @NotNull String director,
        Integer parentId) {
}