package br.ufpb.dcx.tdm.services;


import br.ufpb.dcx.tdm.state.ProjectColors;
import br.ufpb.dcx.tdm.state.ProjectFiles;
import br.ufpb.dcx.tdm.state.beans.ProjectState;
import br.ufpb.dcx.tdm.utils.UtilsUI;
import com.intellij.configurationStore.StateStorageManagerKt;
import com.intellij.openapi.components.*;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;

@State(name = "TDM", storages = {@Storage("tdm.xml")})
public class ProjectStateService implements PersistentStateComponent<ProjectState> {
    private static final Logger LOG = Logger.getInstance(ProjectStateService.class);

    private final ProjectState state = new ProjectState();
    private Project project;
    public ProjectColors colors = new ProjectColors(this);
    public ProjectFiles files = new ProjectFiles(this);

    RequestFileService requestFileService = new RequestFileService();
    public void updateColorFile(VirtualFile file) throws IOException, URISyntaxException {
         Integer classification = requestFileService.getClassification(file.getNameWithoutExtension());
         this.files.addNodes(file, classification);
    }

    @Nullable
    public static ProjectStateService getInstance(Project e){
        ProjectStateService instance = ServiceManager.getService(e, ProjectStateService.class);
        instance.project = e;
        return instance;
    }

    @Override
    public @Nullable ProjectState getState() {
        return this.state;
    }

    @Override
    public void loadState(@NotNull ProjectState state) {
        XmlSerializerUtil.copyBean(state, this.state);
        UtilsUI.updateUI(project);
    }

    public Project getProject() {
        return project;
    }

    public void saveState() {
        try {
            Method saveComponentManager = StateStorageManagerKt.class
                    .getMethod("saveComponentManager", ComponentManager.class, boolean.class);
            saveComponentManager.invoke(null, project, true);
        } catch (Exception ignored) {
            LOG.debug("Can't force save state in older versions prior to 191.4212.41");
        }
    }
}
