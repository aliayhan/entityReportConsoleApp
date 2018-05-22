package com.somecompany.test.daomock;

import com.somecompany.test.model.Entity;

import java.util.ArrayList;
import java.util.List;

import static com.somecompany.test.domain.EntityInstruction.BUY;
import static com.somecompany.test.domain.EntityInstruction.SELL;
import static java.time.LocalDate.of;
import static java.time.Month.JANUARY;

public class EntityReportDaoMock {

    private static List<Entity> entities;
    {
        entities = new ArrayList<>();
        entities.add(Entity.builder()
                .name("b1")
                .instruction(BUY)
                .agreedFx(0.5f)
                .currency("SGP")
                .instructionDate(of(2016, JANUARY, 1))
                .settlementDate(of(2016, JANUARY, 2))
                .units(200)
                .pricePerUnit(100.25f)
                .build());
        entities.add(Entity.builder()
                .name("s1")
                .instruction(SELL)
                .agreedFx(0.22f)
                .currency("AED")
                .instructionDate(of(2016, JANUARY, 5))
                .settlementDate(of(2016, JANUARY, 7))
                .units(450)
                .pricePerUnit(150.5f)
                .build());

        entities.add(Entity.builder()
                .name("b2")
                .instruction(BUY)
                .agreedFx(1f)
                .currency("USD")
                .instructionDate(of(2016, JANUARY, 1))
                .settlementDate(of(2016, JANUARY, 2))
                .units(10)
                .pricePerUnit(10000.25f)
                .build());

        entities.add(Entity.builder()
                .name("b3")
                .instruction(BUY)
                .agreedFx(1f)
                .currency("USD")
                .instructionDate(of(2016, JANUARY, 7))
                .settlementDate(of(2016, JANUARY, 7))
                .units(10)
                .pricePerUnit(100f)
                .build());
        entities.add(Entity.builder()
                .name("s2")
                .instruction(SELL)
                .agreedFx(1f)
                .currency("USD")
                .instructionDate(of(2016, JANUARY, 7))
                .settlementDate(of(2016, JANUARY, 7))
                .units(20)
                .pricePerUnit(10000f)
                .build());

        entities.add(Entity.builder()
                .name("b4")
                .instruction(BUY)
                .agreedFx(1f)
                .currency("USD")
                .instructionDate(of(2016, JANUARY, 9))
                .settlementDate(of(2016, JANUARY, 9))
                .units(10)
                .pricePerUnit(2000f)
                .build());
        entities.add(Entity.builder()
                .name("s3")
                .instruction(SELL)
                .agreedFx(1f)
                .currency("USD")
                .instructionDate(of(2016, JANUARY, 8))
                .settlementDate(of(2016, JANUARY, 8))
                .units(10)
                .pricePerUnit(10000f)
                .build());
    }

    public List<Entity> getAllEntites() {
        return entities;
    }
}
