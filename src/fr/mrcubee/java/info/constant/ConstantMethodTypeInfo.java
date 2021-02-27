package fr.mrcubee.java.info.constant;

import java.nio.ByteBuffer;

/**
 * @author Eliott Belinguier
 */
public class ConstantMethodTypeInfo extends ConstantPoolInfo {

    private short descriptorIndex;

    public ConstantMethodTypeInfo() {
        super(ConstantType.METHOD_TYPE);
    }

    public void setDescriptorIndex(short descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    public short getDescriptorIndex() {
        return this.descriptorIndex;
    }

    @Override
    public int sizeOfByteArray() {
        return 3;
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
