package fr.belinguier.java.attribute;

import fr.belinguier.java.compiler.Serializable;

/**
 * @author Eliott Belinguier
 */
public abstract class Attribute implements Serializable {

    public short nameIndex;

    public int getLength() {
        return sizeOfByteArray() - 6;
    }

    @Override
    public int sizeOfByteArray() {
        return 6;
    }
}
