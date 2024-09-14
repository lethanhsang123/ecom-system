package com.sanzee.ecom.system.order.service.domain.valueobject;

import com.sanzee.ecom.system.domain.valueobject.BaseId;

import java.util.Objects;
import java.util.UUID;

public class StreetAddress extends BaseId<UUID> {
    private final String street;
    private final String postalCode;
    private final String city;

    public StreetAddress(UUID value, String street, String postalCode, String city) {
        super(value);
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StreetAddress that = (StreetAddress) o;
        return Objects.equals(street, that.street) && Objects.equals(postalCode, that.postalCode) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), street, postalCode, city);
    }
}
