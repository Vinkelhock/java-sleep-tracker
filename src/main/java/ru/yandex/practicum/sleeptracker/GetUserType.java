package ru.yandex.practicum.sleeptracker;

import java.time.LocalTime;
import java.util.List;

public class GetUserType implements SleepAnalysisFunction {
    @Override
    public SleepAnalysisResult analyze(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return new SleepAnalysisResult(0, "Недостаточно данных");
        }

        int countOwlNight = Math.toIntExact(sessions
                .stream()
                .filter(session -> session.getStartDate().toLocalDate().isBefore(session.getEndDate().toLocalDate()))
                .filter(session -> (session.getStartDate().toLocalTime().isAfter(LocalTime.of(23, 0))
                        && session.getEndDate().toLocalTime().isAfter(LocalTime.of(9, 0))))
                .count());

        int countLarkNight = Math.toIntExact(sessions
                .stream()
                .filter(session -> session.getStartDate().toLocalDate().isBefore(session.getEndDate().toLocalDate()))
                .filter(session -> (session.getStartDate().toLocalTime().isBefore(LocalTime.of(22, 0))
                        && session.getEndDate().toLocalTime().isBefore(LocalTime.of(7, 0))))
                .count());

        int countPigeonNight = Math.toIntExact(sessions
                .stream()
                .filter(session -> session.getStartDate().toLocalDate().isBefore(session.getEndDate().toLocalDate()))
                .filter(session -> (session.getStartDate().toLocalTime().isAfter(LocalTime.of(22, 0))
                        && session.getStartDate().toLocalTime().isBefore(LocalTime.of(23, 0))
                        && session.getEndDate().toLocalTime().isAfter(LocalTime.of(7, 0))
                        && session.getEndDate().toLocalTime().isBefore(LocalTime.of(9, 0))))
                .count());

        if (countOwlNight > countLarkNight && countOwlNight > countPigeonNight) {
            return new SleepAnalysisResult(1, "Пользователь является совой");
        } else if (countLarkNight > countOwlNight && countLarkNight > countPigeonNight) {
            return new SleepAnalysisResult(2, "Пользователь является жаворонком");
        } else if (countPigeonNight >= countOwlNight && countPigeonNight >= countLarkNight) {
            return new SleepAnalysisResult(3, "Пользователь является голубем");
        } else {
            return new SleepAnalysisResult(4, "Неизвестный тип пользователя");
        }
    }
}
