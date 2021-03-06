package fr.belinguier.java.compiler.builder;

import fr.belinguier.java.access.MethodAccessFlag;
import fr.belinguier.java.attribute.Attribute;
import fr.belinguier.java.attribute.AttributeCode;
import fr.belinguier.java.compiler.builder.code.Instruction;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Eliott Belinguier
 */
public class MethodBuilder {

    private MethodAccessFlag[] accessFlags;
    private final String name;
    private final String descriptor;
    protected final AttributeManager attributeManager;

    protected MethodBuilder(ClassBuilder classBuilder, String name, String descriptor) {
        this.name = name;
        this.descriptor = descriptor;
        this.attributeManager = new AttributeManager();
        this.accessFlags = new MethodAccessFlag[] {MethodAccessFlag.PUBLIC};
    }

    public void setAccessFlags(MethodAccessFlag... accessFlags) {
        if (accessFlags == null)
            return;
        this.accessFlags = accessFlags;
    }

    public MethodAccessFlag[] getAccessFlags() {
        return Arrays.copyOf(this.accessFlags, this.accessFlags.length);
    }

    public String getName() {
        return this.name;
    }

    public String getDescriptor() {
        return this.descriptor;
    }

    public boolean setCode(Object... instructions) {
        Attribute attribute;
        AttributeCode attributeCode;

        if (instructions == null || instructions.length < 1)
            return this.attributeManager.remove("Code");
        attribute = this.attributeManager.get("Code");
        if (attribute instanceof AttributeCode) {
            attributeCode = (AttributeCode) attribute;
            attributeCode.codes = CodeBuilder.build(instructions);
            return attributeCode.codes != null;
        }
        attributeCode = new AttributeCode();
        attributeCode.codes = CodeBuilder.build(instructions);
        return this.attributeManager.register("Code", attributeCode) && attributeCode.codes != null;
    }

    public byte[] getCode() {
        Attribute attribute = this.attributeManager.get("Code");
        byte[] code;

        if (!(attribute instanceof AttributeCode))
            return null;
        code = ((AttributeCode) attribute).codes;
        if (code == null)
            return null;
        return Arrays.copyOf(code, code.length);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MethodBuilder))
            return false;
        return obj.hashCode() == hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.descriptor);
    }
}
