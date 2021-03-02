package fr.belinguier.java.attribute;

import fr.belinguier.java.attribute.bootstrap.Method;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eliott Belinguier
 */
public class AttributeBootstrapMethods extends Attribute {

    private final List<Method> methods;

    public AttributeBootstrapMethods() {
        this.methods = new ArrayList<Method>();
    }

    public List<Method> getMethods() {
        return this.methods;
    }

    @Override
    public int sizeOfByteArray() {
        int methodLength = 0;

        for (Method method : this.methods)
            methodLength += method.sizeOfByteArray();
        return super.sizeOfByteArray() + 2  + methodLength;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(sizeOfByteArray());
        byte[] temp;

        byteBuffer.putShort(this.nameIndex);
        byteBuffer.putInt(getLength());
        byteBuffer.putShort((short) this.methods.size());
        for (Method method : this.methods) {
            temp = method.toByte();
            if (temp != null)
                byteBuffer.put(temp);
        }
        return byteBuffer.array();
    }
}
