package fr.mrcubee.java.info.constant;

import java.nio.ByteBuffer;

/**
 * @author Eliott Belinguier
 */
public class ConstantIntegerInfo extends ConstantPoolInfo {

    private int value;

    public ConstantIntegerInfo() {
        super(ConstantType.INTEGER);
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public int sizeOfByteArray() {
        return 5;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer;

        if (getType() == null)
            return null;
        byteBuffer = ByteBuffer.allocate(sizeOfByteArray());
        byteBuffer.put((byte) getType().getValue());
        byteBuffer.putInt(this.value);
        return byteBuffer.array();
    }
}
