package org.laurieandthegang.parkshark.api.dto;

public record DivisionDto(
        int id,
        String name,
        String originalName,
        String director,
        DivisionDto parentDivision) {
}
