package org.tracy.tracyplugin.services;

import com.intellij.openapi.project.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tracy.tracyplugin.facade.ConnectionTracy;
import org.tracy.tracyplugin.notification.TracyNotification;

import java.io.IOException;
import java.net.URISyntaxException;

public class RequestFileService {
    private static final Logger LOG = LoggerFactory.getLogger(RequestFileService.class);

    private final ConnectionTracy connectionTracy = new ConnectionTracy();

    public Integer getClassification(Project project, String fileName) throws URISyntaxException, IOException {
        Integer response;
        LOG.warn("Send a request for fileName: {}", fileName);
        response = connectionTracy.connectTracyTd(project, fileName);
        LOG.warn("Response return with status code {}", response);
        if (response == -1) TracyNotification.notifyNoConnectionTracy(project, "Error to connect with tracy");
        return response;
    }
}
