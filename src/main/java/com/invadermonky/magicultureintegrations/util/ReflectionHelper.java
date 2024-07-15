package com.invadermonky.magicultureintegrations.util;

import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import gnu.trove.map.hash.THashMap;
import net.minecraft.tileentity.TileEntity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ReflectionHelper {
    public static Object getFieldObject(Object obj, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        try {
            Field objField = obj.getClass().getDeclaredField(fieldName);
            objField.setAccessible(true);
            return objField.get(obj);
        } catch (NoSuchFieldException e) {
            Field objField = obj.getClass().getSuperclass().getDeclaredField(fieldName);
            objField.setAccessible(true);
            return objField.get(obj);
        }
    }

    public static Field getField(Object obj, String fieldName) throws NoSuchFieldException {
        Field objField;
        try {
            objField = obj.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            objField = obj.getClass().getSuperclass().getDeclaredField(fieldName);
        }
        objField.setAccessible(true);
        return objField;
    }

    public static IHeatableTile getIHeatableInstance(THashMap<Class<? extends TileEntity>, Class<? extends IHeatableTile>> heatableMap, TileEntity tile) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class <? extends IHeatableTile> clazz = heatableMap.containsKey(tile.getClass()) ? heatableMap.get(tile.getClass()) : heatableMap.get(tile.getClass().getSuperclass());
        try {
            return clazz.getConstructor(tile.getClass()).newInstance(tile);
        } catch (NoSuchMethodException e) {
            return clazz.getConstructor(tile.getClass().getSuperclass()).newInstance(tile);
        }
    }
}
