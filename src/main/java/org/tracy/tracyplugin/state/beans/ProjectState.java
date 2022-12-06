package org.tracy.tracyplugin.state.beans;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.util.xmlb.annotations.Property;
import com.intellij.util.xmlb.annotations.Tag;
import com.intellij.util.xmlb.annotations.XCollection;
import org.tracy.tracyplugin.services.AppStateService;
import org.tracy.tracyplugin.services.ProjectStateService;

import java.util.ArrayList;
import java.util.List;

public class ProjectState {
    private static final Logger LOG = Logger.getInstance(ProjectState.class);

    @Tag("files")
    @XCollection()
    @Property(alwaysWrite = true)
    public List<HighlightedFile> highlightedFileList;

    @Tag("colors")
    @XCollection()
    @Property(alwaysWrite = true)
    public List<ColorSettings> colorSettingsList;

    @Tag("baseUrl")
    @Property(alwaysWrite = true)
    public BaseUrlState baseState;

    public ProjectState() {
        AppStateService appStateService = new AppStateService();
        LOG.debug("*** INITIALIZED PROJECT STATE ***");
        highlightedFileList = new ArrayList<>();
        colorSettingsList = appStateService.getColorSettingsList();
        baseState = new BaseUrlState();
        colorSettingsList.forEach(c -> LOG.debug("COLOR SETTINGS LIST: " + colorSettingsList));
    }
}
