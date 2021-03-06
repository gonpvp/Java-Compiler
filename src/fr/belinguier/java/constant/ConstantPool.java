package fr.belinguier.java.constant;

import fr.belinguier.java.compiler.Serializable;

import java.util.Objects;

/**
 * @author Eliott Belinguier
 */
public abstract class ConstantPool implements Serializable {

    private final ConstantType type;

    public ConstantPool(ConstantType type) {
        this.type = type;
    }

    public ConstantType getType() {
        return this.type;
    }

    @Override
    public int sizeOfByteArray() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ConstantPool))
            return false;
        return obj.hashCode() == hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
