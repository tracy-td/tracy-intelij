package br.ufpb.dcx.tdm.state.beans;

import com.intellij.util.xmlb.annotations.Attribute;
import com.intellij.util.xmlb.annotations.Property;
import com.intellij.util.xmlb.annotations.Tag;
import com.intellij.util.xmlb.annotations.Transient;

@Tag("node")
@Property(surroundWithTag = false, alwaysWrite = true, flat = true, style = Property.Style.ATTRIBUTE)
public class HighlightedFile {

    @Attribute("color")
    public Integer _colorId;

    @Attribute("path")
    public String _path;

    // an empty constructor is needed for PersistentStateComponent xml serialization
    @SuppressWarnings({"UnusedDeclaration"})
    public HighlightedFile() {
    }

    public HighlightedFile(String path, Integer colorId) {
        this._path = path;
        this._colorId = colorId;
    }

    @Transient
    public String getPath() {
        return _path;
    }

    @Transient
    public int getColorId() {
        return _colorId;
    }

}
