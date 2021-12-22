package org.laurieandthegang.parkshark.api.mapper;

import org.laurieandthegang.parkshark.api.dto.CreateParkingLotDto;
import org.laurieandthegang.parkshark.api.dto.ParkingLotDto;
import org.laurieandthegang.parkshark.domain.parkinglot.ParkingLot;
import org.springframework.stereotype.Component;

@Component
public class ParkingLotMapper {

    public ParkingLotDto mapper(ParkingLot parkingLot) {
        return new ParkingLotDto(parkingLot.getId(), parkingLot.getName(), parkingLot.getCapacity(), parkingLot.getAddress(), parkingLot.getCategory(), parkingLot.getContactPerson(), parkingLot.getPricePerHour());
    }

    public ParkingLot mapper(CreateParkingLotDto createParkingLotDto) {
        return new ParkingLot(createParkingLotDto.name(), createParkingLotDto.category(), createParkingLotDto.capacity(), createParkingLotDto.contactPerson(), createParkingLotDto.address(), createParkingLotDto.pricePerHour());

    }
}
