package fr.belinguier.java.compiler.builder;

import fr.belinguier.java.Field;
import fr.belinguier.java.compiler.builder.util.ConstantRegisterUtil;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Eliott Belinguier
 */
public class FieldManager {

    private final Set<FieldBuilder> fields;

    public FieldManager() {
        this.fields = new HashSet<FieldBuilder>();
    }

    public FieldBuilder getField(String name) {
        int hash;

        if (name == null)
            return null;
        hash = Objects.hashCode(name);
        for (FieldBuilder fieldBuilder : fields)
            if (fieldBuilder.hashCode() == hash)
                return fieldBuilder;
        return null;
    }

    public FieldBuilder getOrRegisterField(FieldBuilder field) {
        if (field == null || field.getName() == null || field.getDescriptor() == null)
            return null;
        if (!this.fields.add(field))
            return getField(field.getName());
        return field;
    }

    public boolean removeField(String name) {
        FieldBuilder fieldBuilder = getField(name);

        if (fieldBuilder == null)
            return false;
        return this.fields.remove(fieldBuilder);
    }

    public boolean registerAll(ConstantManager constantManager, Set<Field> fields) {
        Field field;

        if (constantManager == null || fields == null)
            return false;
        for (FieldBuilder fieldBuilder : this.fields) {
            field = new Field();
            field.nameIndex = ConstantRegisterUtil.registerUtf8Constant(constantManager, fieldBuilder.getName());
            field.descriptorIndex = ConstantRegisterUtil.registerUtf8Constant(constantManager, fieldBuilder.getDescriptor());
            field.setAccessFlags(fieldBuilder.getAccessFlags());
            if (field.nameIndex == 0 || field.descriptorIndex == 0 || !fieldBuilder.attributeManager.registerAll(constantManager, field.getAttributes()))
                return false;
            fields.add(field);
        }
        return true;
    }

    public void clear() {
        this.fields.clear();
    }
}
