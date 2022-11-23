package br.ufpb.dcx.tdm.notification;

import com.intellij.ide.BrowserUtil;
import com.intellij.notification.*;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.project.Project;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.NotNull;

public class TracyNotification {

    private static final NotificationGroup NOTIFICATION_GROUP = new NotificationGroup("Connection Error", NotificationDisplayType.BALLOON, true);

    private static String notificationGroupId = "Tracy Plugin";
    private static String changelogLink = "https://github.com/tracy-td/tracy-intelij/blob/main/CHANGELOG.md";
    private static String repoGithub = "https://github.com/tracy-td/tracy-intelij";

    private static String welcomeMessage = "Tracy plugin has been successfully loaded";

    public static void notify(String content) {
        notify(null, content);
    }

    public static void notify(Project project, String content) {
        final Notification notification = NOTIFICATION_GROUP.createNotification(content, NotificationType.ERROR);
        notification.notify(project);
    }

    public static void notifyFirstlyDownloaded(Project project) {
        String title = "Tracy plugin is running";
        Notification notification = NotificationGroupManager.getInstance().getNotificationGroup(notificationGroupId)
                .createNotification(title, welcomeMessage, NotificationType.INFORMATION);
        addNotificationActions(notification);
        notification.notify(project);
    }

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
