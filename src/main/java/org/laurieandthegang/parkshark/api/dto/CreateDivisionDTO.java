package org.laurieandthegang.parkshark.api.dto;

import javax.persistence.Column;

public record CreateDivisionDTO(
        String name,
        String originalName,
        String director) {
}