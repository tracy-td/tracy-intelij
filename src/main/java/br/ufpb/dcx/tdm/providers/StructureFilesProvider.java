package br.ufpb.dcx.tdm.providers;

import br.ufpb.dcx.tdm.notification.TracyNotification;
import br.ufpb.dcx.tdm.services.ProjectStateService;
import com.intellij.ide.projectView.TreeStructureProvider;
import com.intellij.ide.projectView.ViewSettings;
import com.intellij.ide.projectView.impl.nodes.PsiFileNode;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import com.intellij.openapi.application.ApplicationNamesInfo;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class StructureFilesProvider implements TreeStructureProvider {
    private LocalDateTime lastUpdate;

    private static final Logger LOG = LoggerFactory.getLogger(StructureFilesProvider.class);

    public StructureFilesProvider() {
        super();
        this.lastUpdate = LocalDateTime.now();
    }

    @Override
    public @NotNull Collection<AbstractTreeNode<?>> modify(@NotNull AbstractTreeNode<?> parent, @NotNull Collection<AbstractTreeNode<?>> children, ViewSettings settings) {
        List<AbstractTreeNode<?>> nodes = new LinkedList<>();
        LocalDateTime now = LocalDateTime.now();
        ProjectStateService service = ProjectStateService.getInstance(parent.getProject());

        assert service != null;
        long diff = ChronoUnit.SECONDS.between(lastUpdate, now);
        children.forEach(child -> {
            if (child instanceof PsiFileNode) {
                VirtualFile file = ((PsiFileNode) child).getVirtualFile();
                if (file != null && Objects.equals(file.getExtension(), "java")) {
                    if (diff >= 10) {
                        lastUpdate = now;
                        try {
                            service.updateColorFile(file);
                        } catch (IOException | URISyntaxException e) {
                            LOG.error("File classification return a error: {}", e.getMessage());
                            TracyNotification.notify(e.getMessage());
                        }
                    }
                }
            }
            nodes.add(child);
        });

        return nodes;
    }
}



