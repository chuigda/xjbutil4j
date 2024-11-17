package tech.icey.xjbutil.config;

public @interface ConfigItem {
    String name() default "";
    boolean hasDefault() default false;
    String defaultValue() default "";
}
