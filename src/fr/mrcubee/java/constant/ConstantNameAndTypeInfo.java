package fr.mrcubee.java.constant;

import java.nio.ByteBuffer;

/**
 * @author Eliott Belinguier
 */
public class ConstantNameAndTypeInfo extends ConstantPoolInfo {

    private short nameIndex;
    private short descriptorIndex;

    public ConstantNameAndTypeInfo() {
        super(ConstantType.NAME_AND_TYPE);
    }

    public void setNameIndex(short nameIndex) {
        this.nameIndex = nameIndex;
    }

    public short getNameIndex() {
        return this.nameIndex;
    }

    public void setDescriptorIndex(short descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    public short getDescriptorIndex() {
        return this.descriptorIndex;
    }

    @Override
    public int sizeOfByteArray() {
        return 5;
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
