package ru.yandex.practicum.sleeptracker;

public class SleepAnalysisResult {
    private final int result;
    private final String description;

    public int getResult() {
        return result;
    }

    public String getDescription() {
        return description;
    }

    public SleepAnalysisResult(int result, String description) {
        this.result = result;
        this.description = description;
    }
}
