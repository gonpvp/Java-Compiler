package fr.belinguier.java.constant;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author Eliott Belinguier
 */
public final class ConstantUtf8 extends ConstantPool {

    public byte[] bytes;

    public ConstantUtf8() {
        super(ConstantType.UTF_8);
    }

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

    public int getLength() {
        return (this.bytes != null) ? this.bytes.length : 0;
    }

    @Override
    public int sizeOfByteArray() {
        return super.sizeOfByteArray() + 2 + getLength();
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer;

        if (getType() == null)
            return null;
        byteBuffer = ByteBuffer.allocate(sizeOfByteArray());
        byteBuffer.put((byte) getType().getValue());
        byteBuffer.putShort((short) getLength());
        if (this.bytes != null)
            byteBuffer.put(this.bytes, 0, this.bytes.length);
        return byteBuffer.array();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), this.bytes);
    }
}
