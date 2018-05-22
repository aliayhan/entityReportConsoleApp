package com.somecompany.test.model;

import com.somecompany.test.domain.EntityInstruction;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Builder
@Data
public class Entity {

    private String name;

    private EntityInstruction instruction;

    private float agreedFx;

    private String currency;

    private LocalDate instructionDate;

    private LocalDate settlementDate;

    private int units;

    private double pricePerUnit;

    private double totalPriceInUSD;

    public Entity(String name, EntityInstruction instruction, float agreedFx, String currency, LocalDate instructionDate, LocalDate settlementDate, int units, double pricePerUnit, double totalPriceInUSD) {
        this.name = name;
        this.instruction = instruction;
        this.agreedFx = agreedFx;
        this.currency = currency;
        this.instructionDate = instructionDate;
        this.settlementDate = settlementDate;
        this.units = units;
        this.pricePerUnit = pricePerUnit;

        if (this.currency != "USD") {
            this.totalPriceInUSD = Math.round((this.pricePerUnit * ((float) this.units) * this.agreedFx) * 100f) / 100f;
        } else {
            this.totalPriceInUSD = Math.round((this.pricePerUnit * ((float) this.units)) * 100f) / 100f;
        }
    }

    public Entity(LocalDate settlementDate, double totalPriceInUSD) {
        this.settlementDate = settlementDate;
        this.totalPriceInUSD = totalPriceInUSD;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return Float.compare(entity.agreedFx, agreedFx) == 0 &&
                units == entity.units &&
                Double.compare(entity.pricePerUnit, pricePerUnit) == 0 &&
                Double.compare(entity.totalPriceInUSD, totalPriceInUSD) == 0 &&
                Objects.equals(name, entity.name) &&
                instruction == entity.instruction &&
                Objects.equals(currency, entity.currency) &&
                Objects.equals(instructionDate, entity.instructionDate) &&
                Objects.equals(settlementDate, entity.settlementDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, instruction, agreedFx, currency, instructionDate, settlementDate, units, pricePerUnit, totalPriceInUSD);
    }
}
