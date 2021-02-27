package fr.mrcubee.java.info.constant;

import java.nio.ByteBuffer;

public class ConstantDoubleInfo extends ConstantPoolInfo {

    private int highValue;
    private int lowValue;

    public ConstantDoubleInfo() {
        super(ConstantType.DOUBLE);
    }

    public void setHighValue(int highValue) {
        this.highValue = highValue;
    }

    public int getHighValue() {
        return this.highValue;
    }

    public void setLowValue(int lowValue) {
        this.lowValue = lowValue;
    }

    public int getLowValue() {
        return this.lowValue;
    }

    @Override
    public int sizeOfByteArray() {
        return 9;
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
