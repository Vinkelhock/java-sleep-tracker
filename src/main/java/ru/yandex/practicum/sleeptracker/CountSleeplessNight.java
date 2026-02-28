package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class CountSleeplessNight implements SleepAnalysisFunction {
    private final String DESCRIPTION = "Количество бессонных ночей";

    @Override
    public SleepAnalysisResult analyze(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return new SleepAnalysisResult(0, DESCRIPTION);
        }

        Optional<LocalDateTime> minStartDate = findMinStartDate(sessions);
        Optional<LocalDateTime> maxEndDate = findMaxEndDate(sessions);
        if (minStartDate.isEmpty() || maxEndDate.isEmpty()) {
            return new SleepAnalysisResult(0, DESCRIPTION);
        }

        long countAllNights = Duration.between(minStartDate.get(), maxEndDate.get()).toDays() + 1;

        if (countAllNights == 0) {
            return new SleepAnalysisResult(0, DESCRIPTION);
        }

        long countNights = sessions
                .stream()
                .filter(session -> findNight(session).isPresent())
                .map(session -> findNight(session).get())
                .distinct()
                .count();

        int result = Math.toIntExact((int) countAllNights - countNights);
        if (result > 0) {
            return new SleepAnalysisResult(result, DESCRIPTION);
        } else {
            return new SleepAnalysisResult(0, DESCRIPTION);
        }
    }

    public static Optional<LocalDateTime> findMinStartDate(List<SleepingSession> sessions) {
        return sessions
                .stream()
                .map(SleepingSession::getStartDate)
                .min(LocalDateTime::compareTo);
    }

    public static Optional<LocalDateTime> findMaxEndDate(List<SleepingSession> sessions) {
        return sessions
                .stream()
                .map(SleepingSession::getEndDate)
                .max(LocalDateTime::compareTo);
    }

    public static Optional<LocalDate> findNight(SleepingSession session) {
        if (session.getStartDate().toLocalDate().isEqual(session.getEndDate().toLocalDate()) && session.getStartDate().toLocalTime().isAfter(LocalTime.of(6, 0)) && session.getEndDate().toLocalTime().isBefore(LocalTime.of(23, 59))) {
            return Optional.empty();
        } else if (session.getStartDate().toLocalDate().isAfter(session.getEndDate().toLocalDate())) {
            return Optional.empty();
        } else {
            return Optional.of(session.getEndDate().toLocalDate());
        }
    }
}
