package org.mkproductions.jugnu.utilities;


import org.mkproductions.jugnu.entities.ClassifyAbleClass;
import org.mkproductions.jugnu.entities.ClassifyAbleConstructor;
import org.mkproductions.jugnu.entities.ClassifyAbleField;
import org.mkproductions.jugnu.entities.ClassifyAbleMethod;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class PackageReader {
    private static PackageReader INSTANCE = null;

    private PackageReader() {
    }

    public static PackageReader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PackageReader();
        }
        return INSTANCE;
    }

    public List<Class<?>> findAllClasses(String packageName) {
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        return new HashSet<>(reflections.getSubTypesOf(Object.class)).stream().toList();
    }

    public List<Class<? extends ClassifyAbleClass>> findAllClassesAnnotatedWith(String packageName) {
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false), new TypeAnnotationsScanner());
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(ClassifyAbleClass.class, false);
        List<Class<? extends ClassifyAbleClass>> list = new ArrayList<>();
        classes.forEach(it -> {
            if (it.isAnnotationPresent(ClassifyAbleClass.class) && it != ClassifyAbleClass.class)
                list.add((Class<? extends ClassifyAbleClass>) it);
        });
        return list;
    }

    public List<Constructor<ClassifyAbleConstructor>> findAllAnnotatedConstructorsFromClass(Class<? extends ClassifyAbleClass> annoatedClass) {
        Reflections reflections = new Reflections(annoatedClass, new SubTypesScanner(false), new MethodAnnotationsScanner());
        Set<Constructor> classes = reflections.getConstructorsAnnotatedWith(ClassifyAbleConstructor.class);
        List<Constructor<ClassifyAbleConstructor>> list = new ArrayList<>();
        classes.forEach(it -> {
            if (it.isAnnotationPresent(ClassifyAbleConstructor.class)) list.add(it);
        });
        return list;
    }

    public List<Field> findAllAnnotatedFieldsFromClass(Class<? extends ClassifyAbleClass> annoatedClass) {
        Reflections reflections = new Reflections(annoatedClass, new SubTypesScanner(false), new TypeAnnotationsScanner());
        List<Field> annotatedFields = new ArrayList<>();
        reflections.getTypesAnnotatedWith(ClassifyAbleClass.class).forEach(it -> {
            if (it.isAssignableFrom(annoatedClass))
                for (Field field : it.getDeclaredFields())
                    if (field.isAnnotationPresent(ClassifyAbleField.class)) annotatedFields.add(field);
        });
        return annotatedFields;
    }

    public List<Method> findAllAnnotatedMethodsFromClass(Class<? extends ClassifyAbleClass> annoatedClass) {
        Reflections reflections = new Reflections(annoatedClass, new SubTypesScanner(false), new TypeAnnotationsScanner());
        List<Method> annotatedMethods = new ArrayList<>();
        reflections.getTypesAnnotatedWith(ClassifyAbleClass.class).forEach(it -> {
            if (it.isAssignableFrom(annoatedClass))
                for (Method mth : it.getMethods())
                    if (mth.isAnnotationPresent(ClassifyAbleMethod.class))
                        annotatedMethods.add(mth);
        });
        return annotatedMethods;
    }
}