package fr.belinguier.java.info.constant;

import java.nio.ByteBuffer;

/**
 * @author Eliott Belinguier
 */
public class ConstantNameAndTypeInfo extends ConstantPoolInfo {

    public short nameIndex;
    public short descriptorIndex;

    public ConstantNameAndTypeInfo() {
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
}
