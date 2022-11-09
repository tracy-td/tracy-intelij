package br.ufpb.dcx.tdm.utils;

import com.intellij.ide.projectView.ProjectView;
import com.intellij.ide.projectView.impl.AbstractProjectViewPane;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class UtilsUI {
    private static final Logger LOG = Logger.getInstance(UtilsUI.class);

    private static volatile long lastInvokeTimeMs = 0;
    private static volatile long lastUpdateTimeMs = 0;
    private static final int UPDATE_THRESHOLD = 100;

    public static void updateUI(@Nullable Project project) {
        debounce(() -> {
            updateProjectView(project);
            updateOpenTabs(project);
        });
    }

    public static void updateOpenTabs(@Nullable Project project) {
        if (project == null) return;
        FileEditorManagerEx editorManagerEx = FileEditorManagerEx.getInstanceEx(project);
        VirtualFile[] openFiles = editorManagerEx.getOpenFiles();
        for (VirtualFile openFile : openFiles) {
            editorManagerEx.updateFilePresentation(openFile);
        }
    }

    public static void updateProjectView(@Nullable Project project) {
        if (project == null) return;
        ProjectView projectView = ProjectView.getInstance(project);
        AbstractProjectViewPane projectViewPane = projectView.getCurrentProjectViewPane();
        if (projectViewPane != null) {
            projectViewPane.updateFromRoot(true);
        }
    }

    private static void debounce(@NotNull Runnable task) {
        lastInvokeTimeMs = System.currentTimeMillis();
        EDTInvoker.invokeLater(() -> {
            long currentTimeMs = System.currentTimeMillis();
            long invokeExpirationTimeMs = lastInvokeTimeMs + UPDATE_THRESHOLD - 10;
            long updateExpirationTimeMs = lastUpdateTimeMs + UPDATE_THRESHOLD;
            if ((currentTimeMs >= invokeExpirationTimeMs) || (currentTimeMs >= updateExpirationTimeMs)) {
                task.run();
                lastUpdateTimeMs = currentTimeMs;
            }
        }, UPDATE_THRESHOLD);
    }

    @NotNull
    public static String getSafeLabelString(@Nullable String labelString, String fallbackString, int maxLength) {
        String safeLabelString = labelString != null ? labelString.trim() : "";
        if (safeLabelString.equals("")) {
            return fallbackString;
        }
        if (safeLabelString.length() > maxLength) {
            return safeLabelString.substring(0, maxLength) + "...";
        }
        return safeLabelString;
    }

    public static String addTrailingSlash(@NotNull String path) {
        return path.endsWith("/") ? path : path + "/";
    }

}
