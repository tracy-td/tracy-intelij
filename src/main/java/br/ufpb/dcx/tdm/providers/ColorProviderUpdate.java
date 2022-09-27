package br.ufpb.dcx.tdm.providers;

import br.ufpb.dcx.tdm.services.ProjectStateService;
import br.ufpb.dcx.tdm.state.beans.ColorSettings;
import com.intellij.openapi.fileEditor.impl.EditorTabColorProvider;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.io.IOException;

public class ColorProviderUpdate implements EditorTabColorProvider, DumbAware {

    @Nullable
    public Color getColor(@NotNull Project project, @NotNull VirtualFile file) throws IOException {
        ProjectStateService projectStateService = ProjectStateService.getInstance(project);
        assert projectStateService != null;
        Integer colorId = projectStateService.files.getNodeColorId(file);
        if (colorId == null) return null;
        ColorSettings colorSettings = projectStateService.colors.getColorSettingsById(colorId);
        if (!colorSettings.isSetAndEnabled()) return null;
        return colorSettings.getColor();
    }

    @Override
    public @Nullable Color getEditorTabColor(@NotNull Project project, @NotNull VirtualFile file) {
        try {
            return this.getColor(project, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @Nullable Color getProjectViewColor(@NotNull Project project, @NotNull VirtualFile file) {
        try {
            return this.getColor(project, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
