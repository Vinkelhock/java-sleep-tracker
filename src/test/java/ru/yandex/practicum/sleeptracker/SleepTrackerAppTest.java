package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.List;

public class SleepTrackerAppTest {

    private static List<SleepingSession> sessions;

    @BeforeAll
    public static void beforeAll() {
        sessions = new ArrayList<>();
        sessions.add(new SleepingSession("01.10.25 22:15", "02.10.25 08:00", SleepType.GOOD));
        sessions.add(new SleepingSession("02.10.25 13:13", "02.10.25 14:14", SleepType.GOOD));
        sessions.add(new SleepingSession("02.10.25 23:00", "03.10.25 08:00", SleepType.BAD));
        sessions.add(new SleepingSession("03.10.25 14:30", "03.10.25 15:20", SleepType.NORMAL));
        sessions.add(new SleepingSession("03.10.25 23:30", "04.10.25 06:20", SleepType.BAD));
        sessions.add(new SleepingSession("04.10.25 23:30", "05.10.25 06:20", SleepType.GOOD));
        sessions.add(new SleepingSession("07.10.25 23:30", "08.10.25 06:20", SleepType.GOOD));
    }

    @Test
    public void minSessionDurationTest() {
        MinSessionDuration minSessionDuration = new MinSessionDuration();
        SleepAnalysisResult result = minSessionDuration.analyze(sessions);

        Assertions.assertEquals(result.getResult(), 50);
    }

    @Test
    public void maxSessionDurationTest() {
        MaxSessionDuration maxSessionDuration = new MaxSessionDuration();
        SleepAnalysisResult result = maxSessionDuration.analyze(sessions);

        Assertions.assertEquals(result.getResult(), 585);
    }

    @Test
    public void avgSessionDurationTest() {
        AvgSessionDuration avgSessionDuration = new AvgSessionDuration();
        SleepAnalysisResult result = avgSessionDuration.analyze(sessions);

        Assertions.assertEquals(result.getResult(), 352);
    }

    @Test
    public void countBadSessionTest() {
        CountBadSession countBadSession = new CountBadSession();
        SleepAnalysisResult result = countBadSession.analyze(sessions);

        Assertions.assertEquals(result.getResult(), 2);
    }

    @Test
    public void countSleeplessNightTest() {
        CountSleeplessNight countSleeplessNight = new CountSleeplessNight();
        SleepAnalysisResult result = countSleeplessNight.analyze(sessions);

        Assertions.assertEquals(result.getResult(), 2);
    }

    @Test
    public void getUserTypeTest() {
        GetUserType getUserType = new GetUserType();
        SleepAnalysisResult result = getUserType.analyze(sessions);

        Assertions.assertEquals(result.getResult(), 3);
    }
}