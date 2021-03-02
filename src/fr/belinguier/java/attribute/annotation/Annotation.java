package fr.belinguier.java.attribute.annotation;

import fr.belinguier.java.compiler.Serializable;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eliott Belinguier
 */
public class Annotation implements Serializable {

    public short typeIndex;
    private final List<AnnotationElementValue> elementValues;

    public Annotation() {
        this.elementValues = new ArrayList<AnnotationElementValue>();
    }

    public List<AnnotationElementValue> getElementValues() {
        return this.elementValues;
    }

    @Override
    public int sizeOfByteArray() {
        int elementValueLength = 0;

        for (AnnotationElementValue annotationField : this.elementValues)
            elementValueLength += annotationField.sizeOfByteArray();
        return 4 + elementValueLength;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(sizeOfByteArray());
        byte[] temp;

        byteBuffer.putShort(this.typeIndex);
        byteBuffer.putShort((short) this.elementValues.size());
        for (AnnotationElementValue elementValue : this.elementValues) {
            temp = elementValue.toByte();
            if (temp != null)
                byteBuffer.put(temp);
        }
        return byteBuffer.array();
    }
}
