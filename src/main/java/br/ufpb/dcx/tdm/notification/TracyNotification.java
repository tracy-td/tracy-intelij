// Copyright 2022 Ayty Use of this source code is governed by the license that can be found in the LICENSE file.
package br.ufpb.dcx.tdm.notification;

import com.intellij.ide.BrowserUtil;
import com.intellij.notification.*;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.project.Project;


/**
 * This class is a notification has a title, subtitle, and actions.
 * The notifications, generally, are shown in the balloons that appear on the screen when the corresponding events take place.
 *
 *
 * @see NotificationAction
 * @see com.intellij.notification.SingletonNotificationManager
 *
 * @author Marcos Ludgério
 */
public class TracyNotification {

    /**
     * All the constants used in notification
     */

    private static final NotificationGroup NOTIFICATION_GROUP = new NotificationGroup("Connection Error", NotificationDisplayType.BALLOON, true);

    private static String notificationGroupId = "Tracy Plugin";
    private static String changelogLink = "https://github.com/tracy-td/tracy-intelij/blob/main/CHANGELOG.md";
    private static String repoGithub = "https://github.com/tracy-td/tracy-intelij";

    private static String welcomeMessage = "Tracy plugin has been successfully loaded";

    /**
     * Notify is a method invokes the notification and displays with its message
     *
     * @param content is a notification message
     */
    public static void notify(String content) {
        notify(null, content);
    }

    /**
     * Notify is a method invokes the notification and displays with its message
     *
     * @param content is a notification message
     * @param project the project notification
     */
    public static void notify(Project project, String content) {
        final Notification notification = NOTIFICATION_GROUP.createNotification(content, NotificationType.ERROR);
        notification.notify(project);
    }

    /**
     * This method is called the first time the plugin is loaded with the welcome message
     *
     * @param project the project notification
     */
    public static void notifyFirstlyDownloaded(Project project) {
        String title = "Tracy plugin is running";
        Notification notification = NotificationGroupManager.getInstance().getNotificationGroup(notificationGroupId)
                .createNotification(title, welcomeMessage, NotificationType.INFORMATION);
        addNotificationActions(notification);
        notification.notify(project);
    }

    /**
     * This method adds actions to notifications to view the changelog and plugin source code.
     *
     * @param notification the notification that will have the actions added
     */
    public static void addNotificationActions(Notification notification) {
        AnAction actionChangelog = NotificationAction.createSimple("Changelog", () -> {
            BrowserUtil.browse(changelogLink);
        });

        AnAction actionGithub = NotificationAction.createSimple("Github", () -> {
            BrowserUtil.browse(repoGithub);
        });

        notification.addAction(actionChangelog);
        notification.addAction(actionGithub);
    }
}
