package fr.belinguier.java.attribute;

import fr.belinguier.java.attribute.variabletable.LocalVariableType;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eliott Belinguier
 */
public class AttributeLocalVariableTypeTable extends Attribute {

    private final List<LocalVariableType> localVariableTypes;

    public AttributeLocalVariableTypeTable() {
        this.localVariableTypes = new ArrayList<LocalVariableType>();
    }

    public List<LocalVariableType> getLocalVariableTypes() {
        return this.localVariableTypes;
    }

    @Override
    public int sizeOfByteArray() {
        int lineNumberLength = 0;

        for (LocalVariableType localVariableType : this.localVariableTypes)
            lineNumberLength += localVariableType.sizeOfByteArray();
        return super.sizeOfByteArray() + 2 + lineNumberLength;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(sizeOfByteArray());
        byte[] temp;

        byteBuffer.putShort(this.nameIndex);
        byteBuffer.putInt(getLength());
        byteBuffer.putShort((short) this.localVariableTypes.size());
        for (LocalVariableType localVariableType : this.localVariableTypes) {
            temp = localVariableType.toByte();
            if (temp != null)
                byteBuffer.put(temp);
        }
        return byteBuffer.array();
    }
}
