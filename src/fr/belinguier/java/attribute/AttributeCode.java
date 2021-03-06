package fr.belinguier.java.attribute;

import fr.belinguier.java.attribute.exception.CodeException;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eliott Belinguier
 */
public class AttributeCode extends Attribute {

    public short maxStack;
    public short maxLocals;
    public byte[] codes;
    private final List<CodeException> exceptions;
    private final List<Attribute> attributes;

    public AttributeCode() {
        this.exceptions = new ArrayList<CodeException>();
        this.attributes = new ArrayList<Attribute>();
    }

    public List<CodeException> getExceptions() {
        return this.exceptions;
    }

    public List<Attribute> getAttributes() {
        return this.attributes;
    }

    @Override
    public int sizeOfByteArray() {
        int codeLength = (this.codes != null) ? this.codes.length : 0;
        int exceptionsLength = 0;
        int attributesLength = 0;

        for (CodeException exception : this.exceptions)
            exceptionsLength += exception.sizeOfByteArray();
        for (Attribute attributeInfo : this.attributes)
            attributesLength += attributeInfo.sizeOfByteArray();
        return super.sizeOfByteArray() + 8 + codeLength + 2 + exceptionsLength + 2 + attributesLength;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(sizeOfByteArray());
        int codeLength = (this.codes != null) ? this.codes.length : 0;
        byte[] temp;

        byteBuffer.putShort(this.nameIndex);
        byteBuffer.putInt(getLength());
        byteBuffer.putShort(this.maxStack);
        byteBuffer.putShort(this.maxLocals);
        byteBuffer.putInt(codeLength);
        if (this.codes != null)
            byteBuffer.put(this.codes);
        byteBuffer.putShort((short) this.exceptions.size());
        for (CodeException exception : this.exceptions) {
            temp = exception.toByte();
            if (temp != null)
                byteBuffer.put(temp);
        }
        byteBuffer.putShort((short) this.attributes.size());
        for (Attribute attributeInfo : this.attributes) {
            temp = attributeInfo.toByte();
            if (temp != null)
                byteBuffer.put(temp);
        }
        return byteBuffer.array();
    }
}
