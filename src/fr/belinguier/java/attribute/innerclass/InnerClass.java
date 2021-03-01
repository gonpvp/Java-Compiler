package fr.belinguier.java.attribute.innerclass;

import fr.belinguier.java.access.ClassAccessFlag;
import fr.belinguier.java.access.InnerClassAccessFlag;
import fr.belinguier.java.compiler.Serializable;

import java.nio.ByteBuffer;

/**
 * @author Eliott Belinguier
 */
public class InnerClass implements Serializable {

    public short innerClassIndex;
    public short outerClassIndex;
    public short nameIndex;
    public short accessFlags;

    public void setAccessFlags(InnerClassAccessFlag... accessFlags) {
        this.accessFlags = 0;
        if (accessFlags == null || accessFlags.length < 1)
            return;
        for (InnerClassAccessFlag accessFlag : accessFlags)
            this.accessFlags |= accessFlag.getValue();
    }

    public ClassAccessFlag[] getAccessFlags() {
        return ClassAccessFlag.getFromValue(this.accessFlags);
    }

    @Override
    public int sizeOfByteArray() {
        return 8;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(sizeOfByteArray());

        byteBuffer.putShort(this.innerClassIndex);
        byteBuffer.putShort(this.outerClassIndex);
        byteBuffer.putShort(this.nameIndex);
        byteBuffer.putShort(this.accessFlags);
        return byteBuffer.array();
    }
}
