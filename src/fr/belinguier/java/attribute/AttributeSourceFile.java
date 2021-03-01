package fr.belinguier.java.attribute;

import java.nio.ByteBuffer;

/**
 * @author Eliott Belinguier
 */
public class AttributeSourceFile extends Attribute {

    public short sourceFileIndex;

    @Override
    public int sizeOfByteArray() {
        return super.sizeOfByteArray() + 2;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(sizeOfByteArray());

        byteBuffer.putShort(this.nameIndex);
        byteBuffer.putInt(getLength());
        byteBuffer.putShort(this.sourceFileIndex);
        return byteBuffer.array();
    }
}
