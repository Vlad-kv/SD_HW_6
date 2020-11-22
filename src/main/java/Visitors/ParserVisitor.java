package Visitors;

import Tokens.Brace;
import Tokens.NumberToken;
import Tokens.Operation;
import Tokens.Token;

import java.util.ArrayList;

public class ParserVisitor implements TokenVisitor {
    private final ArrayList<Token> stack = new ArrayList<>();
    private final ArrayList<Token> result = new ArrayList<>();
    private boolean isOperandExpected = true;

    @Override
    public void visit(NumberToken token) {
        if (! isOperandExpected) {
            throw new RuntimeException("Incorrect expression.");
        }
        isOperandExpected = false;
        result.add(token);
    }
    @Override
    public void visit(Brace token) {
        if (token.getBraceType() == Brace.BraceType.OPENING) {
            if (! isOperandExpected) {
                throw new RuntimeException("Incorrect expression.");
            }
            stack.add(token);
        } else {
            if (isOperandExpected) {
                throw new RuntimeException("Incorrect expression.");
            }
            while (! stack.isEmpty()) {
                Token t = stack.get(stack.size() - 1);
                if (t instanceof Operation) {
                    result.add(t);
                    stack.remove(stack.size() - 1);
                } else {
                    break;
                }
            }
            if ((stack.isEmpty()) || (! (stack.get(stack.size() - 1) instanceof Brace))) {
                throw new RuntimeException("Incorrect expression.");
            } else {
                stack.remove(stack.size() - 1);
            }
        }
    }
    @Override
    public void visit(Operation token) {
        if (isOperandExpected) {
            throw new RuntimeException("Incorrect expression.");
        }
        isOperandExpected = true;
        while (! stack.isEmpty()) {
            if (stack.get(stack.size() - 1) instanceof Operation) {
                Operation op = (Operation) stack.get(stack.size() - 1);
                if (! (
                        ((   op.getOperationType() == Operation.OperationType.ADDITION) ||
                         (   op.getOperationType() == Operation.OperationType.SUBTRACTION)) &&
                        ((token.getOperationType() == Operation.OperationType.MULTIPLICATION) ||
                         (token.getOperationType() == Operation.OperationType.DIVISION))
                      )
                ) {
                    result.add(op);
                    stack.remove(stack.size() - 1);
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        stack.add(token);
    }

    public ArrayList<Token> getResult() {
        while ((! stack.isEmpty()) && (stack.get(stack.size() - 1) instanceof  Operation)) {
            result.add(stack.get(stack.size() - 1));
            stack.remove(stack.size() - 1);
        }
        if (!stack.isEmpty()) {
            throw new RuntimeException("Incorrect expression.");
        }
        return result;
    }
}
