package fr.belinguier.java.attribute;

import java.nio.ByteBuffer;

/**
 * @author Eliott Belinguier
 */
public class AttributeEnclosingMethod extends Attribute {

    public short classIndex;
    public short methodIndex;

    @Override
    public int sizeOfByteArray() {
        return super.sizeOfByteArray() + 4;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(sizeOfByteArray());

        byteBuffer.putShort(this.nameIndex);
        byteBuffer.putInt(getLength());
        byteBuffer.putShort(this.classIndex);
        byteBuffer.putShort(this.methodIndex);
        return byteBuffer.array();
    }
}
