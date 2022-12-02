package org.tracy.tracyplugin.state;

import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.tracy.tracyplugin.services.ProjectStateService;
import org.tracy.tracyplugin.state.beans.HighlightedFile;
import org.tracy.tracyplugin.state.beans.ProjectState;
import org.tracy.tracyplugin.utils.UtilsUI;

public class ProjectFiles {
    private final ProjectStateService projectStateService;
    private final ProjectState projectState;

    public ProjectFiles(ProjectStateService projectStateService) {
        this.projectStateService = projectStateService;
        this.projectState = projectStateService.getState();
    }

    public void addNodes(@Nullable VirtualFile file, int colorId) {
        if (file == null) return;
        String filePath = file.getPath();
        projectState.highlightedFileList.add(new HighlightedFile(filePath, colorId));

        UtilsUI.updateUI(projectStateService.getProject());
    }

    public void removeNodes(@Nullable VirtualFile[] files) {
        if (files == null) return;
        for (VirtualFile file : files) {
            assert file != null;
            String path = file.getPath();
            projectState.highlightedFileList.removeIf(node -> node.getPath().equals(path));
        }
        UtilsUI.updateUI(projectStateService.getProject());
    }

    @Nullable
    public Integer getNodeColorId(@NotNull VirtualFile file) {
        Integer colorId = null;
        String pathWithSlash = UtilsUI.addTrailingSlash(file.getPath());
        for (HighlightedFile node : projectState.highlightedFileList) {
            if (pathWithSlash.startsWith(UtilsUI.addTrailingSlash(node.getPath()))) {
                colorId = node.getColorId();
            }
        }
        return colorId;
    }
}
