package fr.belinguier.java.constant;

import java.nio.ByteBuffer;

/**
 * @author Eliott Belinguier
 */
public class ConstantMethodHandle extends ConstantPool {

    public byte referenceKind;
    public short referenceIndex;

    public ConstantMethodHandle() {
        super(ConstantType.METHOD_HANDLE);
    }

    @Override
    public int sizeOfByteArray() {
        return super.sizeOfByteArray() + 3;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer;

        if (getType() == null)
            return null;
        byteBuffer = ByteBuffer.allocate(sizeOfByteArray());
        byteBuffer.put((byte) getType().getValue());
        byteBuffer.put(this.referenceKind);
        byteBuffer.putShort(this.referenceIndex);
        return byteBuffer.array();
    }
}
