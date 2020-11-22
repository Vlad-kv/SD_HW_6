package tokenizer;

public abstract class State {
    protected final Tokenizer tokenizer;
    public State(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public abstract void processChar(char c);
}
