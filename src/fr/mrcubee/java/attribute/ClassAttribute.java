package fr.mrcubee.java.attribute;

import fr.mrcubee.java.access.ClassAccessFlag;
import fr.mrcubee.java.compiler.Serializable;
import fr.mrcubee.java.constant.ConstantClassInfo;
import fr.mrcubee.java.constant.ConstantPoolInfo;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eliott Belinguier
 */
public class ClassAttribute implements Serializable {

    private final int magicNumber;
    private short minorVersion;
    private short majorVersion;
    private final List<ConstantPoolInfo> constantPoolInfos;
    private ClassAccessFlag accessFlag;
    private short nameIndex;
    private short superClassNameIndex;
    private final List<ConstantClassInfo> interfaces;

    public ClassAttribute() {
        this.magicNumber = 0xCAFEBABE;
        this.accessFlag = ClassAccessFlag.PUBLIC;
        this.constantPoolInfos = new ArrayList<ConstantPoolInfo>();
        this.interfaces = new ArrayList<ConstantClassInfo>();
    }

    public int getMagicNumber() {
        return this.magicNumber;
    }

    public void setMinorVersion(short minorVersion) {
        this.minorVersion = minorVersion;
    }

    public short getMinorVersion() {
        return this.minorVersion;
    }

    public void setMajorVersion(short majorVersion) {
        this.majorVersion = majorVersion;
    }

    public short getMajorVersion() {
        return this.majorVersion;
    }

    public List<ConstantPoolInfo> getConstantPoolInfos() {
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

    public void setNameIndex(short nameIndex) {
        this.nameIndex = nameIndex;
    }

    public short getNameIndex() {
        return this.nameIndex;
    }

    public void setSuperClassNameIndex(short superClassNameIndex) {
        this.superClassNameIndex = superClassNameIndex;
    }

    public short getSuperClassNameIndex() {
        return this.superClassNameIndex;
    }

    public List<ConstantClassInfo> getInterfaces() {
        return this.interfaces;
    }

    @Override
    public int sizeOfByteArray() {
        int constantSize = 0;

        for (ConstantPoolInfo constantPoolInfo : this.constantPoolInfos)
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
        for (ConstantPoolInfo constantPoolInfo : this.constantPoolInfos) {
            bytes = constantPoolInfo.toByte();
            if (bytes != null)
                byteBuffer.put(bytes);
        }
        byteBuffer.putShort(this.accessFlag.getValue());
        byteBuffer.putShort(this.nameIndex);
        byteBuffer.putShort(this.superClassNameIndex);
        byteBuffer.putShort((short) this.interfaces.size());
        for (ConstantClassInfo constantClassInfo : this.interfaces) {
            bytes = constantClassInfo.toByte();
            if (bytes != null)
                byteBuffer.put(bytes);
        }
        return byteBuffer.array();
    }
}
