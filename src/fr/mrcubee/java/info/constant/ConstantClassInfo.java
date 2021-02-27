package fr.mrcubee.java.info.constant;

import java.nio.ByteBuffer;

/**
 * @author Eliott Belinguier
 */
public class ConstantClassInfo extends ConstantPoolInfo {

    private short nameIndex;

    public ConstantClassInfo() {
        super(ConstantType.CLASS);
    }

    public void setNameIndex(short nameIndex) {
        this.nameIndex = nameIndex;
    }

    public short getNameIndex() {
        return this.nameIndex;
    }

    @Override
    public int sizeOfByteArray() {
        return 3;
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
