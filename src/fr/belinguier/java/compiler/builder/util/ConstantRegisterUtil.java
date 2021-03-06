package fr.belinguier.java.compiler.builder.util;

import fr.belinguier.java.compiler.builder.ConstantManager;
import fr.belinguier.java.constant.*;

public final class ConstantRegisterUtil {

    public static short registerConstant(ConstantManager constantManager, ConstantPool constantPool) {
        if (constantManager == null || constantPool == null)
            return 0;
        return constantManager.getOrRegister(constantPool);
    }

    public static short registerUtf8Constant(ConstantManager constantManager, String str) {
        ConstantUtf8 constantUtf8;

        if (constantManager == null)
            return 0;
        constantUtf8 = new ConstantUtf8();
        constantUtf8.setString(str);
        return registerConstant(constantManager, constantUtf8);
    }

    public static short registerClassConstant(ConstantManager constantManager, String className) {
        ConstantClass constantClass;

        if (constantManager == null || className == null)
            return 0;
        className = className.replaceAll("\\.", "/");
        constantClass = new ConstantClass();
        constantClass.nameIndex = registerUtf8Constant(constantManager, className);
        return registerConstant(constantManager, constantClass);
    }

    public static short registerNameAndTypeConstant(ConstantManager constantManager, String name, String descriptor) {
        ConstantNameAndType constantNameAndType;

        if (constantManager == null || name == null || descriptor == null)
            return 0;
        constantNameAndType = new ConstantNameAndType();
        constantNameAndType.nameIndex = registerUtf8Constant(constantManager, name);
        constantNameAndType.descriptorIndex = registerUtf8Constant(constantManager, descriptor);
        return registerConstant(constantManager, constantNameAndType);
    }

    public static short registerFieldRefConstant(ConstantManager constantManager, String ownerClass, String name, String descriptor) {
        ConstantFieldRef constantFieldRef;

        if (constantManager == null || ownerClass == null || name == null || descriptor == null)
            return 0;
        constantFieldRef = new ConstantFieldRef();
        constantFieldRef.classIndex = registerClassConstant(constantManager, ownerClass);
        constantFieldRef.nameAndTypeIndex = registerNameAndTypeConstant(constantManager, name, descriptor);
        return registerConstant(constantManager, constantFieldRef);
    }

    public static short registerMethodRefConstant(ConstantManager constantManager, String ownerClass, String name, String descriptor) {
        ConstantMethodRef constantMethodRef;

        if (constantManager == null || ownerClass == null || name == null || descriptor == null)
            return 0;
        constantMethodRef = new ConstantMethodRef();
        constantMethodRef.classIndex = registerClassConstant(constantManager, ownerClass);
        constantMethodRef.nameAndTypeIndex = registerNameAndTypeConstant(constantManager, name, descriptor);
        return registerConstant(constantManager, constantMethodRef);
    }

    public static short registerMethodTypeConstant(ConstantManager constantManager, String descriptor) {
        ConstantMethodType constantMethodType;

        if (constantManager == null || descriptor == null)
            return 0;
        constantMethodType = new ConstantMethodType();
        constantMethodType.descriptorIndex = registerUtf8Constant(constantManager, descriptor);
        return registerConstant(constantManager, constantMethodType);
    }

    public static short registerDoubleConstant(ConstantManager constantManager, double value) {
        ConstantDouble constantDouble;

        if (constantManager == null)
            return 0;
        constantDouble = new ConstantDouble();
        constantDouble.value = value;
        return registerConstant(constantManager, constantDouble);
    }

    public static short registerFloatConstant(ConstantManager constantManager, float value) {
        ConstantFloat constantFloat;

        if (constantManager == null)
            return 0;
        constantFloat = new ConstantFloat();
        constantFloat.value = value;
        return registerConstant(constantManager, constantFloat);
    }

    public static short registerLongConstant(ConstantManager constantManager, long value) {
        ConstantLong constantLong;

        if (constantManager == null)
            return 0;
        constantLong = new ConstantLong();
        constantLong.value = value;
        return registerConstant(constantManager, constantLong);
    }

    public static short registerIntegerConstant(ConstantManager constantManager, int value) {
        ConstantInteger constantInteger;

        if (constantManager == null)
            return 0;
        constantInteger = new ConstantInteger();
        constantInteger.value = value;
        return registerConstant(constantManager, constantInteger);
    }
}
