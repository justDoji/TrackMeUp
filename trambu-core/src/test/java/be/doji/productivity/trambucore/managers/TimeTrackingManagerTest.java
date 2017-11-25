package be.doji.productivity.trambucore.managers;

import be.doji.productivity.trambucore.TrambuTest;
import be.doji.productivity.trambucore.model.tracker.ActivityLog;
import be.doji.productivity.trambucore.model.tracker.TimeLog;
import be.doji.productivity.trambucore.testutil.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

public class TimeTrackingManagerTest extends TrambuTest {

    private static final String FILE_TIME_LOGS_TEST = "data/testTimeLog.txt";

    @Test public void testReadLogs() throws IOException, ParseException {
        String testPath = FileUtils.getTestPath(FILE_TIME_LOGS_TEST, this.getClass().getClassLoader());
        TimeTrackingManager tm = new TimeTrackingManager(testPath);
        tm.readLogs();
        ActivityLog parsedActivityLog = tm.getLogForActivityId("fa183c05-fb22-4411-8f94-12c954484f22");
        Assert.assertNotNull(parsedActivityLog);
        Assert.assertEquals("fa183c05-fb22-4411-8f94-12c954484f22", parsedActivityLog.getActivityId().toString());
        List<TimeLog> parsedLogPoints = parsedActivityLog.getLogpoints();
        Assert.assertNotNull(parsedLogPoints);
        Assert.assertEquals(1, parsedLogPoints.size());
    }

    @Test public void testUpdateFileEmptyFile() throws IOException, ParseException {
        String testPath = FileUtils.getTestPath(FILE_TIME_LOGS_TEST, this.getClass().getClassLoader());
        TimeTrackingManager tm = new TimeTrackingManager(testPath);
        tm.readLogs();
        Assert.assertFalse(tm.getLogs().isEmpty());
        Assert.assertEquals(1, tm.getLogs().size());

        Path tempFile = createTempFile();
        tm.updateFileLocation(tempFile.toString());
        Assert.assertTrue(tm.getLogs().isEmpty());

        Files.delete(tempFile);
    }

    @Test public void testWriteLogs() throws IOException {
        Path tempFile = createTempFile();
        TimeTrackingManager tm = new TimeTrackingManager(tempFile.toString());
        Assert.assertTrue(tm.getLogs().isEmpty());
        tm.writeLogs();
        Assert.assertTrue(Files.readAllLines(tempFile).isEmpty());
        ActivityLog testLog = new ActivityLog(UUID.randomUUID());
        tm.save(testLog);
        tm.writeLogs();
        List<String> linesAfterWrite = Files.readAllLines(tempFile);
        Assert.assertFalse(linesAfterWrite.isEmpty());
        Assert.assertEquals(2, linesAfterWrite.size());

        Files.delete(tempFile);
    }

    @Test public void testStopAll() throws IOException {
        Path tempFile = createTempFile();
        TimeTrackingManager tm = new TimeTrackingManager(tempFile.toString());
        ActivityLog testLogActiveOne = new ActivityLog(UUID.randomUUID());
        testLogActiveOne.startLog();
        Assert.assertTrue(testLogActiveOne.getActiveLog().isPresent());
        tm.save(testLogActiveOne);

        ActivityLog testLogActiveTwo = new ActivityLog(UUID.randomUUID());
        testLogActiveTwo.startLog();
        Assert.assertTrue(testLogActiveTwo.getActiveLog().isPresent());
        tm.save(testLogActiveTwo);

        List<ActivityLog> allLogsBeforeStop = tm.getLogs();
        Assert.assertFalse(allLogsBeforeStop.isEmpty());
        Assert.assertEquals(2, allLogsBeforeStop.size());
        for(ActivityLog log : allLogsBeforeStop) {
            Assert.assertTrue(log.getActiveLog().isPresent());
        }

        tm.stopAll();
        List<ActivityLog> allLogsAfterStop = tm.getLogs();
        Assert.assertFalse(allLogsAfterStop.isEmpty());
        Assert.assertEquals(2, allLogsAfterStop.size());
        for(ActivityLog log : allLogsAfterStop) {
            Assert.assertFalse(log.getActiveLog().isPresent());
        }

        Files.delete(tempFile);
    }

}
