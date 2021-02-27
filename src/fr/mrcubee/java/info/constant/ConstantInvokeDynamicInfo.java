package fr.mrcubee.java.info.constant;

import java.nio.ByteBuffer;

/**
 * @author Eliott Belinguier
 */
public class ConstantInvokeDynamicInfo extends ConstantPoolInfo {

    private short bootstrapMethodAttrIndex;
    private short nameAndTypeIndex;

    public ConstantInvokeDynamicInfo() {
        super(ConstantType.INVOKE_DYNAMIC);
    }

    public void setBootstrapMethodAttrIndex(short bootstrapMethodAttrIndex) {
        this.bootstrapMethodAttrIndex = bootstrapMethodAttrIndex;
    }

    public short getBootstrapMethodAttrIndex() {
        return this.bootstrapMethodAttrIndex;
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
        byteBuffer.putShort(this.bootstrapMethodAttrIndex);
        byteBuffer.putShort(this.nameAndTypeIndex);
        return byteBuffer.array();
    }
}
