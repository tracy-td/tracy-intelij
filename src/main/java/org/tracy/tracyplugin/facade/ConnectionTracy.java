package org.tracy.tracyplugin.facade;

import com.google.common.base.Charsets;
import com.intellij.openapi.project.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        String tracyBaseUrl = projectStateService.getBaseState().getBase() + "api/v1/classification?name=%s";
        String searchUrl = String.format(tracyBaseUrl, URLEncoder.encode(filename, Charsets.UTF_8));
        LOG.warn("SENDING REQUEST: " + searchUrl);

        HttpURLConnection connection;

        connection = (HttpURLConnection) new URI(searchUrl).toURL().openConnection();

        LOG.warn("STATUS CODE REQUEST: " + connection.getResponseCode());

        StringBuilder responseBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(connection.getInputStream())))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                responseBuilder.append(line);
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }//http://localhost:8081/h2-console

        if(connection.getResponseCode() == 200) return Integer.parseInt(responseBuilder.toString());
        if (responseBuilder.toString().equals("")) return -1;
        return 1000;
    }
}
