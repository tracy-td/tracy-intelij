// Copyright 2022 Ayty. Use of this source code is governed by the license that can be found in the LICENSE file.
package br.ufpb.dcx.tdm.activities;

import br.ufpb.dcx.tdm.notification.TracyNotification;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import org.jetbrains.annotations.NotNull;

/**
 * This class is responsible for show the notification always the plugin started
 *
 * @author Marcos Ludgerio
 * @see StartupActivity
 */
public class TracyPluginStartupActivity implements StartupActivity {


    /**
     * This method is called when the plugin is loaded in first time
     */
    @Override
    public void runActivity(@NotNull Project project) {
        TracyNotification.notifyFirstlyDownloaded(project);
    }
}
