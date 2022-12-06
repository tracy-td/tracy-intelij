package org.tracy.tracyplugin.state;

import com.intellij.openapi.diagnostic.Logger;
import org.jetbrains.annotations.NotNull;
import org.tracy.tracyplugin.services.ProjectStateService;
import org.tracy.tracyplugin.state.beans.ColorSettings;
import org.tracy.tracyplugin.state.beans.ProjectState;

public class ProjectColors {

    private static final Logger LOG = Logger.getInstance(ProjectColors.class);

    private final ProjectStateService projectStateService;
    private final ProjectState projectState;

    public ProjectColors(ProjectStateService projectStateService) {
        this.projectStateService = projectStateService;
        this.projectState = projectStateService.getState();
    }


    @NotNull
    public ColorSettings getColorSettingsById(int colorId) {
        return projectState.colorSettingsList.stream()
                .filter(colorSettings -> colorSettings.getId() == colorId)
                .findFirst()
                .orElse(new ColorSettings(colorId, null, "Color " + colorId, false));
    }
}
