package com.hcmut.advancedprogramming.flidi.persistence.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hcmut.advancedprogramming.flidi.persistence.enumtype.ProvinceDomainStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "provinces")
@AttributeOverride(name = "id", column = @Column(name = "province_id"))
public class Province extends AbstractEntity {
    @Column(name = "province_name", nullable = false, unique = true)
    private String provinceName;

    @Enumerated
    @Column(name = "status", columnDefinition = "smallint", nullable = false)
    private ProvinceDomainStatus status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "province")
    @JsonManagedReference
    private Set<Location> locations;

    public Province() {
        super();
    }

    public Province(String provinceName, ProvinceDomainStatus status) {
        this.provinceName = provinceName;
        this.status = status;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public ProvinceDomainStatus getStatus() {
        return status;
    }

    public void setStatus(ProvinceDomainStatus status) {
        this.status = status;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }
}
