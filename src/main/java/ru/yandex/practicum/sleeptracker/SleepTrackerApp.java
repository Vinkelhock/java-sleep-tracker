package ru.yandex.practicum.sleeptracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SleepTrackerApp {

    public static void main(String[] args) {

        String fileName = "src\\main\\resources\\sleep_log.txt";

        try {
            List<String> sleepString = Files.readAllLines(Paths.get(fileName));
            List<SleepingSession> sleepingSessionList = sleepString
                    .stream()
                    .map(sleep -> new SleepingSession(sleep))
                    .collect(Collectors.toList());

            List<SleepAnalysisFunction> functions = new ArrayList<>();
            functions.add(new MinSessionDuration());
            functions.add(new MaxSessionDuration());
            functions.add(new AvgSessionDuration());
            functions.add(new CountBadSession());
            functions.add(new CountSleeplessNight());
            functions.add(new GetUserType());

            functions.stream()
                    .map(function -> function.analyze(sleepingSessionList))
                    .forEach(result -> System.out.println("Была вызвана функция \"" + result.getDescription() + "\". Результат: " + result.getResult()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}