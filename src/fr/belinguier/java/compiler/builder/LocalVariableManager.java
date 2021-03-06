package fr.belinguier.java.compiler.builder;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Eliott Belinguier
 */
public class LocalVariableManager {

    private final Set<LocalVariableBuilder> localVariables;

    public LocalVariableManager() {
        this.localVariables = new HashSet<LocalVariableBuilder>();
    }

    public LocalVariableBuilder[] getConstantPools() {
        return this.localVariables.toArray(new LocalVariableBuilder[this.localVariables.size()]);
    }

    public LocalVariableBuilder getLocalVariable(String name) {
        int nameHashCode;

        if (name == null)
            return null;
        nameHashCode = name.hashCode();
        for (LocalVariableBuilder localVariable : this.localVariables)
            if (localVariable.getName().hashCode() == nameHashCode)
                return localVariable;
        return null;
    }

    public LocalVariableBuilder getOrRegisterLocalVariable(LocalVariableBuilder localVariable) {
        if (localVariable == null || localVariable.getName() == null || localVariable.getDescriptor() == null)
            return null;
        if (!this.localVariables.add(localVariable))
            return getLocalVariable(localVariable.getName());
        return localVariable;
    }

    public boolean removeLocalVariable(String name) {
        LocalVariableBuilder localVariable = getLocalVariable(name);

        if (localVariable == null)
            return false;
        return this.localVariables.remove(localVariable);
    }

    protected boolean registerAll(MethodBuilder method) {
        if (method == null)
            return false;
        return true;
    }

    public void clear() {
        this.localVariables.clear();
    }
}
