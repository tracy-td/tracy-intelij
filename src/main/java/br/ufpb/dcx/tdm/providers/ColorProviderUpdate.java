// Copyright 2022 Ayty s.r.o. Use of this source code is governed by the license that can be found in the LICENSE file.
package br.ufpb.dcx.tdm.providers;

import br.ufpb.dcx.tdm.services.ProjectStateService;
import br.ufpb.dcx.tdm.state.beans.ColorSettings;
import com.intellij.notification.NotificationAction;
import com.intellij.openapi.fileEditor.impl.EditorTabColorProvider;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.io.IOException;


/**
 * This class is responsible for changing the colors of the files
 * in the editor tab and in the elements tree on the left
 * ColorProviderUpdate get the current color from the file in the view provider
 * and tab and changes them to the color specified in the colorSettings class
 *
 * @see EditorTabColorProvider
 * @see DumbAware
 * @see ColorSettings
 *
 * @author Marcos Ludgério
 */
public class ColorProviderUpdate implements EditorTabColorProvider, DumbAware {

    /**
     * This method retrieves the current color from the file within a project
     *
     * @param project the project that is open
     * @param file file that will change the color
     *
     * @return color file
     *
     * @see VirtualFile
     * @throws IOException if the file is not found
     */
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

    /**
     * This method gets the color of the file in the editor tab
     *
     * @param project the project that is open
     * @param file file that will change the color
     *
     * @return color file
     */
    @Override
    public @Nullable Color getEditorTabColor(@NotNull Project project, @NotNull VirtualFile file) {
        try {
            return this.getColor(project, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method gets the color of the file in the view provider
     *
     * @param project the project that is open
     * @param file file that will change the color
     *
     * @return color file
     *
     * @see com.intellij.psi.FileViewProvider
     */

    @Override
    public @Nullable Color getProjectViewColor(@NotNull Project project, @NotNull VirtualFile file) {
        try {
            return this.getColor(project, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
