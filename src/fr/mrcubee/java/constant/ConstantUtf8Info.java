package fr.mrcubee.java.constant;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author Eliott Belinguier
 */
public class ConstantUtf8Info extends ConstantPoolInfo {

    private short length;
    private byte[] bytes;

    public ConstantUtf8Info() {
        super(ConstantType.UTF_8);
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
        if (bytes != null)
            this.length = (short) bytes.length;
        else
            this.length = 0;
    }

    public byte[] getBytes() {
        if (this.bytes == null)
            return null;
        return Arrays.copyOf(this.bytes, this.bytes.length);
    }

    public void setString(String str) {
        if (str == null) {
            this.bytes = null;
            return;
        }
        this.bytes = str.getBytes(StandardCharsets.UTF_8);
        if (this.bytes != null)
            this.length = (short) this.bytes.length;
        else
            this.length = 0;
    }

    public String getString() {
        return new String(this.bytes, StandardCharsets.UTF_8);
    }

    @Override
    public int sizeOfByteArray() {
        return 3 + this.length;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer;

        if (getType() == null)
            return null;
        byteBuffer = ByteBuffer.allocate(sizeOfByteArray());
        byteBuffer.put((byte) getType().getValue());
        byteBuffer.putShort(this.length);
        if (this.bytes != null)
            byteBuffer.put(this.bytes, 0, this.bytes.length);
        return byteBuffer.array();
    }
}
