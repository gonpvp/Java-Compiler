package fr.belinguier.java.attribute;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @author Eliott Belinguier
 */
public class AttributeSourceDebugExtension extends Attribute {

    public byte[] bytes;

    public void setString(String str) {
        if (str == null) {
            this.bytes = null;
            return;
        }
        this.bytes = str.getBytes(StandardCharsets.UTF_8);
    }

    public String getString() {
        if (this.bytes == null)
            return null;
        return new String(this.bytes, StandardCharsets.UTF_8);
    }

    @Override
    public int sizeOfByteArray() {
        int byteLength = (this.bytes != null) ? this.bytes.length : 0;

        return super.sizeOfByteArray() + byteLength;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(sizeOfByteArray());

        byteBuffer.putShort(this.nameIndex);
        byteBuffer.putInt(getLength());
        if (this.bytes != null)
            byteBuffer.put(this.bytes);
        return byteBuffer.array();
    }
}
