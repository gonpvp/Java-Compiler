package fr.belinguier.java.attribute.exception;

import fr.belinguier.java.compiler.Serializable;

import java.nio.ByteBuffer;

public class CodeException implements Serializable {

    public short startPc;
    public short endPc;
    public short handlerPc;
    public short catchType;

    @Override
    public int sizeOfByteArray() {
        return 8;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(sizeOfByteArray());

        byteBuffer.putShort(this.startPc);
        byteBuffer.putShort(this.endPc);
        byteBuffer.putShort(this.handlerPc);
        byteBuffer.putShort(this.catchType);
        return byteBuffer.array();
    }
}
