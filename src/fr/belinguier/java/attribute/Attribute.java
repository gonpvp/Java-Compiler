package fr.belinguier.java.attribute;

import fr.belinguier.java.compiler.Serializable;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hashCode(this.nameIndex);
    }
}
