package ru.yandex.practicum.sleeptracker;

import java.util.List;

class CountBadSession implements SleepAnalysisFunction {
    @Override
    public SleepAnalysisResult analyze(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return new SleepAnalysisResult(0, "Минимальная сессия");
        }

        long count = sessions
                .stream()
                .filter(session -> session.getSleepType() == SleepType.BAD)
                .count();

        return new SleepAnalysisResult((int) count, "Количество сессий с плохим качеством сна");
    }
}