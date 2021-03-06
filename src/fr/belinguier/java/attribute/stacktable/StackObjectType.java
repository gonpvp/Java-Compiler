package fr.belinguier.java.attribute.stacktable;

import java.nio.ByteBuffer;

/**
 * @author Eliott Belinguier
 */
public class StackObjectType implements VerificationType {

    private final short classIndex;

    protected StackObjectType(short classIndex) {
        this.classIndex = classIndex;
    }

    @Override
    public int sizeOfByteArray() {
        return 3;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(sizeOfByteArray());

        byteBuffer.put(StackType.OBJECT.getValue());
        byteBuffer.putShort(this.classIndex);
        return byteBuffer.array();
    }
}
