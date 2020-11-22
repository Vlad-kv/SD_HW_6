package tokenizer;

import Tokens.NumberToken;

public class ReadingNumberState extends State {
    private final StringBuilder builder;

    ReadingNumberState(Tokenizer tokenizer, char firstDigit) {
        super(tokenizer);
        builder = new StringBuilder();
        builder.append(firstDigit);
    }

    @Override
    public void processChar(char c) {
        if (Character.isDigit(c)) {
            builder.append(c);
        } else {
            tokenizer.addToken(new NumberToken(Integer.parseInt(builder.toString())));
            tokenizer.setState(new ReadingAnyTokenState(tokenizer, c));
        }
    }
}
