package fr.mrcubee.java.info.constant;

import java.nio.ByteBuffer;

/**
 * @author Eliott Belinguier
 */
public class ConstantLongInfo extends ConstantPoolInfo {

    public int highValue;
    public int lowValue;

    public ConstantLongInfo() {
        super(ConstantType.LONG);
    }

    @Override
    public int sizeOfByteArray() {
        return super.sizeOfByteArray() + 8;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer;

        if (getType() == null)
            return null;
        byteBuffer = ByteBuffer.allocate(sizeOfByteArray());
        byteBuffer.put((byte) getType().getValue());
        byteBuffer.putInt(this.highValue);
        byteBuffer.putInt(this.lowValue);
        return byteBuffer.array();
    }
}
