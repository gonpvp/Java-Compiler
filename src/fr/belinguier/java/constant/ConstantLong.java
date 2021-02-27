package fr.belinguier.java.constant;

import java.nio.ByteBuffer;

/**
 * @author Eliott Belinguier
 */
public class ConstantLong extends ConstantPool {

    public int highValue;
    public int lowValue;

    public ConstantLong() {
        super(ConstantType.LONG);
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
        byteBuffer.putInt(this.highValue);
        byteBuffer.putInt(this.lowValue);
        return byteBuffer.array();
    }
}
