package fr.belinguier.java.constant;

import java.nio.ByteBuffer;
import java.util.Objects;

/**
 * @author Eliott Belinguier
 */
public final class ConstantFloat extends ConstantPool {

    public float value;

    public ConstantFloat() {
        super(ConstantType.FLOAT);
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
        byteBuffer.putFloat(this.value);
        return byteBuffer.array();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), this.value);
    }
}
