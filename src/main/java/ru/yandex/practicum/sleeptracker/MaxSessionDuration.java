package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;

class MaxSessionDuration implements SleepAnalysisFunction {
    @Override
    public SleepAnalysisResult analyze(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return new SleepAnalysisResult(0, "Минимальная сессия");
        }

        int max = sessions
                .stream()
                .mapToInt(session -> (int) Duration.between(session.getStartDate(), session.getEndDate()).toMinutes())
                .max()
                .orElse(0);

        return new SleepAnalysisResult(max, "Максимальная продолжительность сессии");
    }
}