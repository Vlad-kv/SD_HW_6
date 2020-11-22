package Visitors;

import Tokens.Brace;
import Tokens.NumberToken;
import Tokens.Operation;

public class PrintVisitor implements TokenVisitor {
    @Override
    public void visit(NumberToken token) {
        System.out.print(token.getNumber() + " ");
    }
    @Override
    public void visit(Brace token) {
        char val = (token.getBraceType() == Brace.BraceType.OPENING) ? '(' : ')';
        System.out.print(val + " ");
    }
    @Override
    public void visit(Operation token) {
        char val = 0;
        switch (token.getOperationType()) {
            case ADDITION: {
                val = '+';
                break;
            }
            case SUBTRACTION: {
                val = '-';
                break;
            }
            case MULTIPLICATION: {
                val = '*';
                break;
            }
            case DIVISION: {
                val = '/';
                break;
            }
        }
        System.out.print(val + " ");
    }
}
