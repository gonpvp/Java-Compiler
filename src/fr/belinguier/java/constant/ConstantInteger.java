package fr.belinguier.java.constant;

import java.nio.ByteBuffer;

/**
 * @author Eliott Belinguier
 */
public class ConstantInteger extends ConstantPool {

    public int value;

    public ConstantInteger() {
        super(ConstantType.INTEGER);
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
        byteBuffer.putInt(this.value);
        return byteBuffer.array();
    }
}
