package fr.belinguier.java.attribute.variabletable;

import fr.belinguier.java.compiler.Serializable;

import java.nio.ByteBuffer;

public class LocalVariable implements Serializable {

    public short startPc;
    public short length;
    public short nameIndex;
    public short descriptorIndex;
    public short index;

    @Override
    public int sizeOfByteArray() {
        return 10;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(sizeOfByteArray());

        byteBuffer.putShort(this.startPc);
        byteBuffer.putShort(this.length);
        byteBuffer.putShort(this.nameIndex);
        byteBuffer.putShort(this.descriptorIndex);
        byteBuffer.putShort(this.index);
        return byteBuffer.array();
    }
}
