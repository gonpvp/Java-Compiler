package fr.belinguier.java.attribute;

import fr.belinguier.java.attribute.linetable.LineNumber;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eliott Belinguier
 */
public class AttributeLineNumberTable extends Attribute {

    private final List<LineNumber> lineNumbers;

    public AttributeLineNumberTable() {
        this.lineNumbers = new ArrayList<LineNumber>();
    }

    public List<LineNumber> getLineNumbers() {
        return this.lineNumbers;
    }

    @Override
    public int sizeOfByteArray() {
        int lineNumberLength = 0;

        for (LineNumber lineNumber : this.lineNumbers)
            lineNumberLength += lineNumber.sizeOfByteArray();
        return super.sizeOfByteArray() + 2 + lineNumberLength;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(sizeOfByteArray());
        byte[] temp;

        byteBuffer.putShort(this.nameIndex);
        byteBuffer.putInt(getLength());
        byteBuffer.putShort((short) this.lineNumbers.size());
        for (LineNumber lineNumber : this.lineNumbers) {
            temp = lineNumber.toByte();
            if (temp != null)
                byteBuffer.put(temp);
        }
        return byteBuffer.array();
    }
}
