package tech.icey.xjbutil.pl7.syntax;

import tech.icey.xjbutil.container.Option;

public record Token(TokenKind kind, Option<Object> value, SourceLocation location) {
    public Token(TokenKind kind, SourceLocation location) {
        this(kind, Option.none(), location);
    }

    public Token(byte value, SourceLocation location) {
        this(TokenKind.ByteLiteral, Option.some(value), location);
    }

    public Token(int value, SourceLocation location) {
        this(TokenKind.IntLiteral, Option.some(value), location);
    }

    public Token(long value, SourceLocation location) {
        this(TokenKind.LongLiteral, Option.some(value), location);
    }

    public Token(float value, SourceLocation location) {
        this(TokenKind.FloatLiteral, Option.some(value), location);
    }

    public Token(double value, SourceLocation location) {
        this(TokenKind.DoubleLiteral, Option.some(value), location);
    }

    public Token(String value, SourceLocation location) {
        this(TokenKind.StringLiteral, Option.some(value), location);
    }

    public static final Token EOI = new Token(TokenKind.EndOfInput, SourceLocation.DUMMY);
}
