package fr.belinguier.java.constant;

import java.nio.ByteBuffer;
import java.util.Objects;

/**
 * @author Eliott Belinguier
 */
public final class ConstantDouble extends ConstantPool {

    public double value;

    public ConstantDouble() {
        super(ConstantType.DOUBLE);
    }

    @Override
    public int sizeOfByteArray() {
        return super.sizeOfByteArray() + 8;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer;

        if (getType() == null)
            return null;
        byteBuffer = ByteBuffer.allocate(sizeOfByteArray());
        byteBuffer.put((byte) getType().getValue());
        byteBuffer.putDouble(this.value);
        return byteBuffer.array();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), this.value);
    }
}
