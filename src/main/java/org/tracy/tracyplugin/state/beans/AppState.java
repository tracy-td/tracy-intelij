package org.tracy.tracyplugin.state.beans;

import com.intellij.util.xmlb.annotations.Tag;
import com.intellij.util.xmlb.annotations.XCollection;
import org.tracy.tracyplugin.state.DefaultSettings;

import java.util.List;

public class AppState {
    @Tag("colors")
    @XCollection()
    public List<ColorSettings> colorSettingsList;

    public AppState() {
        colorSettingsList = DefaultSettings.getColorSettingsList();
    }

}
