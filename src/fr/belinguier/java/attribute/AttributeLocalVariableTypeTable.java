package fr.belinguier.java.attribute;

import fr.belinguier.java.attribute.variabletable.LocalVariableType;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eliott Belinguier
 */
public class AttributeLocalVariableTypeTable extends Attribute {

    private final List<LocalVariableType> localVariableTypeList;

    public AttributeLocalVariableTypeTable() {
        this.localVariableTypeList = new ArrayList<LocalVariableType>();
    }

    public List<LocalVariableType> getLocalVariableTypeList() {
        return this.localVariableTypeList;
    }

    @Override
    public int sizeOfByteArray() {
        int lineNumberLength = 0;

        for (LocalVariableType localVariableType : this.localVariableTypeList)
            lineNumberLength += localVariableType.sizeOfByteArray();
        return super.sizeOfByteArray() + 2 + lineNumberLength;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(sizeOfByteArray());
        byte[] temp;

        byteBuffer.putShort(this.nameIndex);
        byteBuffer.putInt(getLength());
        byteBuffer.putShort((short) this.localVariableTypeList.size());
        for (LocalVariableType localVariableType : this.localVariableTypeList) {
            temp = localVariableType.toByte();
            if (temp != null)
                byteBuffer.put(temp);
        }
        return byteBuffer.array();
    }
}
