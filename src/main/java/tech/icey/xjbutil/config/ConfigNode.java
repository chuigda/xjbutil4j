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
        private final String key;
        private final HashMap<String, ConfigNode> children;

        public ConfigTree(String key) {
            this.key = key;
            this.children = new HashMap<>();
        }

        public String key() {
            return key;
        }

        public HashMap<String, ConfigNode> children() {
            return children;
        }

        @Override
        public String toString() {
            if (key.isEmpty()) {
                return "Config{children=" + children + "}";
            }
            return "ConfigTree{key='" + key + "', children=" + children + "}";
        }
    }
}
