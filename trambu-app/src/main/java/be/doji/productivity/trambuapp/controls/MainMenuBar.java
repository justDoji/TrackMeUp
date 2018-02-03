package be.doji.productivity.trambuapp.controls;

import be.doji.productivity.trambuapp.utils.DisplayUtils;
import be.doji.productivity.trambuapp.utils.TooltipConstants;
import be.doji.productivity.trambuapp.components.view.ActivityPageView;
import be.doji.productivity.trambuapp.components.view.OptionsPageView;
import be.doji.productivity.trambuapp.components.view.TimesheetPageView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import kotlin.jvm.JvmClassMappingKt;
import org.jetbrains.annotations.NotNull;
import tornadofx.UIComponent;
import tornadofx.View;
import tornadofx.ViewTransition;

public class MainMenuBar extends UIComponent {

    private HBox root;
    private View superView;

    public MainMenuBar(View parentView) {
        super();
        this.superView = parentView;
        root = new HBox();
        root.setSpacing(5.0);
        root.getChildren().add(createActivityButton());
        root.getChildren().add(createTimesheetButton());
        root.getChildren().add(createOptionButton());

    }

    @NotNull private Button createActivityButton() {
        Button activityPage = new Button("Activity overview");
        FontAwesomeIconView activityIcon = DisplayUtils.createStyledIcon(FontAwesomeIcon.CALENDAR_ALT);
        activityPage.setGraphic(activityIcon);
        activityPage.setOnAction(event -> {
            if (!superView.getClass().equals(ActivityPageView.class)) {
                superView.replaceWith(JvmClassMappingKt.getKotlinClass(ActivityPageView.class),
                        new ViewTransition.Slide(Duration.seconds(0.3), ViewTransition.Direction.LEFT), true, true);
            }
        });
        activityPage.setTooltip(DisplayUtils.createTooltip(TooltipConstants.TOOLTIP_TEXT_MENU_ACTIVITIES));
        return activityPage;
    }

    @NotNull private Button createTimesheetButton() {
        Button timesheetPage = new Button("Timesheet");
        FontAwesomeIconView timesheetIcon = DisplayUtils.createStyledIcon(FontAwesomeIcon.CLOCK_ALT);
        timesheetPage.setGraphic(timesheetIcon);
        timesheetPage.setOnAction(event -> {
            if (!superView.getClass().equals(TimesheetPageView.class)) {
                superView.replaceWith(JvmClassMappingKt.getKotlinClass(TimesheetPageView.class),
                        new ViewTransition.Slide(Duration.seconds(0.3), ViewTransition.Direction.RIGHT), true, true);
            }
        });
        timesheetPage.setTooltip(DisplayUtils.createTooltip(TooltipConstants.TOOLTIP_TEXT_MENU_TIMESHEET));
        return timesheetPage;
    }

    @NotNull private Button createOptionButton() {
        Button options = new Button("Options");
        FontAwesomeIconView optionIcon = DisplayUtils.createStyledIcon(FontAwesomeIcon.GEARS);
        options.setGraphic(optionIcon);
        options.setOnAction(event -> {
            if (!superView.getClass().equals(OptionsPageView.class)) {
                superView.replaceWith(JvmClassMappingKt.getKotlinClass(OptionsPageView.class),
                        new ViewTransition.Slide(Duration.seconds(0.3), ViewTransition.Direction.DOWN), true, true);
            }
        });
        options.setTooltip(DisplayUtils.createTooltip(TooltipConstants.TOOLTIP_TEXT_MENU_OPTIONS));
        return options;
    }

    @NotNull @Override public Parent getRoot() {
        return root;
    }
}
