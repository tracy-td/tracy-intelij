// Copyright 2022 Ayty. Use of this source code is governed by the license that can be found in the LICENSE file.
package br.ufpb.dcx.tdm.providers;

import br.ufpb.dcx.tdm.notification.TracyNotification;
import br.ufpb.dcx.tdm.services.ProjectStateService;
import com.intellij.ide.projectView.TreeStructureProvider;
import com.intellij.ide.projectView.ViewSettings;
import com.intellij.ide.projectView.impl.nodes.PsiFileNode;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * This class is the core of the plugin.
 * It is responsible for checking all files in the element tree, retrieving
 * the names and submitting them for classification.
 * This class also implements the TreeStructureProvider interface which
 * has a method that scans the tree of elements recursively
 *
 * @see TreeStructureProvider
 *
 * @author Marcos Ludgério
 */
public class StructureFilesProvider implements TreeStructureProvider {
    private LocalDateTime lastUpdate;

    private static final Logger LOG = LoggerFactory.getLogger(StructureFilesProvider.class);

    /**
     * No arg constructor and set the lastUpdate
     */
    public StructureFilesProvider() {
        super();
        this.lastUpdate = LocalDateTime.now();
    }

    /**
     * This method is an override that is responsible for modifying the files in the element tree.
     * It receives the tree elements and a variable for settings
     * Also this method contains a timer that periodically checks the files
     *
     * @param parent is an element immediately above the file
     * @param children is all child elements
     * @param settings is an object to change default settings
     *
     * @return a list of elements in the tree
     *
     * @see TreeStructureProvider#modify(AbstractTreeNode, Collection, ViewSettings)
     * @see PsiFileNode
     * @see AbstractTreeNode
     *
     * @author Marcos Ludgério
     *
     * @throw IOException
     * @throw URISyntaxException
     */
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



