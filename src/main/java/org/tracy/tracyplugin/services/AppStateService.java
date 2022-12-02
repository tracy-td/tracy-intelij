package org.tracy.tracyplugin.services;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.tracy.tracyplugin.state.beans.AppState;
import org.tracy.tracyplugin.state.beans.ColorSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@State(name = "tracy", storages = {@Storage("colors.xml")})
public class AppStateService  implements PersistentStateComponent<AppState> {

    private final AppState state = new AppState();

    @Nullable
    @Override
    public AppState getState() {
        return this.state;
    }

    @Override
    public void loadState(@NotNull AppState state) {
        XmlSerializerUtil.copyBean(state, this.state);
    }

    public List<ColorSettings> getColorSettingsList() {
        return state.colorSettingsList.stream()
                .map(ColorSettings::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
