package com.somecompany.test.service;

import com.somecompany.test.model.Entity;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.somecompany.test.domain.EntityInstruction.BUY;
import static com.somecompany.test.domain.EntityInstruction.SELL;
import static java.time.LocalDate.of;
import static java.time.Month.JANUARY;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class EntityReportServiceTest {

    private EntityReportService testReportService;

    @Before
    public void setup() {
        testReportService = new EntityReportService();

        List<Entity> testEntities = new ArrayList<>();
        testEntities.add(Entity.builder()
                .name("b1")
                .instruction(BUY)
                .agreedFx(0.5f)
                .currency("SGP")
                .instructionDate(of(2016, JANUARY, 1))
                .settlementDate(of(2016, JANUARY, 2))
                .units(200)
                .pricePerUnit(100.25f)
                .build());
        testEntities.add(Entity.builder()
                .name("s1")
                .instruction(SELL)
                .agreedFx(0.22f)
                .currency("AED")
                .instructionDate(of(2016, JANUARY, 5))
                .settlementDate(of(2016, JANUARY, 7))
                .units(450)
                .pricePerUnit(150.5f)
                .build());

        testEntities.add(Entity.builder()
                .name("b2")
                .instruction(BUY)
                .agreedFx(1f)
                .currency("USD")
                .instructionDate(of(2016, JANUARY, 1))
                .settlementDate(of(2016, JANUARY, 2))
                .units(10)
                .pricePerUnit(10000.25f)
                .build());

        testEntities.add(Entity.builder()
                .name("b3")
                .instruction(BUY)
                .agreedFx(1f)
                .currency("USD")
                .instructionDate(of(2016, JANUARY, 7))
                .settlementDate(of(2016, JANUARY, 7))
                .units(10)
                .pricePerUnit(100f)
                .build());
        testEntities.add(Entity.builder()
                .name("s2")
                .instruction(SELL)
                .agreedFx(1f)
                .currency("USD")
                .instructionDate(of(2016, JANUARY, 7))
                .settlementDate(of(2016, JANUARY, 7))
                .units(20)
                .pricePerUnit(10000f)
                .build());

        testEntities.add(Entity.builder()
                .name("b4")
                .instruction(BUY)
                .agreedFx(1f)
                .currency("USD")
                .instructionDate(of(2016, JANUARY, 9))
                .settlementDate(of(2016, JANUARY, 9))
                .units(10)
                .pricePerUnit(2000f)
                .build());
        testEntities.add(Entity.builder()
                .name("s3")
                .instruction(SELL)
                .agreedFx(1f)
                .currency("USD")
                .instructionDate(of(2016, JANUARY, 8))
                .settlementDate(of(2016, JANUARY, 8))
                .units(10)
                .pricePerUnit(10000f)
                .build());

        testReportService.setAllEntities(testEntities);
    }

    @Test
    public void incomingInUSDEmptyListTest() {
        testReportService.setAllEntities(new ArrayList<>());
        assertThat(testReportService.getIncomingInUSD(), hasSize(0));
    }

    @Test
    public void incomingInUSDValidTest() {

        Entity first = new Entity(of(2016, JANUARY, 7), 214899.5f);
        Entity second = new Entity(of(2016, JANUARY, 8), 100000f);

        assertThat(testReportService.getIncomingInUSD(), contains(first, second));
    }

    @Test
    public void outgoingInUSDEmptyListTest() {
        testReportService.setAllEntities(new ArrayList<>());
        assertThat(testReportService.getOutgoingInUSD(), hasSize(0));
    }

    @Test
    public void outgoingInUSDValidTest() {
        Entity first = new Entity(of(2016, JANUARY, 2), 110027.5f);
        Entity second = new Entity(of(2016, JANUARY, 7), 1000f);
        Entity third = new Entity(of(2016, JANUARY, 9), 20000f);

        assertThat(testReportService.getOutgoingInUSD(), contains(first, second, third));
    }

    @Test
    public void buyRankingEmptyListTest() {
        testReportService.setAllEntities(new ArrayList<>());
        assertThat(testReportService.getBuyRanking(), hasSize(0));
    }

    @Test
    public void buyRankingValidTest() {

        Entity third = Entity.builder()
                .name("b1")
                .instruction(BUY)
                .agreedFx(0.5f)
                .currency("SGP")
                .instructionDate(of(2016, JANUARY, 1))
                .settlementDate(of(2016, JANUARY, 2))
                .units(200)
                .pricePerUnit(100.25f)
                .build();

        Entity first = Entity.builder()
                .name("b2")
                .instruction(BUY)
                .agreedFx(1f)
                .currency("USD")
                .instructionDate(of(2016, JANUARY, 1))
                .settlementDate(of(2016, JANUARY, 2))
                .units(10)
                .pricePerUnit(10000.25f)
                .build();

        Entity fourth = Entity.builder()
                .name("b3")
                .instruction(BUY)
                .agreedFx(1f)
                .currency("USD")
                .instructionDate(of(2016, JANUARY, 7))
                .settlementDate(of(2016, JANUARY, 7))
                .units(10)
                .pricePerUnit(100f)
                .build();

        Entity second = Entity.builder()
                .name("b4")
                .instruction(BUY)
                .agreedFx(1f)
                .currency("USD")
                .instructionDate(of(2016, JANUARY, 9))
                .settlementDate(of(2016, JANUARY, 9))
                .units(10)
                .pricePerUnit(2000f)
                .build();

        assertThat(testReportService.getBuyRanking(), contains(first, second, third, fourth));
    }

    @Test
    public void sellRankingEmptyListTest() {
        testReportService.setAllEntities(new ArrayList<>());
        assertThat(testReportService.getSellRanking(), hasSize(0));
    }

    @Test
    public void sellRankingValidTest() {

        Entity third = Entity.builder()
                .name("s1")
                .instruction(SELL)
                .agreedFx(0.22f)
                .currency("AED")
                .instructionDate(of(2016, JANUARY, 5))
                .settlementDate(of(2016, JANUARY, 7))
                .units(450)
                .pricePerUnit(150.5f)
                .build();

        Entity first = Entity.builder()
                .name("s2")
                .instruction(SELL)
                .agreedFx(1f)
                .currency("USD")
                .instructionDate(of(2016, JANUARY, 7))
                .settlementDate(of(2016, JANUARY, 7))
                .units(20)
                .pricePerUnit(10000f)
                .build();

        Entity second = Entity.builder()
                .name("s3")
                .instruction(SELL)
                .agreedFx(1f)
                .currency("USD")
                .instructionDate(of(2016, JANUARY, 8))
                .settlementDate(of(2016, JANUARY, 8))
                .units(10)
                .pricePerUnit(10000f)
                .build();

        assertThat(testReportService.getSellRanking(), contains(first, second, third));
    }
}
