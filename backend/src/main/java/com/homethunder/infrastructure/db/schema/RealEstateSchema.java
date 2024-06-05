package com.homethunder.infrastructure.db.schema;

import com.homethunder.domain.realEstate.RealEstate;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "real_eastate")
@EqualsAndHashCode(callSuper = true)
public class RealEstateSchema extends BaseSchema {
    private String city;
    private String address;
    @ManyToOne
    public UserSchema owner;

    public RealEstateSchema(RealEstate entity) {
        super(entity);

        city = entity.getCity();
        address = entity.getAddress();
        owner = new UserSchema(entity.getOwner());
    }

    public RealEstate toBaseEntity() {
        RealEstate realEstate = super.toBaseEntity(new RealEstate());

        realEstate.setCity(city);
        realEstate.setAddress(address);
        realEstate.setOwner(owner.toUser());

        return realEstate;
    }
}
