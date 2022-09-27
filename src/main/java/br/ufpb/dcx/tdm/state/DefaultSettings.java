package br.ufpb.dcx.tdm.state;

import br.ufpb.dcx.tdm.state.beans.ColorSettings;
import com.intellij.ui.JBColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultSettings {
    public static List<ColorSettings> getColorSettingsList() {
        return new ArrayList(Arrays.asList(
                new ColorSettings(4, new JBColor(0xde2c2c, 0x4f060d), "Color 1", true),
                new ColorSettings(1, new JBColor(0x28ff2e, 0x162c16), "Color 2", true),
                new ColorSettings(3, new JBColor(0x00b5ff, 0x171a34), "Color 3", true),
                new ColorSettings(2, new JBColor(0xfefc22, 0x3f371b), "Color 4", true)
                ));
    }
}
