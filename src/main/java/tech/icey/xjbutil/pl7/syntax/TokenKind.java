package tech.icey.xjbutil.pl7.syntax;

public enum TokenKind {
    Ident,
    ByteLiteral,
    IntLiteral,
    LongLiteral,
    FloatLiteral,
    DoubleLiteral,
    CharLiteral,
    StringLiteral,

    Plus,
    Minus,
    Aster,
    Slash,
    Backslash,
    Exclamation,
    Tilde,
    Percent,
    Caret,
    Amp,
    Pipe,
    Less,
    Greater,
    Eq,
    DAmp,
    DPipe,
    DEq,
    LessEq,
    GreaterEq,
    TildeEq,
    LParen,
    RParen,
    LBrace,
    RBrace,
    LBracket,
    RBracket,
    Comma,
    Colon,
    Semicolon,
    Dot,

    KwdVar,
    KwdByte,
    KwdInt,
    KwdLong,
    KwdFloat,
    KwdDouble,
    KwdChar,
    KwdVoid,
    KwdBool,
    KwdTrue,
    KwdFalse,
    KwdIf,
    KwdElse,
    KwdWhile,
    KwdFor,
    KwdReturn,
    KwdBreak,
    KwdContinue,
    KwdClass,

    EndOfInput;

    public String repr() {
        return switch (this) {
            case Ident -> "identifier";
            case ByteLiteral -> "byte literal";
            case IntLiteral -> "int literal";
            case LongLiteral -> "long literal";
            case FloatLiteral -> "float literal";
            case DoubleLiteral -> "double literal";
            case CharLiteral -> "char literal";
            case StringLiteral -> "string literal";

            case Plus -> "+";
            case Minus -> "-";
            case Aster -> "*";
            case Slash -> "/";
            case Backslash -> "\\";
            case Exclamation -> "!";
            case Tilde -> "~";
            case Percent -> "%";
            case Caret -> "^";
            case Amp -> "&";
            case Pipe -> "|";
            case Less -> "<";
            case Greater -> ">";
            case Eq -> "=";
            case DAmp -> "&&";
            case DPipe -> "||";
            case DEq -> "==";
            case LessEq -> "<=";
            case GreaterEq -> ">=";
            case TildeEq -> "!=";
            case LParen -> "(";
            case RParen -> ")";
            case LBrace -> "{";
            case RBrace -> "}";
            case LBracket -> "[";
            case RBracket -> "]";
            case Comma -> ",";
            case Colon -> ":";
            case Semicolon -> ";";
            case Dot -> ".";
            case KwdVar -> "var";
            case KwdByte -> "byte";
            case KwdInt -> "int";
            case KwdLong -> "long";
            case KwdFloat -> "float";
            case KwdDouble -> "double";
            case KwdChar -> "char";
            case KwdVoid -> "void";
            case KwdBool -> "bool";
            case KwdTrue -> "true";
            case KwdFalse -> "false";
            case KwdIf -> "if";
            case KwdElse -> "else";
            case KwdWhile -> "while";
            case KwdFor -> "for";
            case KwdReturn -> "return";
            case KwdBreak -> "break";
            case KwdContinue -> "continue";
            case KwdClass -> "class";
            case EndOfInput -> "end of input";
        };
    }
}
