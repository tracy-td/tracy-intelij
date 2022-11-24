// Copyright 2022 Ayty. Use of this source code is governed by the license that can be found in the LICENSE file.
package br.ufpb.dcx.tdm.state.beans;

import br.ufpb.dcx.tdm.state.DefaultSettings;
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
