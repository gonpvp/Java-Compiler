package fr.belinguier.java.constant;

import java.nio.ByteBuffer;

/**
 * @author Eliott Belinguier
 */
public class ConstantClass extends ConstantPool {

    public short nameIndex;

    public ConstantClass() {
        super(ConstantType.CLASS);
    }

    @Override
    public int sizeOfByteArray() {
        return super.sizeOfByteArray() + 2;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer;

        if (getType() == null)
            return null;
        byteBuffer = ByteBuffer.allocate(sizeOfByteArray());
        byteBuffer.put((byte) getType().getValue());
        byteBuffer.putShort(this.nameIndex);
        return byteBuffer.array();
    }
}
