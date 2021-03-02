package fr.belinguier.java.constant;

import java.nio.ByteBuffer;
import java.util.Objects;

/**
 * @author Eliott Belinguier
 */
public final class ConstantMethodType extends ConstantPool {

    public short descriptorIndex;

    public ConstantMethodType() {
        super(ConstantType.METHOD_TYPE);
    }

    @Override
    public int sizeOfByteArray() {
        return super.sizeOfByteArray() + 2;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer;

        if (getType() == null)
            return null;
        byteBuffer = ByteBuffer.allocate(sizeOfByteArray());
        byteBuffer.put((byte) getType().getValue());
        byteBuffer.putShort(this.descriptorIndex);
        return byteBuffer.array();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), this.descriptorIndex);
    }
}
