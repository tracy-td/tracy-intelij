// Copyright 2022 Ayty. Use of this source code is governed by the license that can be found in the LICENSE file.
package br.ufpb.dcx.tdm.state;

import br.ufpb.dcx.tdm.state.beans.ColorSettings;
import com.intellij.ui.JBColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultSettings {
    public static List<ColorSettings> getColorSettingsList() {
        return new ArrayList(Arrays.asList(
                new ColorSettings(4, new JBColor(0xde2c2c, 0x4f060d), "Critical", true),
                new ColorSettings(3, new JBColor(0xfefc22, 0x3f371b), "High", true),
                new ColorSettings(2, new JBColor(0x00b5ff, 0x171a34), "Medium", true),
                new ColorSettings(1, new JBColor(0x28ff2e, 0x162c16), "Low", true)
                ));
    }
}
