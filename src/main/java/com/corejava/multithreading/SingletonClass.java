package com.corejava.multithreading;

public class SingletonClass {
    private static volatile SingletonClass obj = null;

    public SingletonClass getObj() {
        if (obj == null) {
            synchronized (SingletonClass.class) {
                if (obj == null) {
                    obj = new SingletonClass();
                }
            }
        }
        return obj;
    }
}
