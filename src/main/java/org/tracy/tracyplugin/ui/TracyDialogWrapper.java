package org.tracy.tracyplugin.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;
import org.tracy.tracyplugin.facade.RetrofitInit;

import javax.swing.*;
import java.awt.*;

public class TracyDialogWrapper extends DialogWrapper {

    RetrofitInit retrofitInit = new RetrofitInit();

    public TracyDialogWrapper(@Nullable Project project, boolean canBeParent) {
        super(true);
        init();
        show();
        setTitle("URL Tracy");
    }


    @Override
    protected @Nullable JComponent createCenterPanel() {

        JLabel title = new JLabel("Please, insert the tracy url");
        JPanel dialogPanel = new JPanel(new BorderLayout());
        JButton testButton = new JButton();
        JTextField baseUrl = new JTextField();

        title.setFont(new java.awt.Font("Arial Black", Font.PLAIN, 14));
        title.setText("Insert a tracy url: ");

        dialogPanel.add(title, BorderLayout.CENTER);

        baseUrl.setEnabled(true);
        baseUrl.setEditable(true);

        dialogPanel.add(baseUrl);
        dialogPanel.add(testButton);

        testButton.addActionListener(actionEvent -> {
            retrofitInit.setBaseUrl(baseUrl.getText());
        });

        return dialogPanel;
    }

}
