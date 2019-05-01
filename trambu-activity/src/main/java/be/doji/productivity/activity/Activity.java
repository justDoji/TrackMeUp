package be.doji.productivity.activity;

import be.doji.productivity.time.TimePoint;

/**
 * An activity is something you do at a certain time. Activities have a link to times spent on it
 *
 * An be.doji.productivity.activity.Activity has a configurable set of properties. These can be
 * configured.
 */
public class Activity {

  private String name;
  private TimePoint plannedStart;
  private TimePoint plannedEnd;

  private void setName(String activityName) {
    this.name = activityName;
  }

  private void setPlannedStart(TimePoint plannedStart) {
    this.plannedStart = plannedStart;
  }

  private void setPlannedEnd(TimePoint plannedEnd) {
    this.plannedEnd = plannedEnd;
  }


  private Activity() {
  }

  public static ActivityBuilder builder() {
    return new ActivityBuilder();
  }


  public static class ActivityBuilder {

    private String activityName;
    private TimePoint plannedStart;
    private TimePoint plannedEnd;

    public ActivityBuilder name(String activityName) {
      this.activityName = activityName;
      return this;
    }

    public ActivityBuilder plannedStartAt(TimePoint startDate) {
      this.plannedStart = startDate;
      return this;
    }

    public ActivityBuilder plannedEndAt(TimePoint plannedEnd) {
      this.plannedEnd = plannedEnd;
      return this;
    }

    public Activity build() {
      Activity result = new Activity();
      result.setName(this.activityName);
      result.setPlannedStart(this.plannedStart);
      result.setPlannedEnd(this.plannedEnd);
      return result;
    }
  }
}