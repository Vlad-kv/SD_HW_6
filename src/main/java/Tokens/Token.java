package Tokens;

import Visitors.TokenVisitor;

public interface Token {
    void accept(TokenVisitor visitor);
}
