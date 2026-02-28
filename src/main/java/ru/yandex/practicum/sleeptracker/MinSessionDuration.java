package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;

class MinSessionDuration implements SleepAnalysisFunction {
    @Override
    public SleepAnalysisResult analyze(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return new SleepAnalysisResult(0, "Минимальная сессия");
        }

        int min = sessions
                .stream()
                .mapToInt(session -> (int) Duration.between(session.getStartDate(), session.getEndDate()).toMinutes())
                .min()
                .orElse(0);

        return new SleepAnalysisResult(min, "Минимальная продолжительность сессии");
    }
}