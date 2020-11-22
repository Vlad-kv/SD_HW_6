package Visitors;

import Tokens.Brace;
import Tokens.NumberToken;
import Tokens.Operation;

import java.util.ArrayList;

public class CalcVisitor implements TokenVisitor {
    private final ArrayList<Integer> stack = new ArrayList<>();
    public void visit(NumberToken token) {
        stack.add(token.getNumber());
    }
    public void visit(Brace token) {
    }
    public void visit(Operation token) {
        if (stack.size() < 2) {
            throw new RuntimeException("Not enough arguments.");
        }
        int arg1 = stack.get(stack.size() - 2);
        int arg2 = stack.get(stack.size() - 1);

        stack.remove(stack.size() - 1);
        stack.remove(stack.size() - 1);

        switch (token.getOperationType()) {
            case ADDITION: {
                stack.add(arg1 + arg2);
                break;
            }
            case SUBTRACTION: {
                stack.add(arg1 - arg2);
                break;
            }
            case MULTIPLICATION: {
                stack.add(arg1 * arg2);
                break;
            }
            case DIVISION: {
                if (arg2 == 0) {
                    throw new RuntimeException("Division by zero.");
                }
                stack.add(arg1 / arg2);
                break;
            }
        }
    }
    public int extractResult() {
        if (stack.isEmpty()) {
            throw new RuntimeException("No result.");
        }
        if (stack.size() > 1) {
            throw new RuntimeException("Expression is not completed.");
        }
        return stack.get(0);
    }
}
