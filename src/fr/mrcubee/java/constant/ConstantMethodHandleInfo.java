package fr.mrcubee.java.constant;

import java.nio.ByteBuffer;

/**
 * @author Eliott Belinguier
 */
public class ConstantMethodHandleInfo extends ConstantPoolInfo {

    private byte referenceKind;
    private short referenceIndex;

    public ConstantMethodHandleInfo() {
        super(ConstantType.METHOD_HANDLE);
    }

    public void setReferenceKind(byte referenceKind) {
        this.referenceKind = referenceKind;
    }

    public byte getReferenceKind() {
        return this.referenceKind;
    }

    public void setReferenceIndex(short referenceIndex) {
        this.referenceIndex = referenceIndex;
    }

    public short getReferenceIndex() {
        return this.referenceIndex;
    }

    @Override
    public int sizeOfByteArray() {
        return 4;
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
