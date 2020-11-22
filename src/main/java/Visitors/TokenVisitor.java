package Visitors;

import Tokens.Brace;
import Tokens.NumberToken;
import Tokens.Operation;

public interface TokenVisitor {
    void visit(NumberToken token);
    void visit(Brace token);
    void visit(Operation token);
}
