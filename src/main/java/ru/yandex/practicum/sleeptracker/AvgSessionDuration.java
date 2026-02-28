package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;

class AvgSessionDuration implements SleepAnalysisFunction {
    @Override
    public SleepAnalysisResult analyze(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return new SleepAnalysisResult(0, "Минимальная сессия");
        }

        double average = sessions
                .stream()
                .mapToInt(session -> (int) Duration.between(session.getStartDate(), session.getEndDate()).toMinutes())
                .average()
                .orElse(0);

        return new SleepAnalysisResult((int) average, "Средняя продолжительность сессии");
    }
}