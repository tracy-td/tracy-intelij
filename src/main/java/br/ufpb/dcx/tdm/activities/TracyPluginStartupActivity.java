package br.ufpb.dcx.tdm.activities;

import br.ufpb.dcx.tdm.notification.TracyNotification;
import br.ufpb.dcx.tdm.services.AppStateService;
import br.ufpb.dcx.tdm.services.ProjectStateService;
import com.intellij.notification.NotificationAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import org.jetbrains.annotations.NotNull;

public class TracyPluginStartupActivity implements StartupActivity {

    @Override
    public void runActivity(@NotNull Project project) {
        TracyNotification.notifyFirstlyDownloaded(project);
    }
}
