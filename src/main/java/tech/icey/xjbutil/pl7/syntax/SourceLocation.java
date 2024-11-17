package tech.icey.xjbutil.pl7.syntax;

public record SourceLocation(String file, int line, int col) {
    public static final SourceLocation DUMMY = new SourceLocation("", -1, -1);
}
