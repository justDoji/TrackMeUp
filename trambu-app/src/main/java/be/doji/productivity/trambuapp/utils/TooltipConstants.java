package be.doji.productivity.trambuapp.utils;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;

public final class TooltipConstants {

    /**
     * Utility classes should not have a public or default constructor
     */
    private TooltipConstants() {
    }

    public static final FontAwesomeIcon TOOLTIP_DEFAULT_ICON = FontAwesomeIcon.INFO_CIRCLE;
    public static final String TOOLTIP_TEXT_ACTIVITY_DONE = "Click to toggle activity completion on";
    public static final String TOOLTIP_TEXT_ACTIVITY_NOT_DONE = "Click to toggle activity completion off";
    public static final String TOOLTIP_TEXT_ACTIVITY_EDIT = "Make changes to the activity";
    public static final String TOOLTIP_TEXT_ACTIVITY_TIMING_CONTROL_START = "Start tracking time spent on activity";
    public static final String TOOLTIP_TEXT_ACTIVITY_TIMING_CONTROL_STOP = "Start tracking time spent on activity";
    public static final String TOOLTIP_TEXT_ACTIVITY_DELETE = "Permanently delete this activity (can not be undone)";

    public static final String TOOLTIP_TEXT_CONTROL_REFRESH = "Refresh data from files";
    public static final String TOOLTIP_TEXT_CONTROL_CREATE = "Create a new activity";
    public static final String TOOLTIP_TEXT_CONTROL_FILTER_RESET = "Clear active filters";
    public static final String TOOLTIP_TEXT_CONTROL_FILTER_DONE = "Filter all completed activities";

}
