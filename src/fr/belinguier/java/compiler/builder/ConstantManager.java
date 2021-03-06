package fr.belinguier.java.compiler.builder;

import fr.belinguier.java.ClassFile;
import fr.belinguier.java.constant.ConstantPool;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Eliott Belinguier
 */
public class ConstantManager {

    private final Set<ConstantPool> constants;

    public ConstantManager() {
        this.constants = new LinkedHashSet<ConstantPool>();
    }

    public ConstantPool[] getConstantPools() {
        return this.constants.toArray(new ConstantPool[this.constants.size()]);
    }

    public short indexOf(ConstantPool constantPool) {
        ConstantPool[] constantPools;

        if (constantPool == null)
            return 0;
        constantPools = getConstantPools();
        for (int i = 0; i < this.constants.size(); i++) {
            if (constantPools[i].hashCode() == constantPool.hashCode())
                return (short) (i + 1);
        }
        return 0;
    }

    public short getOrRegister(ConstantPool constantPool) {
        if (constantPool == null)
            return 0;
        if (!this.constants.add(constantPool))
            return indexOf(constantPool);
        return (short) this.constants.size();
    }

    public boolean registerAll(ClassFile classFile) {
        if (classFile == null)
            return true;
        return classFile.getConstantPools().addAll(this.constants);
    }

    public void clear() {
        this.constants.clear();
    }
}
