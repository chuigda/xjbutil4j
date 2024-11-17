package tech.icey.xjbutil.pl7.parse;

import tech.icey.xjbutil.container.Pair;
import tech.icey.xjbutil.pl7.syntax.Token;

public record ParseContext(String source, int position, String file, int line, int col) {
    Pair<Token, ParseContext> nextToken() {
        throw new RuntimeException("not implemented");
    }
}
