package tokenizer;

import Tokens.Brace;
import Tokens.Operation;

public class ReadingAnyTokenState extends State {
    public ReadingAnyTokenState(Tokenizer tokenizer) {
        super(tokenizer);
    }

    public ReadingAnyTokenState(Tokenizer tokenizer, char c) {
        super(tokenizer);
        processChar(c);
    }

    @Override
    public void processChar(char c) {
        if (Character.isDigit(c)) {
            tokenizer.setState(new ReadingNumberState(tokenizer, c));
            return;
        }
        if (Character.isSpaceChar(c)) {
            return;
        }
        switch (c) {
            case (char) -1 :
                return;
            case '(' : {
                tokenizer.addToken(new Brace(Brace.BraceType.OPENING));
                break;
            }
            case ')' : {
                tokenizer.addToken(new Brace(Brace.BraceType.CLOSING));
                break;
            }
            case '+' : {
                tokenizer.addToken(new Operation(Operation.OperationType.ADDITION));
                break;
            }
            case '-' : {
                tokenizer.addToken(new Operation(Operation.OperationType.SUBTRACTION));
                break;
            }
            case '*' : {
                tokenizer.addToken(new Operation(Operation.OperationType.MULTIPLICATION));
                break;
            }
            case '/' : {
                tokenizer.addToken(new Operation(Operation.OperationType.DIVISION));
                break;
            }
            default:
                throw new RuntimeException("Unknown symbol \"" + c + "\" with code " + ((int) c) + ".");
        }
    }
}
