package org.tracy.tracyplugin.facade;

import com.google.common.base.Charsets;
import com.intellij.openapi.project.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tracy.tracyplugin.notification.TracyNotification;
import org.tracy.tracyplugin.providers.StructureFileProvider;
import org.tracy.tracyplugin.services.ProjectStateService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.zip.GZIPInputStream;

public class ConnectionTracy {
    private static final Logger LOG = LoggerFactory.getLogger(StructureFileProvider.class);

    public Integer connectTracyTd(Project project, String filename) throws URISyntaxException, IOException {

        ProjectStateService projectStateService = ProjectStateService.getInstance(project);

        assert projectStateService != null;
        if(projectStateService.getBaseState().getBase() == null || projectStateService.getBaseState().getBase().equals("")) TracyNotification.notifyNoConnectionTracy(project, "Please, verify Tracy URL");
        String tracyBaseUrl = projectStateService.getBaseState().getBase() + "api/v1/classification?name=%s";
        String searchUrl = String.format(tracyBaseUrl, URLEncoder.encode(filename, Charsets.UTF_8));
        LOG.warn("SENDING REQUEST");



        HttpURLConnection connection;

        connection = (HttpURLConnection) new URI(searchUrl).toURL().openConnection();

        LOG.warn("STATUS CODE REQUEST: " + connection.getResponseCode());

        StringBuilder responseBuilder = new StringBuilder();
        try (InputStreamReader streamReader = new InputStreamReader(connection.getInputStream()); BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }
        }
        if(connection.getResponseCode() == 200) return Integer.parseInt(responseBuilder.toString());
        if (responseBuilder.toString().equals("")) return -1;
        return 1000;
    }
}
