package fr.belinguier.java.attribute;

import fr.belinguier.java.attribute.annotation.Annotation;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class AttributeRuntimeVisibleAnnotations extends Attribute {

    private final List<Annotation> annotations;

    public AttributeRuntimeVisibleAnnotations() {
        this.annotations = new ArrayList<Annotation>();
    }

    public List<Annotation> getAnnotations() {
        return this.annotations;
    }

    @Override
    public int sizeOfByteArray() {
        int annotationLength = 0;

        for (Annotation annotation : this.annotations)
            annotationLength += annotation.sizeOfByteArray();
        return super.sizeOfByteArray() + 2 + annotationLength;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(sizeOfByteArray());
        byte[] temp;

        byteBuffer.putShort(this.nameIndex);
        byteBuffer.putInt(getLength());
        byteBuffer.putShort((short) this.annotations.size());
        for (Annotation annotation : this.annotations) {
            temp = annotation.toByte();
            if (temp != null)
                byteBuffer.put(temp);
        }
        return byteBuffer.array();
    }
}
