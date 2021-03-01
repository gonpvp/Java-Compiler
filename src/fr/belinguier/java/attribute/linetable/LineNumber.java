package fr.belinguier.java.attribute.linetable;

import fr.belinguier.java.compiler.Serializable;

import java.nio.ByteBuffer;

public class LineNumber implements Serializable {

    public short startPc;
    public short lineNumber;

    @Override
    public int sizeOfByteArray() {
        return 4;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(sizeOfByteArray());

        byteBuffer.putShort(this.startPc);
        byteBuffer.putShort(this.lineNumber);
        return byteBuffer.array();
    }
}
