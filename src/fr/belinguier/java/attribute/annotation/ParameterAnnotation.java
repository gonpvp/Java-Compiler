package fr.belinguier.java.attribute.annotation;

import fr.belinguier.java.compiler.Serializable;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eliott Belinguier
 */
public class ParameterAnnotation implements Serializable {

    private final List<Annotation> annotations;

    public ParameterAnnotation() {
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
        return 2 + annotationLength;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(sizeOfByteArray());
        byte[] temp;

        byteBuffer.putShort((short) this.annotations.size());
        for (Annotation annotation : this.annotations) {
            temp = annotation.toByte();
            if (temp != null)
                byteBuffer.put(temp);
        }
        return byteBuffer.array();
    }
}
