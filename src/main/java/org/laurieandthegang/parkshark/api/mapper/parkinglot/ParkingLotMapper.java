package org.laurieandthegang.parkshark.api.mapper.parkinglot;

import org.laurieandthegang.parkshark.api.dto.parkinglot.CreateParkingLotDto;
import org.laurieandthegang.parkshark.api.dto.parkinglot.ParkingLotDto;
import org.laurieandthegang.parkshark.domain.parkinglot.ParkingLot;
import org.springframework.stereotype.Component;

@Component
public class ParkingLotMapper {

    public ParkingLotDto mapper(ParkingLot parkingLot) {
        return new ParkingLotDto(
                parkingLot.getId(),
                parkingLot.getName(),
                parkingLot.getCategory(),
                parkingLot.getCapacity(),
                parkingLot.getContactPerson(),
                parkingLot.getAddress(),
                parkingLot.getPricePerHour());
    }

    public ParkingLot mapper(CreateParkingLotDto createParkingLotDto) {
        return new ParkingLot(
                createParkingLotDto.name(),
                createParkingLotDto.category(),
                createParkingLotDto.capacity(),
                createParkingLotDto.contactPerson(),
                createParkingLotDto.address(),
                createParkingLotDto.pricePerHour());
    }
}
