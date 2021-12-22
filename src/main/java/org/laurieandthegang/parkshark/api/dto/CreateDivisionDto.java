package org.laurieandthegang.parkshark.api.dto;

import com.sun.istack.NotNull;

import javax.persistence.Column;

public record CreateDivisionDto(
        @NotNull String name,
        @NotNull String originalName,
        @NotNull String director) {
}