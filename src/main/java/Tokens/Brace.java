package Tokens;

import Visitors.TokenVisitor;

public class Brace implements Token {
    enum BraceType {OPENING, CLOSING};

    private final BraceType braceType;
    public Brace(BraceType braceType) {
        this.braceType = braceType;
    }

    public BraceType getBraceType() {
        return braceType;
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}
