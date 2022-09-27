package br.ufpb.dcx.tdm.state.beans;

import br.ufpb.dcx.tdm.state.DefaultSettings;
import br.ufpb.dcx.tdm.state.beans.ColorSettings;
import com.intellij.util.xmlb.annotations.Tag;
import com.intellij.util.xmlb.annotations.XCollection;

import java.util.List;

public class AppState {
    @Tag("colors")
    @XCollection()
    public List<ColorSettings> colorSettingsList;

    public AppState() {
        colorSettingsList = DefaultSettings.getColorSettingsList();
    }
}
