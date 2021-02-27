package fr.mrcubee.java.constant;

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

}
