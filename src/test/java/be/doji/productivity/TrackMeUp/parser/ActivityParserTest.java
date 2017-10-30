package be.doji.productivity.TrackMeUp.parser;

import be.doji.productivity.TrackMeUp.managers.ActivityManager;
import be.doji.productivity.TrackMeUp.model.tasks.Activity;
import be.doji.productivity.TrackMeUp.testutil.ActivityTestData;
import be.doji.productivity.TrackMeUp.testutil.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Doji on 30/10/2017.
 */
public class ActivityParserTest {

    @Test public void testMapStringToActivityCompleted() throws IOException, ParseException {
        Activity activity = ActivityParser.mapStringToActivity(ActivityTestData.COMPLETED_ACTIVITY);
        Assert.assertNotNull(activity);

        Assert.assertEquals("B", activity.getPriority());
        Assert.assertEquals("Buy thunderbird plugin license", activity.getName());
        Assert.assertTrue(activity.isCompleted());
    }

    @Test public void testmapStringToActivity() throws IOException, ParseException {
        Activity activity = ActivityParser.mapStringToActivity(ActivityTestData.ACTIVITY_DATA_LINE);
        Assert.assertNotNull(activity);

        Assert.assertEquals("A", activity.getPriority());
        Assert.assertEquals("TaskTitle", activity.getName());
        Assert.assertFalse(activity.isCompleted());

        List<String> tags = activity.getTags();
        Assert.assertEquals(2, tags.size());
        Assert.assertTrue(tags.contains("Tag"));
        Assert.assertTrue(tags.contains("Tag2"));

        List<String> projects = activity.getProjects();
        Assert.assertEquals(1, projects.size());
        Assert.assertEquals("OverarchingProject", projects.get(0));

        LocalDateTime deadline = activity.getDeadline();
        Assert.assertNotNull(deadline);
        Assert.assertEquals(21, deadline.getDayOfMonth());
        Assert.assertEquals(12, deadline.getMonth().getValue());
        Assert.assertEquals(2017, deadline.getYear());
    }

    @Test public void testMapNoPrefixLine() throws IOException, ParseException {
        Activity activity = ActivityParser.mapStringToActivity(ActivityTestData.NO_PREFIX_DATA_LINE);
        Assert.assertNotNull(activity);
        Assert.assertEquals("Write my own todo.txt webapp", activity.getName());
        Assert.assertNotNull(activity.getProjects());
        Assert.assertEquals(3, activity.getProjects().size());
        Assert.assertNotNull(activity.getTags());
        Assert.assertEquals(1, activity.getTags().size());
        Assert.assertEquals("development", activity.getTags().get(0));

    }

    @Test public void testMapNoPrefixLineWithNumbers() throws IOException, ParseException {
        Activity activity = ActivityParser.mapStringToActivity(ActivityTestData.NO_PREFIX_DATA_LINE_WITH_NUMBERS);
        Assert.assertNotNull(activity);
        Assert.assertEquals("Write my own 123-todo.txt webapp", activity.getName());
    }

    @Test public void testCreateActivityWithWarningTimeframe() throws ParseException, IOException {
        Activity activity = ActivityParser.mapStringToActivity(ActivityTestData.ACTIVITY_DATA_LINE_WITH_WARNING);
        Assert.assertNotNull(activity);
        Assert.assertNotNull(activity.getWarningTimeFrame());
        Assert.assertEquals(2, activity.getWarningTimeFrame().toDays());
        Assert.assertEquals((24 * 2) + 3, activity.getWarningTimeFrame().toHours());
        Assert.assertEquals(((24 * 2) + 3) * 60 + 4, activity.getWarningTimeFrame().toMinutes());
    }

    @Test public void testDateTime() {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.ofSeconds(86400);
        Assert.assertEquals(1, duration.toDays());
        Assert.assertNotNull(now.minus(duration));
    }
}