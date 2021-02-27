package fr.belinguier.java;

import fr.belinguier.java.access.ClassAccessFlag;
import fr.belinguier.java.compiler.Serializable;
import fr.belinguier.java.info.constant.ConstantClassInfo;
import fr.belinguier.java.info.constant.ConstantPoolInfo;

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
    private final List<ConstantPoolInfo> constantPoolInfos;
    private ClassAccessFlag accessFlag;
    public short nameIndex;
    public short superClassNameIndex;
    private final List<ConstantClassInfo> interfaces;

    public ClassFile() {
        this.magicNumber = 0xCAFEBABE;
        this.accessFlag = ClassAccessFlag.PUBLIC;
        this.constantPoolInfos = new ArrayList<ConstantPoolInfo>();
        this.interfaces = new ArrayList<ConstantClassInfo>();
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
