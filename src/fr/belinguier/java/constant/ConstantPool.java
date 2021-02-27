package fr.belinguier.java.constant;

import fr.belinguier.java.compiler.Serializable;

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
}
