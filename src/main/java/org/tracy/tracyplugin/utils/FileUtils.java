package org.tracy.tracyplugin.utils;

import com.intellij.openapi.project.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tracy.tracyplugin.providers.StructureFileProvider;
import org.tracy.tracyplugin.services.ProjectStateService;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileUtils {
    private static final Logger LOG = LoggerFactory.getLogger(StructureFileProvider.class);

    public void updateBaseUrlInTheFile(Project project, String newBase) {
        ProjectStateService projectStateService = ProjectStateService.getInstance(project);
        assert projectStateService != null;
        LOG.error("UPDATE BASEURL: " + newBase);
        projectStateService.updateBase(newBase);
    }
}
