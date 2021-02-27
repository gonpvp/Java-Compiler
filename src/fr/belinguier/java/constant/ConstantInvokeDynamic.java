package fr.belinguier.java.constant;

import java.nio.ByteBuffer;

/**
 * @author Eliott Belinguier
 */
public class ConstantInvokeDynamic extends ConstantPool {

    public short bootstrapMethodAttrIndex;
    public short nameAndTypeIndex;

    public ConstantInvokeDynamic() {
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
