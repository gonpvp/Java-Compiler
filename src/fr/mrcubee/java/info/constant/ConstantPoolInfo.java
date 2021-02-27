package fr.mrcubee.java.info.constant;

import fr.mrcubee.java.compiler.Serializable;

/**
 * @author Eliott Belinguier
 */
public abstract class ConstantPoolInfo implements Serializable {

    private final ConstantType type;

    public ConstantPoolInfo(ConstantType type) {
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
