package fr.belinguier.java.info.constant;

import java.nio.ByteBuffer;

/**
 * @author Eliott Belinguier
 */
public class ConstantMethodTypeInfo extends ConstantPoolInfo {

    public short descriptorIndex;

    public ConstantMethodTypeInfo() {
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
}
