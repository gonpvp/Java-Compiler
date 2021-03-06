package fr.belinguier.java.compiler.builder;

import fr.belinguier.java.access.FieldAccessFlag;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Eliott Belinguier
 */
public class FieldBuilder {

    private FieldAccessFlag[] accessFlags;
    private final String name;
    private final String descriptor;
    protected final AttributeManager attributeManager;

    protected FieldBuilder(String name, String descriptor) {
        this.name = name;
        this.descriptor = descriptor;
        this.attributeManager = new AttributeManager();
        this.accessFlags = new FieldAccessFlag[] {FieldAccessFlag.PUBLIC};
    }

    public void setAccessFlags(FieldAccessFlag... accessFlags) {
        if (accessFlags == null)
            return;
        this.accessFlags = accessFlags;
    }

    public FieldAccessFlag[] getAccessFlags() {
        return Arrays.copyOf(this.accessFlags, this.accessFlags.length);
    }

    public String getName() {
        return this.name;
    }

    public String getDescriptor() {
        return this.descriptor;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FieldBuilder))
            return false;
        return obj.hashCode() == hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name);
    }
}
