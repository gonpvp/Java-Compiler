package fr.belinguier.java.compiler.builder;

import fr.belinguier.java.Method;
import fr.belinguier.java.compiler.builder.util.ConstantRegisterUtil;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Eliott Belinguier
 */
public class MethodManager {

    private final Set<MethodBuilder> methods;

    public MethodManager() {
        this.methods = new HashSet<MethodBuilder>();
    }

    public MethodBuilder getMethod(String name, String descriptor) {
        int hash;

        if (name == null || descriptor == null)
            return null;
        hash = Objects.hash(name, descriptor);
        for (MethodBuilder methodBuilder : methods)
            if (methodBuilder.hashCode() == hash)
                return methodBuilder;
        return null;
    }

    public MethodBuilder getOrRegisterMethod(MethodBuilder method) {
        if (method == null || method.getName() == null || method.getDescriptor() == null)
            return null;
        if (!this.methods.add(method))
            return getMethod(method.getName(), method.getDescriptor());
        return method;
    }

    public boolean removeMethod(String name, String descriptor) {
        MethodBuilder methodBuilder = getMethod(name, descriptor);

        if (methodBuilder == null)
            return false;
        return this.methods.remove(methodBuilder);
    }

    protected boolean registerAll(ConstantManager constantManager, Set<Method> methods) {
        Method method;

        if (constantManager == null || methods == null)
            return false;
        for (MethodBuilder methodBuilder : this.methods) {
            method = new fr.belinguier.java.Method();
            method.nameIndex = ConstantRegisterUtil.registerUtf8Constant(constantManager, methodBuilder.getName());
            method.descriptorIndex = ConstantRegisterUtil.registerUtf8Constant(constantManager, methodBuilder.getDescriptor());
            method.setAccessFlags(methodBuilder.getAccessFlags());
            if (method.nameIndex == 0 || method.descriptorIndex == 0 || !methodBuilder.attributeManager.registerAll(constantManager, method.getAttributes()))
                return false;
            methods.add(method);
        }
        return true;
    }

    public void clear() {
        this.methods.clear();
    }
}
