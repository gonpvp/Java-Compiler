package fr.belinguier.java.compiler.builder;

import java.util.Objects;

/**
 * @author Eliott Belinguier
 */
public class LocalVariableBuilder {

    private final String name;
    private final String descriptor;
    private final short startPc;
    private final short length;

    protected LocalVariableBuilder(String name, String signature, short startPc, short length) {
        this.name = name;
        this.descriptor = signature;
        this.startPc = startPc;
        this.length = length;
    }

    public String getName() {
        return this.name;
    }

    public String getDescriptor() {
        return this.descriptor;
    }

    public short getStartPc() {
        return this.startPc;
    }

    public short getLength() {
        return this.length;
    }

    private boolean isBetween(short min, short max, short test) {
        return test >= min && test <= max;
    }

    @Override
    public boolean equals(Object obj) {
        LocalVariableBuilder localVariableObj;

        if (!(obj instanceof LocalVariableBuilder))
            return false;
        else if (obj.hashCode() == hashCode())
            return true;
        localVariableObj = (LocalVariableBuilder) obj;
        return isBetween(this.startPc, (short) (this.startPc + this.length), localVariableObj.startPc)
        || isBetween(this.startPc, (short) (this.startPc + this.length), (short) (localVariableObj.startPc + localVariableObj.length));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name);
    }
}
