package fr.belinguier.java.info.constant;

import java.nio.ByteBuffer;

/**
 * @author Eliott Belinguier
 */
public class ConstantInvokeDynamicInfo extends ConstantPoolInfo {

    public short bootstrapMethodAttrIndex;
    public short nameAndTypeIndex;

    public ConstantInvokeDynamicInfo() {
        super(ConstantType.INVOKE_DYNAMIC);
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
