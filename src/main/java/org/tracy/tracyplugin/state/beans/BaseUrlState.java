package org.tracy.tracyplugin.state.beans;

import com.intellij.util.xmlb.annotations.Attribute;
import com.intellij.util.xmlb.annotations.Property;
import com.intellij.util.xmlb.annotations.Tag;

@Tag("base")
@Property(alwaysWrite = true)
public class BaseUrlState {

    @Attribute("value")
    public String base;

    @SuppressWarnings({"UnusedDeclaration"})
    public BaseUrlState() {
        this.base = "http://td.phoebus.local/";
    }

    public BaseUrlState(String base) {
        this.base = base;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }
}
