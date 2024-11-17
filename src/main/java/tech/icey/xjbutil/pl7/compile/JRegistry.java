package tech.icey.xjbutil.pl7.compile;

import tech.icey.xjbutil.functional.GeneralAction;
import tech.icey.xjbutil.functional.GeneralFunction;

import java.util.HashMap;

public final class JRegistry {
    private record JFunction(GeneralFunction function, boolean returnsVoid) {
        JFunction(GeneralFunction function) {
            this(function, false);
        }

        JFunction(GeneralAction action) {
            this(args -> {
                action.accept(args);
                return null;
            }, true);
        }
    }

    private final HashMap<String, JFunction> javaFunctions = new HashMap<>();
    private final HashMap<String, Class<?>> javaClasses = new HashMap<>();

    public boolean registerFunction(String name, GeneralFunction function) {
        boolean duplicate = javaFunctions.containsKey(name);
        javaFunctions.put(name, new JFunction(function));
        return duplicate;
    }

    public boolean registerAction(String name, GeneralAction action) {
        boolean duplicate = javaFunctions.containsKey(name);
        javaFunctions.put(name, new JFunction(action));
        return duplicate;
    }

    public boolean registerClass(String name, Class<?> clazz) {
        boolean duplicate = javaClasses.containsKey(name);
        javaClasses.put(name, clazz);
        return duplicate;
    }

    public HashMap<String, JFunction> javaFunctions() {
        return javaFunctions;
    }

    public HashMap<String, Class<?>> javaClasses() {
        return javaClasses;
    }
}
