package fr.belinguier.java.attribute;

import java.nio.ByteBuffer;

/**
 * @author Eliott Belinguier
 */
public class AttributeSynthetic extends Attribute {

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(sizeOfByteArray());

        byteBuffer.putShort(this.nameIndex);
        byteBuffer.putInt(getLength());
        return byteBuffer.array();
    }
}
