package fr.belinguier.java.attribute;

import java.nio.ByteBuffer;

/**
 * @author Eliott Belinguier
 */
public class AttributeAnnotationDefault extends Attribute {

    public short defaultValue;

    @Override
    public int sizeOfByteArray() {
        return super.sizeOfByteArray() + 2;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(sizeOfByteArray());

        byteBuffer.putShort(this.nameIndex);
        byteBuffer.putInt(getLength());
        byteBuffer.putShort(this.defaultValue);
        return byteBuffer.array();
    }
}
