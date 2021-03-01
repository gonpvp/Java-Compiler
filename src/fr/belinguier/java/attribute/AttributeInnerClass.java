package fr.belinguier.java.attribute;

import fr.belinguier.java.attribute.innerclass.InnerClass;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eliott Belinguier
 */
public class AttributeInnerClass extends Attribute {

    private final List<InnerClass> innerClasses;

    public AttributeInnerClass() {
        this.innerClasses = new ArrayList<InnerClass>();
    }

    public List<InnerClass> getInnerClasses() {
        return this.innerClasses;
    }

    @Override
    public int sizeOfByteArray() {
        int innerClassLength = 0;

        for (InnerClass innerClass : this.innerClasses)
            innerClassLength += innerClass.sizeOfByteArray();
        return super.sizeOfByteArray() + 2 + innerClassLength;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(sizeOfByteArray());
        byte[] temp;

        byteBuffer.putShort(this.nameIndex);
        byteBuffer.putInt(getLength());
        byteBuffer.putShort((short) this.innerClasses.size());
        for (InnerClass innerClass : this.innerClasses) {
            temp = innerClass.toByte();
            if (temp != null)
                byteBuffer.put(temp);
        }
        return byteBuffer.array();
    }
}
