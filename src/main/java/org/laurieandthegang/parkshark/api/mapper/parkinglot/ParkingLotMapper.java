package org.laurieandthegang.parkshark.api.mapper.parkinglot;

import org.laurieandthegang.parkshark.api.dto.parkinglot.CreateParkingLotDto;
import org.laurieandthegang.parkshark.api.dto.parkinglot.ParkingLotDto;
import org.laurieandthegang.parkshark.api.dto.parkinglot.RestrictedParkingLotDto;
import org.laurieandthegang.parkshark.api.mapper.address.AddressMapper;
import org.laurieandthegang.parkshark.domain.parkinglot.ParkingLot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParkingLotMapper {

    private final ContactPersonMapper contactPersonMapper;
    private final AddressMapper addressMapper;

    @Autowired
    public ParkingLotMapper(ContactPersonMapper contactPersonMapper, AddressMapper addressMapper) {
        this.contactPersonMapper = contactPersonMapper;
        this.addressMapper = addressMapper;
    }


    public ParkingLotDto mapper(ParkingLot parkingLot) {
        return new ParkingLotDto(
                parkingLot.getId(),
                parkingLot.getName(),
                parkingLot.getCategory(),
                parkingLot.getCapacity(),
                contactPersonMapper.mapper(parkingLot.getContactPerson()),
                addressMapper.mapper(parkingLot.getAddress()),
                parkingLot.getPricePerHour());
    }

    public ParkingLot mapper(CreateParkingLotDto createParkingLotDto) {
        return new ParkingLot(
                createParkingLotDto.name(),
                createParkingLotDto.category(),
                createParkingLotDto.capacity(),
                contactPersonMapper.mapper(createParkingLotDto.contactPerson()),
                addressMapper.mapper(createParkingLotDto.address()),
                createParkingLotDto.pricePerHour());
    }

    public RestrictedParkingLotDto mapEntityToTheRestrictedDto(ParkingLot parkingLot) {

        return new RestrictedParkingLotDto(
                parkingLot.getId(),
                parkingLot.getName(),
                parkingLot.getCapacity(),
                contactPersonMapper.mapper(parkingLot.getContactPerson()).email(),
                contactPersonMapper.mapper(parkingLot.getContactPerson()).telephoneNumber());
    }
}
