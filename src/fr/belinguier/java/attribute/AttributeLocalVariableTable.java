package fr.belinguier.java.attribute;

import fr.belinguier.java.attribute.variabletable.LocalVariable;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eliott Belinguier
 */
public class AttributeLocalVariableTable extends Attribute {

    private final List<LocalVariable> localVariables;

    public AttributeLocalVariableTable() {
        this.localVariables = new ArrayList<LocalVariable>();
    }

    public List<LocalVariable> getLocalVariables() {
        return this.localVariables;
    }

    @Override
    public int sizeOfByteArray() {
        int lineNumberLength = 0;

        for (LocalVariable localVariable : this.localVariables)
            lineNumberLength += localVariable.sizeOfByteArray();
        return super.sizeOfByteArray() + 2 + lineNumberLength;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(sizeOfByteArray());
        byte[] temp;

        byteBuffer.putShort(this.nameIndex);
        byteBuffer.putInt(getLength());
        byteBuffer.putShort((short) this.localVariables.size());
        for (LocalVariable localVariable : this.localVariables) {
            temp = localVariable.toByte();
            if (temp != null)
                byteBuffer.put(temp);
        }
        return byteBuffer.array();
    }
}
