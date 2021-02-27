package fr.belinguier.java;

import fr.belinguier.java.access.ClassAccessFlag;
import fr.belinguier.java.compiler.Serializable;
import fr.belinguier.java.constant.ConstantClass;
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
    private final List<ConstantPool> constantPoolInfos;
    private ClassAccessFlag accessFlag;
    public short nameIndex;
    public short superClassNameIndex;
    private final List<ConstantClass> interfaces;

    public ClassFile() {
        this.magicNumber = 0xCAFEBABE;
        this.minorVersion = 0;
        this.majorVersion = 52;
        this.accessFlag = ClassAccessFlag.PUBLIC;
        this.constantPoolInfos = new ArrayList<ConstantPool>();
        this.interfaces = new ArrayList<ConstantClass>();
    }

    public List<ConstantPool> getConstantPoolInfos() {
        return this.constantPoolInfos;
    }

    public void setAccessFlag(ClassAccessFlag accessFlag) {
        if (accessFlag == null)
            this.accessFlag = ClassAccessFlag.PUBLIC;
        else
            this.accessFlag = accessFlag;
    }

    public ClassAccessFlag getAccessFlag() {
        return this.accessFlag;
    }

    public List<ConstantClass> getInterfaces() {
        return this.interfaces;
    }

    @Override
    public int sizeOfByteArray() {
        int constantSize = 0;

        for (ConstantPool constantPoolInfo : this.constantPoolInfos)
            constantSize += constantPoolInfo.sizeOfByteArray();
        return 10 + constantSize + 8;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(sizeOfByteArray());
        byte[] bytes;

        byteBuffer.putInt(magicNumber);
        byteBuffer.putShort(minorVersion);
        byteBuffer.putShort(majorVersion);
        byteBuffer.putShort((short) this.constantPoolInfos.size());
        for (ConstantPool constantPoolInfo : this.constantPoolInfos) {
            bytes = constantPoolInfo.toByte();
            if (bytes != null)
                byteBuffer.put(bytes);
        }
        byteBuffer.putShort(this.accessFlag.getValue());
        byteBuffer.putShort(this.nameIndex);
        byteBuffer.putShort(this.superClassNameIndex);
        byteBuffer.putShort((short) this.interfaces.size());
        for (ConstantClass constantClassInfo : this.interfaces) {
            bytes = constantClassInfo.toByte();
            if (bytes != null)
                byteBuffer.put(bytes);
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
