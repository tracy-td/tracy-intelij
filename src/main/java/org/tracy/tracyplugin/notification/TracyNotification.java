// Copyright 2022 Ayty Use of this source code is governed by the license that can be found in the LICENSE file.
package org.tracy.tracyplugin.notification;

import com.intellij.ide.BrowserUtil;
import com.intellij.notification.*;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.project.Project;
import org.tracy.tracyplugin.facade.RetrofitInit;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicReference;


/**
 * This class is a notification has a title, subtitle, and actions.
 * The notifications, generally, are shown in the balloons that appear on the screen when the corresponding events take place.
 *
 * @author Marcos Ludgério
 * @see NotificationAction
 * @see com.intellij.notification.SingletonNotificationManager
 */
public class TracyNotification {
    private static final NotificationGroup NOTIFICATION_GROUP = new NotificationGroup("Connection Error", NotificationDisplayType.BALLOON, true);

    private static String notificationGroupId = "Tracy Plugin";


    private static String changelogLink = "https://github.com/tracy-td/tracy-intelij/blob/main/CHANGELOG.md";
    private static String repoGithub = "https://github.com/tracy-td/tracy-intelij";

    private static String welcomeMessage = "Tracy plugin has been successfully loaded";
    private static String errorMessage = "Unable to connect to tracy";

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
        Notification notification = NotificationGroupManager.getInstance().getNotificationGroup(notificationGroupId).createNotification(title, welcomeMessage, NotificationType.INFORMATION);
        addNotificationActions(notification);
        notification.notify(project);
    }

    /**
     * This method return error connection message if a tracy is offline
     *
     * @param project the project notification
     */
    public static void notifyNoConnectionTracy(Project project, String content, RetrofitInit retrofitInit) {
        String title = "Tracy error connection";
        Notification notification = NotificationGroupManager.getInstance().getNotificationGroup(notificationGroupId).createNotification(title, content, NotificationType.ERROR);
        addDialogErrorMessage(project, notification, retrofitInit);
        notification.notify(project);
    }

    /**
     * This method add a dialog for insert Tracy's URL
     *
     * @param notification the notification that will have the actions added
     */
    public static void addDialogErrorMessage(Project project, Notification notification, RetrofitInit retrofitInit) {
        AtomicReference<String> baseUrlUpdated = new AtomicReference<>(retrofitInit.getBaseUrl());
        AnAction showDialogBaseUrl = NotificationAction.createSimple("Update URL Tracy", () -> {
            baseUrlUpdated.set(JOptionPane.showInputDialog("Insert Tracy-TD base URL: "));
        });
        retrofitInit.setBaseUrl(baseUrlUpdated.get());
        System.out.println("Update base url");
        System.out.println(retrofitInit.getBaseUrl());
        System.out.println("ESSA MERDAAAAAA");
        notification.addAction(showDialogBaseUrl);
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
