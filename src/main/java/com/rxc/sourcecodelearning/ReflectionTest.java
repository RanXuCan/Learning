package com.rxc.sourcecodelearning;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * @Description: 使用反射打印某个Java api中类的信息（域、构造器、方法）
 * @Author RanXuCan
 * @Date 2020/9/14 18:00
 */
public class ReflectionTest {

    public static void main(String[] argv) {
        System.out.println("请输入某个类的全路径名：");
        Scanner scanner = new Scanner(System.in);
        String className = scanner.next().trim();
        printClass(className);

        System.out.println(fun(9));

    }

    public static int fun(int i) {
        if (i == 1)
            return 1;
        if (i == 2)
            return 2;
        if (i > 2)
            return fun(i - 1) + fun(i - 2);
        else return 0;
    }

    public static void printClass(String string) {
        try {
            Class<?> aClass = Class.forName(string);
            Class<?> superclass = aClass.getSuperclass();
            String modifier = Modifier.toString(aClass.getModifiers());
            if (modifier.length() > 0) {
                System.out.print(modifier);
            }
            System.out.print(" class" + aClass.getName());
            if (superclass != null) {
                System.out.print(" extends " + superclass.getName());
            }
            System.out.println("{");

            printConstructor(aClass);

            printMethod(aClass);

            printField(aClass);

            System.out.println("\n}");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printField(Class<?> aClass) {
        System.out.println();
        Field[] fields = aClass.getFields();
        for (Field field : fields) {
            System.out.print("\t");
            String modifiers = Modifier.toString(field.getModifiers());
            if (modifiers.length() > 0)
                System.out.println(modifiers + " " + field.getType() + " " + field.getName() + ";");
        }
    }

    private static void printMethod(Class<?> aClass) {
        System.out.println();
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.print("\t");
            String modifiers = Modifier.toString(method.getModifiers());
            if (modifiers.length() > 0)
                System.out.print(modifiers + " " + method.getReturnType().getName() + " " + method.getName() + "(");

            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                if (i > 0)
                    System.out.print(",");
                System.out.print(parameterTypes[i].getName());
            }
            System.out.println(");");
        }
    }

    private static void printConstructor(Class<?> aClass) {
        System.out.println();
        Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();
        for (Constructor constructor : declaredConstructors) {
            System.out.print("\t");
            String modifiers = Modifier.toString(constructor.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.print(constructor.getName() + "(");
            Class[] parameterTypes = constructor.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                if (i > 0) System.out.print(",");
                System.out.print(parameterTypes[i].getName());
            }
            System.out.println(");");
        }
    }
}
