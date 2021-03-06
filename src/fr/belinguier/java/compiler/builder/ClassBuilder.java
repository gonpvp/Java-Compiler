package fr.belinguier.java.compiler.builder;

import fr.belinguier.java.ClassFile;
import fr.belinguier.java.access.ClassAccessFlag;
import fr.belinguier.java.attribute.AttributeSourceFile;
import fr.belinguier.java.compiler.builder.util.ConstantRegisterUtil;
import fr.belinguier.java.compiler.builder.util.DescriptorBuilder;

import java.io.File;
import java.util.Arrays;

/**
 * @author Eliott Belinguier
 */
public class ClassBuilder {

    private static final String BYTECODE_BUILDER_AUTHOR = "Byte code built by Eliott Belinguier ByteCode Builder";

    private final ConstantManager constantManager;
    private final String name;
    private String superClass;
    private ClassAccessFlag[] accessFlags;
    private final FieldManager fieldManager;
    private final MethodManager methodManager;
    private final AttributeManager attributeManager;
    private String sourceFileName;

    protected ClassBuilder(String name) {
        this.name = name;
        this.constantManager = new ConstantManager();
        this.accessFlags = new ClassAccessFlag[] {ClassAccessFlag.PUBLIC};
        this.fieldManager = new FieldManager();
        this.methodManager = new MethodManager();
        this.attributeManager = new AttributeManager();
    }

    public ConstantManager getConstantManager() {
        return this.constantManager;
    }

    public String getName() {
        return this.name;
    }

    public void setSuperClass(String superClass) {
        this.superClass = superClass;
    }

    public String getSuperClass() {
        return this.superClass;
    }

    public void setAccessFlags(ClassAccessFlag... accessFlags) {
        if (accessFlags == null)
            return;
        this.accessFlags = accessFlags;
    }

    public ClassAccessFlag[] getAccessFlags() {
        return Arrays.copyOf(this.accessFlags, this.accessFlags.length);
    }

    public FieldBuilder getField(String name) {
        return this.fieldManager.getField(name);
    }

    protected FieldBuilder getOrCreateFieldFromDescriptor(String name, String descriptor) {
        FieldBuilder fieldBuilder;

        if (name == null || descriptor == null)
            return null;
        fieldBuilder = new FieldBuilder(name, descriptor);
        return this.fieldManager.getOrRegisterField(fieldBuilder);
    }

    public FieldBuilder getOrCreateField(String name, String typeClassName) {
        return getOrCreateFieldFromDescriptor(name, DescriptorBuilder.getTypeDescriptor(typeClassName));
    }

    public FieldBuilder getOrCreateField(String name, Class<?> typeClass) {
        return getOrCreateFieldFromDescriptor(name, DescriptorBuilder.getTypeDescriptor(typeClass));
    }

    public MethodBuilder getMethod(String name, String descriptor) {
        return this.methodManager.getMethod(name, descriptor);
    }

    private MethodBuilder getOrCreateMethod(String name, String descriptor) {
        MethodBuilder methodBuilder;

        if (name == null || descriptor == null)
            return null;
        methodBuilder = new MethodBuilder(this, name, descriptor.replaceAll("\\.", "/"));
        return this.methodManager.getOrRegisterMethod(methodBuilder);
    }

    public MethodBuilder getOrCreateMethodName(String name, String returnClassName, String... parameterClassName) {
        if (name == null)
            return null;
        return getOrCreateMethod(name, DescriptorBuilder.getMethodDescriptor(returnClassName, parameterClassName));
    }

    public MethodBuilder getOrCreateMethodClass(String name, Class<?> returnClass, Class<?>... parameterClass) {
        if (name == null)
            return null;
        return getOrCreateMethod(name, DescriptorBuilder.getMethodDescriptor(returnClass, parameterClass));
    }

    public String getSourceFileName() {
        return this.sourceFileName;
    }

    public void setSourceFileName(String sourceFileName) {
        this.sourceFileName = sourceFileName;
    }

    private ClassFile createClassFile() {
        ClassFile classFile = new ClassFile();
        AttributeSourceFile attributeSourceFile;

        classFile.setAccessFlags(this.accessFlags);
        classFile.classIndex = ConstantRegisterUtil.registerClassConstant(this.constantManager, this.name);
        classFile.superClassNameIndex = ConstantRegisterUtil.registerClassConstant(this.constantManager, this.superClass);
        if (classFile.superClassNameIndex == 0)
            classFile.superClassNameIndex = ConstantRegisterUtil.registerClassConstant(this.constantManager, "java.lang.Object");
        if (classFile.classIndex == 0 || classFile.superClassNameIndex == 0)
            return null;
        if (sourceFileName != null) {
            attributeSourceFile = new AttributeSourceFile();
            attributeSourceFile.sourceFileIndex = ConstantRegisterUtil.registerUtf8Constant(this.constantManager, this.sourceFileName);
            this.attributeManager.register("SourceFile", attributeSourceFile);
        }
        this.fieldManager.registerAll(this.constantManager, classFile.getFields());
        this.methodManager.registerAll(this.constantManager, classFile.getMethods());
        this.attributeManager.registerAll(this.constantManager, classFile.getAttributes());
        ConstantRegisterUtil.registerUtf8Constant(this.constantManager, BYTECODE_BUILDER_AUTHOR);
        this.constantManager.registerAll(classFile);
        return classFile;
    }

    public byte[] toByte() {
        ClassFile classFile = createClassFile();

        if (classFile == null)
            return null;
        return classFile.toByte();
    }

    public boolean write(File file) {
        ClassFile classFile = createClassFile();

        if (classFile == null)
            return false;
        return classFile.write(file);
    }

    public static ClassBuilder create(String className) {
        if (className == null)
            return null;
        return new ClassBuilder(className);
    }
}
