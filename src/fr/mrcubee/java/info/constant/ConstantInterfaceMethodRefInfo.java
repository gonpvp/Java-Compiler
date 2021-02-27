package fr.mrcubee.java.info.constant;

import java.nio.ByteBuffer;

/**
 * @author Eliott Belinguier
 */
public class ConstantInterfaceMethodRefInfo extends ConstantPoolInfo {

    private short classIndex;
    private short nameAndTypeIndex;

    public ConstantInterfaceMethodRefInfo() {
        super(ConstantType.INTERFACE_METHOD_REF);
    }

    public void setClassIndex(short classIndex) {
        this.classIndex = classIndex;
    }

    public short getClassIndex() {
        return this.classIndex;
    }

    public void setNameAndTypeIndex(short nameAndTypeIndex) {
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public short getNameAndTypeIndex() {
        return this.nameAndTypeIndex;
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
        byteBuffer.putShort(this.classIndex);
        byteBuffer.putShort(this.nameAndTypeIndex);
        return byteBuffer.array();
    }
}