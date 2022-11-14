package br.ufpb.dcx.tdm.services;

import br.ufpb.dcx.tdm.state.beans.AppState;
import br.ufpb.dcx.tdm.state.beans.ColorSettings;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@State(name = "tracy", storages = {@Storage("colors.xml")})
public class AppStateService implements PersistentStateComponent<AppState> {

    private final AppState state = new AppState();

    @Override
    public @Nullable AppState getState() {
        return this.state;
    }

    @Override
    public void loadState(@NotNull AppState state) {
        XmlSerializerUtil.copyBean(state, this.state);
    }

    @Nullable
    public List<ColorSettings> getColorSettingsList() {
        return state.colorSettingsList.stream()
                .map(ColorSettings::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }


}
