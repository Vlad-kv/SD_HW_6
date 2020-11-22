package Tokens;

import Visitors.TokenVisitor;

public class Operation implements Token {
    enum OperationType {ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION};

    private final OperationType operationType;

    public Operation(OperationType operationType) {
        this.operationType = operationType;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}
