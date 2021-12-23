package org.laurieandthegang.parkshark.api.dto.parkinglot;

import java.util.Objects;

public record RestrictedParkingLotDto(int id, String name, int capacity, String email, String telephone) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestrictedParkingLotDto that)) return false;
        return id == that.id && capacity == that.capacity
                && Objects.equals(name, that.name)
                && Objects.equals(email, that.email)
                && Objects.equals(telephone, that.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, capacity, email, telephone);
    }
}
