package tech.icey.xjbutil.config;

import java.util.HashMap;

public abstract sealed class ConfigNode {
    public static final class ConfigValue extends ConfigNode {
        private final String value;

        public ConfigValue(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }

        @Override
        public String toString() {
            return "\"" + this.value + "\"";
        }
    }

    public static final class ConfigTree extends ConfigNode {
        private final HashMap<String, ConfigNode> children;

        public ConfigTree() {
            this.children = new HashMap<>();
        }

        public HashMap<String, ConfigNode> children() {
            return children;
        }

        @Override
        public String toString() {
            return "ConfigTree{children=" + children + "}";
        }
    }
}
