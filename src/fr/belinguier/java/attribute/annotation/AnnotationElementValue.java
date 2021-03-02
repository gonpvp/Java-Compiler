package fr.belinguier.java.attribute.annotation;

import fr.belinguier.java.compiler.Serializable;

import java.nio.ByteBuffer;

public class AnnotationElementValue implements Serializable {

    public short nameIndex;

    @Override
    public int sizeOfByteArray() {
        return 2;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(sizeOfByteArray());

        byteBuffer.putShort(this.nameIndex);
        return byteBuffer.array();
    }
}
