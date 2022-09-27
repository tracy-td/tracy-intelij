package br.ufpb.dcx.tdm.state.beans;

import com.intellij.ui.ColorUtil;
import com.intellij.util.xmlb.Converter;
import com.intellij.util.xmlb.annotations.Attribute;
import com.intellij.util.xmlb.annotations.Property;
import com.intellij.util.xmlb.annotations.Tag;
import com.intellij.util.xmlb.annotations.Transient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

@Tag("color")
@Property(surroundWithTag = false, alwaysWrite = true, flat = true, style = Property.Style.ATTRIBUTE)
public class ColorSettings {

    @Attribute("id")
    public int _id;

    @Attribute(value = "value", converter = ColorHexConverter.class)
    public Color _color;

    @Attribute("name")
    public String _name;

    @Attribute("enabled")
    public boolean _enabled;

    // an empty constructor is needed for PersistentStateComponent xml serialization
    @SuppressWarnings({"UnusedDeclaration"})
    public ColorSettings() {
    }

    public ColorSettings(int id, Color color, String name, boolean isEnabled) {
        setId(id);
        setColor(color);
        setName(name);
        setEnabled(isEnabled);
    }

    public ColorSettings(ColorSettings colorSettings) {
        setId(colorSettings.getId());
        setColor(colorSettings.getColor());
        setName(colorSettings.getName());
        setEnabled(colorSettings.isEnabled());
    }

    @Override
    public String toString() {
        return "ColorSettings{" +
                ", _color=" + _color +
                ", _name='" + _name + '\'' +
                ", _enabled=" + _enabled +
                '}';
    }

    @Transient
    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    @Transient
    public Color getColor() {
        return _color;
    }

    public void setColor(Color color) {
        this._color = color;
    }

    @Transient
    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }

    @Transient
    public boolean isEnabled() {
        return _enabled;
    }

    public void setEnabled(boolean isEnabled) {
        this._enabled = isEnabled;
    }

    @Transient
    public boolean isSetAndEnabled() {
        return _enabled && _color != null;
    }



    public static class ColorHexConverter extends Converter<Color> {

        @Nullable
        public Color fromString(@Nullable String colorHex) {
            return ColorUtil.fromHex(colorHex, null);
        }

        @Nullable
        public String toString(@Nullable Color color) {
            return color != null ? toHtmlColorWithOptionalAlpha(color) : "";
        }

        public static String toHtmlColorWithOptionalAlpha(@NotNull Color color) {
            return "#" + ColorUtil.toHex(color, color.getAlpha() < 255);
        }
    }

}
