package fr.mrcubee.java.info.constant;

import java.nio.ByteBuffer;

/**
 * @author Eliott Belinguier
 */
public class ConstantStringInfo extends ConstantPoolInfo {

    private short stringIndex;

    public ConstantStringInfo() {
        super(ConstantType.STRING);
    }

    public void setStringIndex(short stringIndex) {
        this.stringIndex = stringIndex;
    }

    public short getStringIndex() {
        return this.stringIndex;
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
        byteBuffer.putShort(this.stringIndex);
        return byteBuffer.array();
    }
}
