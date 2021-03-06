package fr.belinguier.java.attribute;

import com.sun.org.apache.bcel.internal.classfile.StackMap;
import fr.belinguier.java.attribute.stacktable.StackMapFrame;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eliott Belinguier
 */
public class AttributeStackMapTable extends Attribute {

    private final List<StackMapFrame> frames;

    public AttributeStackMapTable() {
        this.frames = new ArrayList<StackMapFrame>();
    }

    public List<StackMapFrame> getFrames() {
        return this.frames;
    }

    @Override
    public int sizeOfByteArray() {
        return super.sizeOfByteArray();
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(sizeOfByteArray());

        byteBuffer.putShort(this.nameIndex);
        byteBuffer.putInt(getLength());
        return byteBuffer.array();
    }
}
