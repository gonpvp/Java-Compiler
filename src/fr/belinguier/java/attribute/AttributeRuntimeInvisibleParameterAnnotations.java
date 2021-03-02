package fr.belinguier.java.attribute;

import fr.belinguier.java.attribute.annotation.ParameterAnnotation;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class AttributeRuntimeInvisibleParameterAnnotations extends Attribute {

    private final List<ParameterAnnotation> parameterAnnotations;

    public AttributeRuntimeInvisibleParameterAnnotations() {
        this.parameterAnnotations = new ArrayList<ParameterAnnotation>();
    }

    public List<ParameterAnnotation> getParameterAnnotations() {
        return this.parameterAnnotations;
    }

    @Override
    public int sizeOfByteArray() {
        int annotationLength = 0;

        for (ParameterAnnotation parameterAnnotation : this.parameterAnnotations)
            annotationLength += parameterAnnotation.sizeOfByteArray();
        return super.sizeOfByteArray() + 2 + annotationLength;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(sizeOfByteArray());
        byte[] temp;

        byteBuffer.putShort(this.nameIndex);
        byteBuffer.putInt(getLength());
        byteBuffer.putShort((short) this.parameterAnnotations.size());
        for (ParameterAnnotation parameterAnnotation : this.parameterAnnotations) {
            temp = parameterAnnotation.toByte();
            if (temp != null)
                byteBuffer.put(temp);
        }
        return byteBuffer.array();
    }
}
