package tech.icey.xjbutil.config;

import java.util.HashMap;

public abstract sealed class ConfigNode {
    private final String key;

    public ConfigNode(String key) {
        this.key = key;
    }

    public final String key() {
        return key;
    }

    public final class ConfigValue extends ConfigNode {
        private final String value;

        public ConfigValue(String key, String value) {
            super(key);
            this.value = value;
        }

        public String value() {
            return value;
        }
    }

    public final class ConfigTree extends ConfigNode {
        private final HashMap<String, ConfigNode> children;

        public ConfigTree(String key) {
            super(key);
            this.children = new HashMap<>();
        }

        public HashMap<String, ConfigNode> children() {
            return children;
        }
    }
}
