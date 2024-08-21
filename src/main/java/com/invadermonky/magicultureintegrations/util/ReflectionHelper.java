package com.invadermonky.magicultureintegrations.util;

import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import gnu.trove.map.hash.THashMap;
import net.minecraft.tileentity.TileEntity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class ReflectionHelper {
    private static final THashMap<FieldKey, Field> cachedFields = new THashMap<>();

    public static Object getFieldObject(Object obj, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        FieldKey key1 = new FieldKey(obj.getClass(), fieldName);
        FieldKey key2 = new FieldKey(obj.getClass().getSuperclass(), fieldName);

        if(cachedFields.containsKey(key1)) {
            return cachedFields.get(key1).get(obj);
        } else if(cachedFields.containsKey(key2)) {
            return cachedFields.get(key2).get(obj);
        }

        try {
            return getField(obj, fieldName).get(obj);
        } catch (NoSuchFieldException e) {
            return getField(obj, fieldName).get(obj);
        }
    }

    public static Field getField(Object obj, String fieldName) throws NoSuchFieldException {
        FieldKey key1 = new FieldKey(obj.getClass(), fieldName);
        FieldKey key2 = new FieldKey(obj.getClass().getSuperclass(), fieldName);

        if(cachedFields.containsKey(key1)) {
            return cachedFields.get(key1);
        } else if(cachedFields.containsKey(key2)) {
            return cachedFields.get(key2);
        }

        Field objField;
        try {
            objField = obj.getClass().getDeclaredField(fieldName);
            objField.setAccessible(true);
            cachedFields.put(key1, objField);
        } catch (NoSuchFieldException e) {
            objField = obj.getClass().getSuperclass().getDeclaredField(fieldName);
            objField.setAccessible(true);
            cachedFields.put(key2, objField);
        }
        return objField;
    }

    public static void setField(Object obj, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field f = getField(obj, fieldName);
        f.set(obj, value);
    }

    public static IHeatableTile getIHeatableInstance(THashMap<Class<? extends TileEntity>, Class<? extends IHeatableTile>> heatableMap, TileEntity tile) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class <? extends IHeatableTile> clazz = heatableMap.containsKey(tile.getClass()) ? heatableMap.get(tile.getClass()) : heatableMap.get(tile.getClass().getSuperclass());
        try {
            return clazz.getConstructor(tile.getClass()).newInstance(tile);
        } catch (NoSuchMethodException e) {
            return clazz.getConstructor(tile.getClass().getSuperclass()).newInstance(tile);
        }
    }

    private static class FieldKey {
        private final Class<?> clazz;
        private final String fieldName;

        public FieldKey(Object obj, String fieldName) {
            this.clazz = obj.getClass();
            this.fieldName = fieldName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FieldKey fieldKey = (FieldKey) o;
            return Objects.equals(clazz, fieldKey.clazz) && Objects.equals(fieldName, fieldKey.fieldName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(clazz, fieldName);
        }
    }
}
