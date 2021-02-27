package fr.belinguier.java.constant;

import java.nio.ByteBuffer;

/**
 * @author Eliott Belinguier
 */
public class ConstantFieldRef extends ConstantPool {

    public short classIndex;
    public short nameAndTypeIndex;

    public ConstantFieldRef() {
        super(ConstantType.FIELD_REF);
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
