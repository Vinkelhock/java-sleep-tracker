package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SleepingSession {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final SleepType sleepType;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    public SleepingSession(String sleep) {
        String[] arr = sleep.split(";");
        this.startDate = LocalDateTime.parse(arr[0], DATE_TIME_FORMATTER);
        this.endDate = LocalDateTime.parse(arr[1], DATE_TIME_FORMATTER);
        this.sleepType = SleepType.valueOf(arr[2]);
    }

    public SleepingSession(String startDate, String endDate, SleepType sleepType) {
        this.startDate = LocalDateTime.parse(startDate, DATE_TIME_FORMATTER);
        this.endDate = LocalDateTime.parse(endDate, DATE_TIME_FORMATTER);
        this.sleepType = sleepType;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public SleepType getSleepType() {
        return sleepType;
    }

    public String toString() {
        String dreamStart = startDate.format(DATE_TIME_FORMATTER);
        String dreamEnd = endDate.format(DATE_TIME_FORMATTER);
        String dreamType = sleepType.toString();
        return String.format("Начало сна: %s. Конец сна: %s. Состояние сна: %s", dreamStart, dreamEnd, dreamType);
    }
}
