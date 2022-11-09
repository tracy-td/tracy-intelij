package br.ufpb.dcx.tdm.state;

import br.ufpb.dcx.tdm.services.ProjectStateService;
import br.ufpb.dcx.tdm.state.beans.ColorSettings;
import br.ufpb.dcx.tdm.state.beans.ProjectState;
import com.intellij.openapi.diagnostic.Logger;
import org.jetbrains.annotations.NotNull;

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
