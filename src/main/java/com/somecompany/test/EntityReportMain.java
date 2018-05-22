package com.somecompany.test;

import com.somecompany.test.model.Entity;
import com.somecompany.test.service.EntityReportService;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.lang.String.format;
import static java.time.format.DateTimeFormatter.ofPattern;

public class EntityReportMain {

    private static String TAB = "\t";
    private static String LINE_BREAK = System.lineSeparator();
    private static String EMPTY = "";

    public static void main(String[] args) {
        EntityReportService reportService = new EntityReportService();

        System.out.println();
        printOf(reportService.getAllEntities(), false);

        System.out.println();
        System.out.println("Amount in USD settled incoming (sell) everyday (based on SettlementDate):");
        printOf(reportService.getIncomingInUSD(), true);

        System.out.println();
        System.out.println("Amount in USD settled outgoing (buy) everyday (based on SettlementDate):");
        printOf(reportService.getOutgoingInUSD(), true);

        System.out.println();
        System.out.println("Ranking of entities based on incoming (sell) amount:");
        printOf(reportService.getSellRanking(), false);

        System.out.println();
        System.out.println("Ranking of entities based on outgoing (buy) amount:");
        printOf(reportService.getBuyRanking(), false);
    }

    private static void printOf(List<Entity> entities, boolean onlyShowDateAndTotal) {
        DateTimeFormatter dateTimeFormatter = ofPattern("dd MMM yyyy");
        StringBuilder screen = new StringBuilder(1000);

        printTitles(screen, onlyShowDateAndTotal);

        if (onlyShowDateAndTotal) {
            for (Entity entity : entities) {
                screen.append(entity.getSettlementDate().format(dateTimeFormatter))
                        .append(TAB + TAB + TAB)
                        .append(format("%012.2f", entity.getTotalPriceInUSD()))
                        .append(LINE_BREAK);
            }
        } else {
            for (Entity entity : entities) {
                screen.append(format("%012.2f", entity.getTotalPriceInUSD()))
                        .append(TAB)
                        .append(entity.getName())
                        .append(TAB + TAB + TAB)
                        .append(entity.getInstruction().getValue())
                        .append(TAB + TAB + TAB + TAB)
                        .append(format("%.2f", entity.getAgreedFx()))
                        .append(TAB + TAB + TAB)
                        .append(entity.getCurrency())
                        .append(TAB + TAB + TAB + TAB)
                        .append(entity.getInstructionDate().format(dateTimeFormatter))
                        .append(TAB + TAB + TAB)
                        .append(entity.getSettlementDate().format(dateTimeFormatter))
                        .append(TAB + TAB + TAB)
                        .append(format("%05d", entity.getUnits()))
                        .append(TAB + TAB)
                        .append(format("%08.2f", entity.getPricePerUnit()))
                        .append(LINE_BREAK);
            }
        }

        System.out.println(screen.toString());
    }

    private static void printTitles(StringBuilder screen, boolean onlyShowDateAndTotal) {
        if (onlyShowDateAndTotal) {
            screen.append("SettlementDate")
                    .append(TAB + TAB)
                    .append("Total in USD")
                    .append(LINE_BREAK);
        } else {
            screen.append("Total in USD")
                    .append(TAB)
                    .append("Entity")
                    .append(TAB + TAB)
                    .append("Buy/Sell")
                    .append(TAB + TAB)
                    .append("AgreedFx")
                    .append(TAB + TAB)
                    .append("Currency")
                    .append(TAB + TAB)
                    .append("InstructionDate")
                    .append(TAB + TAB)
                    .append("SettlementDate")
                    .append(TAB + TAB)
                    .append("Units")
                    .append(TAB + TAB)
                    .append("Price per unit")
                    .append(TAB + TAB)
                    .append(LINE_BREAK);
        }
    }
}
