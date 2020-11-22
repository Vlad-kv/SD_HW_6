package tokenizer;

import Tokens.Token;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Tokenizer {
    private ArrayList<Token> tokens;
    private State state = new ReadingAnyTokenState(this);

    public Tokenizer() {
    }

    void setState(State state) {
        this.state = state;
    }

    void addToken(Token token) {
        tokens.add(token);
    }

    public ArrayList<Token> getTokens(InputStream in) throws IOException {
        int c;
        tokens = new ArrayList<>();
        while ((c = in.read()) > 0) {
            state.processChar((char) c);
        }
        state.processChar((char) -1);

        ArrayList<Token> res = tokens;
        tokens = null;
        return res;
    }
}
