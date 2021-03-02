package fr.belinguier.java.constant;

import java.nio.ByteBuffer;
import java.util.Objects;

/**
 * @author Eliott Belinguier
 */
public final class ConstantNameAndType extends ConstantPool {

    public short nameIndex;
    public short descriptorIndex;

    public ConstantNameAndType() {
        super(ConstantType.NAME_AND_TYPE);
    }

    @Override
    public int sizeOfByteArray() {
        return super.sizeOfByteArray() + 4;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer;

        if (getType() == null)
            return null;
        byteBuffer = ByteBuffer.allocate(sizeOfByteArray());
        byteBuffer.put((byte) getType().getValue());
        byteBuffer.putShort(this.nameIndex);
        byteBuffer.putShort(this.descriptorIndex);
        return byteBuffer.array();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), this.nameIndex, this.descriptorIndex);
    }
}
