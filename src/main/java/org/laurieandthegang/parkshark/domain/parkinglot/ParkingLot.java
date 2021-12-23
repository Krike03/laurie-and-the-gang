package org.laurieandthegang.parkshark.domain.parkinglot;

import org.laurieandthegang.parkshark.domain.people.Address;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PARKING_LOT")
public class ParkingLot {

    @Id
    @SequenceGenerator(name = "PARKING_LOT_SEQ", sequenceName = "parking_lot_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARKING_LOT_SEQ")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORY")
    private Category category;

    @Column(name = "CAPACITY")
    private int capacity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "contact_person")
    private ContactPerson contactPerson;

    @Embedded
    private Address address;

    @Column(name = "PRICE_PER_HOUR")
    private double pricePerHour;

    private ParkingLot() {
    }

    public ParkingLot(String name, Category category, int capacity, ContactPerson contactPerson, Address address, double pricePerHour) {
        this.name = name;
        this.category = category;
        this.capacity = capacity;
        this.contactPerson = contactPerson;
        this.address = address;
        this.pricePerHour = pricePerHour;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public int getCapacity() {
        return capacity;
    }

    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public Address getAddress() {
        return address;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    @Override
    public String toString() {
        return "ParkingLot{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", capacity=" + capacity +
                ", contactPerson=" + contactPerson +
                ", address=" + address +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingLot that = (ParkingLot) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
