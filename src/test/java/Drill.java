import tech.icey.xjbutil.config.Config;

import java.util.Arrays;

public class Drill {
    public static void main(String[] args) {
        var configText =
                """
                a = 42
                b.c = 45
                b.d = this is a line of text
                b.e.c1 = another line of text
                b.e.c2 = 1145""";

        var config = Config.readConfigText(configText);
        System.err.println(config);
        System.err.println(Config.generateConfigText(config));
    }
}
