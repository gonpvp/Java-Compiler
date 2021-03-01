package fr.belinguier.java;

import fr.belinguier.java.access.ClassAccessFlag;
import fr.belinguier.java.attribute.Attribute;
import fr.belinguier.java.compiler.Serializable;
import fr.belinguier.java.constant.ConstantPool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eliott Belinguier
 */
public class ClassFile implements Serializable {

    public int magicNumber;
    public short minorVersion;
    public short majorVersion;
    private final List<ConstantPool> constantPools;
    public short accessFlags;
    public short classIndex;
    public short superClassNameIndex;
    private final List<Short> interfaces;
    private final List<Field> fields;
    private final List<Method> methods;
    private final List<Attribute> attributes;

    public ClassFile() {
        this.magicNumber = 0xCAFEBABE;
        this.minorVersion = 0;
        this.majorVersion = 52;
        this.accessFlags = ClassAccessFlag.PUBLIC.getValue();
        this.constantPools = new ArrayList<ConstantPool>();
        this.interfaces = new ArrayList<Short>();
        this.fields = new ArrayList<Field>();
        this.methods = new ArrayList<Method>();
        this.attributes = new ArrayList<Attribute>();
    }

    public List<ConstantPool> getConstantPools() {
        return this.constantPools;
    }

    public void setAccessFlags(ClassAccessFlag... accessFlags) {
        this.accessFlags = 0;
        if (accessFlags == null || accessFlags.length < 1)
            return;
        for (ClassAccessFlag accessFlag : accessFlags)
            this.accessFlags |= accessFlag.getValue();
    }

    public ClassAccessFlag[] getAccessFlags() {
        return ClassAccessFlag.getFromValue(this.accessFlags);
    }

    public List<Short> getInterfaces() {
        return this.interfaces;
    }

    public List<Field> getFields() {
        return this.fields;
    }

    public List<Method> getMethods() {
        return this.methods;
    }

    public List<Attribute> getAttributes() {
        return this.attributes;
    }

    @Override
    public int sizeOfByteArray() {
        int constantLength = 0;
        int interfaceLength = 0;
        int fieldLength = 0;
        int methodLength = 0;
        int attributeLength = 0;

        for (ConstantPool constantPoolInfo : this.constantPools)
            constantLength += constantPoolInfo.sizeOfByteArray();
        interfaceLength = this.interfaces.size() * 2;
        for (Field field : this.fields)
            fieldLength += field.sizeOfByteArray();
        for (Method method : this.methods)
            methodLength += method.sizeOfByteArray();
        for (Attribute attribute : this.attributes)
            attributeLength += attribute.sizeOfByteArray();
        System.out.println("ConstantLength: " + constantLength);
        System.out.println("InterfaceLength: " + interfaceLength);
        System.out.println("FieldLength: " + fieldLength);
        System.out.println("MethodLength: " + methodLength);
        System.out.println("AttributeLength: " + attributeLength);
        return 26 + constantLength + interfaceLength + fieldLength + methodLength + attributeLength;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(sizeOfByteArray());
        byte[] temp;

        byteBuffer.putInt(magicNumber);
        byteBuffer.putShort(minorVersion);
        byteBuffer.putShort(majorVersion);
        byteBuffer.putShort((short) (this.constantPools.size() + 1));
        for (ConstantPool constantPool : this.constantPools) {
            temp = constantPool.toByte();
            if (temp != null)
                byteBuffer.put(temp);
        }
        byteBuffer.putShort(this.accessFlags);
        byteBuffer.putShort(this.classIndex);
        byteBuffer.putShort(this.superClassNameIndex);
        byteBuffer.putShort((short) this.interfaces.size());
        for (Short interfaceIndex : this.interfaces) {
            if (interfaceIndex != null)
                byteBuffer.putShort(interfaceIndex);
        }
        byteBuffer.putShort((short) this.fields.size());
        for (Field field : this.fields) {
            temp = field.toByte();
            if (temp != null)
                byteBuffer.put(temp);
        }
        byteBuffer.putShort((short) this.methods.size());
        for (Method method : this.methods) {
            temp = method.toByte();
            if (temp != null)
                byteBuffer.put(temp);
        }
        byteBuffer.putShort((short) this.attributes.size());
        for (Attribute attribute : this.attributes) {
            temp = attribute.toByte();
            if (temp != null)
                byteBuffer.put(temp);
        }
        return byteBuffer.array();
    }

    public boolean write(File file) {
        FileOutputStream fileOutputStream;
        byte[] bytes;

        if (file == null || (file.exists() && !file.delete()))
            return false;
        bytes = toByte();
        if (bytes == null || bytes.length < 1)
            return false;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes);
            fileOutputStream.close();
            return true;
        } catch (IOException ignored) {};
        return false;
    }
}
