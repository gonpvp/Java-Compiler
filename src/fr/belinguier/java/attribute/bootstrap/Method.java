package fr.belinguier.java.attribute.bootstrap;

import fr.belinguier.java.compiler.Serializable;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eliott Belinguier
 */
public class Method implements Serializable {

    public short methodRefIndex;
    private final List<Short> argumentIndexList;

    public Method() {
        this.argumentIndexList = new ArrayList<Short>();
    }

    public List<Short> getArgumentIndexList() {
        return this.argumentIndexList;
    }

    @Override
    public int sizeOfByteArray() {
        int argumentIndexLength = this.argumentIndexList.size() * 2;

        return 2 + argumentIndexLength;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(sizeOfByteArray());

        byteBuffer.putShort(this.methodRefIndex);
        byteBuffer.putShort((short) this.argumentIndexList.size());
        for (Short index : this.argumentIndexList)
            byteBuffer.putShort(index);
        return byteBuffer.array();
    }
}
