package fr.belinguier.java;

import fr.belinguier.java.access.FieldAccessFlag;
import fr.belinguier.java.attribute.Attribute;
import fr.belinguier.java.compiler.Serializable;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eliott Belinguier
 */
public class Field implements Serializable {

    public short accessFlags;
    public short nameIndex;
    public short descriptorIndex;
    private final List<Attribute> attributes;

    public Field() {
        this.attributes = new ArrayList<Attribute>();
    }

    public void setAccessFlags(FieldAccessFlag... accessFlags) {
        this.accessFlags = 0;
        if (accessFlags == null || accessFlags.length < 1)
            return;
        for (FieldAccessFlag accessFlag : accessFlags)
            this.accessFlags |= accessFlag.getValue();
    }

    public FieldAccessFlag[] getAccessFlags() {
        return FieldAccessFlag.getFromValue(this.accessFlags);
    }

    public List<Attribute> getAttributes() {
        return this.attributes;
    }

    @Override
    public int sizeOfByteArray() {
        int attributeLength = 0;

        for (Attribute attribute : this.attributes)
            attributeLength += attribute.sizeOfByteArray();
        return 8 + attributeLength;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(sizeOfByteArray());
        byte[] temp;

        byteBuffer.putShort(this.accessFlags);
        byteBuffer.putShort(this.nameIndex);
        byteBuffer.putShort(this.descriptorIndex);
        byteBuffer.putShort((short) this.attributes.size());
        for (Attribute attribute : this.attributes) {
            temp = attribute.toByte();
            if (temp != null)
                byteBuffer.put(temp);
        }
        return byteBuffer.array();
    }
}
