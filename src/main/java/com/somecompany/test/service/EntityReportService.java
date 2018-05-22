package com.somecompany.test.service;

import com.somecompany.test.daomock.EntityReportDaoMock;
import com.somecompany.test.model.Entity;
import com.somecompany.test.domain.EntityInstruction;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EntityReportService {

    private List<Entity> allEntities;

    public EntityReportService() {
        allEntities = new EntityReportDaoMock().getAllEntites();
    }

    public List<Entity> getAllEntities() {
        return allEntities;
    }

    public void setAllEntities(List<Entity> allEntities) {
        this.allEntities = allEntities;
    }

    public List<Entity> getIncomingInUSD() {

        Map<LocalDate, Double> aggregatePerDayMap = allEntities.stream()
                .filter(e -> e.getInstruction() == EntityInstruction.SELL)
                .collect(Collectors.groupingBy(Entity::getSettlementDate, Collectors.summingDouble(Entity::getTotalPriceInUSD)));

        return aggregatePerDayMap.entrySet().stream()
                .map(e -> new Entity(e.getKey(), e.getValue()))
                .sorted(EntitySettlementDateComparator)
                .collect(Collectors.toList());
    }

    public List<Entity> getOutgoingInUSD() {

        Map<LocalDate, Double> aggregatePerDayMap = allEntities.stream()
                .filter(e -> e.getInstruction() == EntityInstruction.BUY)
                .collect(Collectors.groupingBy(Entity::getSettlementDate, Collectors.summingDouble(Entity::getTotalPriceInUSD)));

        return aggregatePerDayMap.entrySet().stream()
                .map(e -> new Entity(e.getKey(), e.getValue()))
                .sorted(EntitySettlementDateComparator)
                .collect(Collectors.toList());
    }

    public List<Entity> getBuyRanking() {

        return allEntities.stream()
                .filter(e -> e.getInstruction() == EntityInstruction.BUY)
                .sorted(EntityTotalInUSDComparatorDesc)
                .collect(Collectors.toList());
    }

    public List<Entity> getSellRanking() {

        return allEntities.stream()
                .filter(e -> e.getInstruction() == EntityInstruction.SELL)
                .sorted(EntityTotalInUSDComparatorDesc)
                .collect(Collectors.toList());
    }

    private static Comparator<Entity> EntitySettlementDateComparator = new Comparator<Entity>() {

        @Override
        public int compare(Entity o1, Entity o2) {

            boolean isO1GivenAndO2Empty = o1 != null && o2 == null;
            if (isO1GivenAndO2Empty) {
                return 1;
            }

            boolean isO1EmptyAndO2Given = o1 == null && o2 != null;
            if (isO1EmptyAndO2Given) {
                return -1;
            }

            boolean isO1EmptyAndO2Emptry = o1 == null && o2 == null;
            if (isO1EmptyAndO2Emptry) {
                return 0;
            }

            return o1.getSettlementDate().compareTo(o2.getSettlementDate());
        }
    };

    private static Comparator<Entity> EntityTotalInUSDComparatorDesc = new Comparator<Entity>() {

        @Override
        public int compare(Entity o1, Entity o2) {

            boolean isO1GivenAndO2Empty = o1 != null && o2 == null;
            if (isO1GivenAndO2Empty) {
                return -1;
            }

            boolean isO1EmptyAndO2Given = o1 == null && o2 != null;
            if (isO1EmptyAndO2Given) {
                return 1;
            }

            boolean isO1EmptyAndO2Emptry = o1 == null && o2 == null;
            if (isO1EmptyAndO2Emptry) {
                return 0;
            }

            Double o1TotalInUSD = o1.getTotalPriceInUSD();
            Double o2TotalInUSD = o2.getTotalPriceInUSD();
            if (o1TotalInUSD > o2TotalInUSD) {
                return -1;
            } else if (o1TotalInUSD == o2TotalInUSD) {
                return 0;
            } else {
                return 1;
            }
        }
    };
}
