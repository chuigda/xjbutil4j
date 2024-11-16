package tech.icey.xjbutil.config;

import tech.icey.xjbutil.container.Option;
import tech.icey.xjbutil.functional.Action2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public final class Config {
    public static String generateConfigText(ConfigNode.ConfigTree config) {
        StringBuilder sb = new StringBuilder();
        generateConfigText("", config, sb, 0);
        return sb.toString();
    }

    public static ConfigNode.ConfigTree readConfigText(String text, Option<Action2<Integer, String>> errorCallback) {
        List<String> lines = Arrays.stream(text.split("\n"))
                .map(String::strip)
                .filter(s -> !s.isEmpty())
                .toList();
        ConfigNode.ConfigTree root = new ConfigNode.ConfigTree("");

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.startsWith("#") || line.startsWith("[") && line.endsWith("]")) {
                continue;
            }

            String[] parts = line.split("=", 2);
            if (parts.length != 2) {
                if (errorCallback instanceof Option.Some<Action2<Integer, String>> someErrorCallback) {
                    someErrorCallback.value.apply(i, "bad syntax: " + line);
                }
            }

            String key = parts[0].strip();
            String value = parts[1].strip();

            String[] keySections = key.split("\\.");
            for (String section : keySections) {
                if (section.isBlank()) {
                    if (errorCallback instanceof Option.Some<Action2<Integer, String>> someErrorCallback) {
                        someErrorCallback.value.apply(i, "bad key: " + key);
                    }
                }
            }

            recursiveInsert(root.children(), keySections, value, i, 0, errorCallback);
        }
        return root;
    }

    private static void recursiveInsert(
            HashMap<String, ConfigNode> map,
            String[] keySections,
            String value,
            int line,
            int sectionId,
            Option<Action2<Integer, String>> errorCallback
    ) {
        if (sectionId == keySections.length - 1) {
            if (map.containsKey(keySections[sectionId])) {
                if (errorCallback instanceof Option.Some<Action2<Integer, String>> someErrorCallback) {
                    someErrorCallback.value.apply(line, "duplicate key: " + keySections[sectionId]);
                }
            } else {
                map.put(keySections[sectionId], new ConfigNode.ConfigValue(value));
            }
        } else {
            ConfigNode.ConfigTree tree;
            if (map.containsKey(keySections[sectionId])) {
                if (map.get(keySections[sectionId]) instanceof ConfigNode.ConfigTree t) {
                    tree = t;
                } else {
                    if (errorCallback instanceof Option.Some<Action2<Integer, String>> someErrorCallback) {
                        someErrorCallback.value.apply(
                                line,
                                "key conflict: " + keySections[sectionId] + "is already a value and cannot be a tree"
                        );
                    }
                    return;
                }
            } else {
                tree = new ConfigNode.ConfigTree(keySections[sectionId]);
                map.put(keySections[sectionId], tree);
            }

            recursiveInsert(tree.children(), keySections, value, line, sectionId + 1, errorCallback);
        }
    }

    public static ConfigNode.ConfigTree readConfigText(String text) {
        return readConfigText(text, Option.none());
    }

    private static void generateConfigText(String prefix, ConfigNode node, StringBuilder builder, int depth) {
        if (node instanceof ConfigNode.ConfigValue value) {
            builder.append(prefix).append(" = ").append(value.value()).append("\n");
        } else if (node instanceof ConfigNode.ConfigTree tree) {
            String newPrefix = prefix.isEmpty() ? tree.key() : prefix + "." + tree.key();
            // for each key-value pair
            tree.children().forEach((key, value) -> {
                generateConfigText(newPrefix, value, builder, depth + 1);
            });
        }
    }

    private static boolean isValidKeyCharacter(char ch) {
        return Character.isLetterOrDigit(ch) || ch == '_' || ch == '-' || ch == '$';
    }
}
