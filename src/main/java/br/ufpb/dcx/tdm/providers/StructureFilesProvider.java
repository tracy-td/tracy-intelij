package br.ufpb.dcx.tdm.providers;

import br.ufpb.dcx.tdm.services.ProjectStateService;
import com.intellij.ide.projectView.TreeStructureProvider;
import com.intellij.ide.projectView.ViewSettings;
import com.intellij.ide.projectView.impl.nodes.PsiFileNode;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class StructureFilesProvider implements TreeStructureProvider {
    private LocalDateTime lastUpdate;

    public StructureFilesProvider() {
        super();
        this.lastUpdate = LocalDateTime.now();
    }

    @Override
    public @NotNull Collection<AbstractTreeNode<?>> modify(@NotNull AbstractTreeNode<?> parent, @NotNull Collection<AbstractTreeNode<?>> children, ViewSettings settings) {

        List<AbstractTreeNode<?>> nodes = new LinkedList<>();
        LocalDateTime now = LocalDateTime.now();
        ProjectStateService service = ProjectStateService.getInstance(parent.getProject());
        long diff = ChronoUnit.SECONDS.between(lastUpdate, now);
        for (AbstractTreeNode child : children) {
            if (child instanceof PsiFileNode) {
                VirtualFile file = ((PsiFileNode) child).getVirtualFile();
                if (file != null && Objects.equals(file.getExtension(), "java")) {
                    if (diff >= 1) {
                        lastUpdate = now;
                        try {
                            assert service != null;
                            service.updateColorFile(file);
                        } catch (IOException | URISyntaxException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
            nodes.add(child);
        }
        return nodes;
    }
}


