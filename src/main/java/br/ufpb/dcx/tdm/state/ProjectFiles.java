// Copyright 2022 Ayty. Use of this source code is governed by the license that can be found in the LICENSE file.
package br.ufpb.dcx.tdm.state;

import br.ufpb.dcx.tdm.services.ProjectStateService;
import br.ufpb.dcx.tdm.state.beans.HighlightedFile;
import br.ufpb.dcx.tdm.state.beans.ProjectState;
import br.ufpb.dcx.tdm.utils.UtilsUI;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
