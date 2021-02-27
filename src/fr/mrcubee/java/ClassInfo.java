package fr.mrcubee.java;

import fr.mrcubee.java.compiler.Serializable;
import fr.mrcubee.java.constant.ConstantPoolInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eliott Belinguier
 */
public class ClassInfo implements Serializable {

    private final int magicNumber;
    private short minorVersion;
    private short majorVersion;
    private List<ConstantPoolInfo> constantPoolInfos;
    private AccessFlag accessFlag;
    private short nameIndex;
    private short superClassNameIndex;

    public ClassInfo() {
        this.magicNumber = 0xCAFEBABE;
        this.constantPoolInfos = new ArrayList<ConstantPoolInfo>();
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

    public void setAccessFlag(AccessFlag accessFlag) {
        if (accessFlag == null)
            this.accessFlag = AccessFlag.PUBLIC;
        else
            this.accessFlag = accessFlag;
    }

    public AccessFlag getAccessFlag() {
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

    @Override
    public int sizeOfByteArray() {
        return 0;
    }

    @Override
    public byte[] toByte() {
        return new byte[0];
    }
}
