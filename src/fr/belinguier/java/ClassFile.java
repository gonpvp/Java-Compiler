package fr.belinguier.java;

import fr.belinguier.java.access.ClassAccessFlag;
import fr.belinguier.java.attribute.Attribute;
import fr.belinguier.java.compiler.Serializable;
import fr.belinguier.java.constant.ConstantPool;
import fr.belinguier.java.constant.ConstantUtf8;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.*;

/**
 * @author Eliott Belinguier
 */
public final class ClassFile implements Serializable {

    private static final String COMPILER_AUTHOR = "Compiled by Eliott Belinguier Compiler";

    public int magicNumber;
    public short minorVersion;
    public short majorVersion;
    private final Set<ConstantPool> constantPools;
    public short accessFlags;
    public short classIndex;
    public short superClassNameIndex;
    private final Set<Short> interfaces;
    private final Set<Field> fields;
    private final Set<Method> methods;
    private final Set<Attribute> attributes;

    public ClassFile() {
        this.magicNumber = 0xCAFEBABE;
        this.minorVersion = 0;
        this.majorVersion = 52;
        this.accessFlags = ClassAccessFlag.PUBLIC.getValue();
        this.constantPools = new LinkedHashSet<ConstantPool>();
        this.interfaces = new HashSet<Short>();
        this.fields = new HashSet<Field>();
        this.methods = new HashSet<Method>();
        this.attributes = new HashSet<Attribute>();
    }

    public Set<ConstantPool> getConstantPools() {
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

    public Set<Short> getInterfaces() {
        return this.interfaces;
    }

    public Set<Field> getFields() {
        return this.fields;
    }

    public Set<Method> getMethods() {
        return this.methods;
    }

    public Set<Attribute> getAttributes() {
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
        return 26 + constantLength + interfaceLength + fieldLength + methodLength + attributeLength;
    }

    @Override
    public byte[] toByte() {
        ConstantUtf8 constantUtf8 = new ConstantUtf8();
        ByteBuffer byteBuffer;
        byte[] temp;

        constantUtf8.setString(COMPILER_AUTHOR);
        byteBuffer = ByteBuffer.allocate(sizeOfByteArray() + constantUtf8.sizeOfByteArray());

        byteBuffer.putInt(magicNumber);
        byteBuffer.putShort(minorVersion);
        byteBuffer.putShort(majorVersion);
        this.constantPools.add(constantUtf8);
        byteBuffer.putShort((short) (this.constantPools.size() + 1));
        for (ConstantPool constantPool : this.constantPools) {
            temp = constantPool.toByte();
            if (temp != null)
                byteBuffer.put(temp);
        }
        this.constantPools.remove(constantUtf8);
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
