import Tokens.Token;
import Visitors.ParserVisitor;
import Visitors.PrintVisitor;
import tokenizer.Tokenizer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        try {
            Tokenizer tokenizer = new Tokenizer();
            ArrayList<Token> tokens = tokenizer.getTokens(new ByteArrayInputStream(input.getBytes()));
            ParserVisitor parserVisitor = new ParserVisitor();
            for (Token token : tokens) {
                token.accept(parserVisitor);
            }
            ArrayList<Token> rpn = parserVisitor.getResult();
            PrintVisitor printVisitor = new PrintVisitor();
            for (Token token : rpn) {
                token.accept(printVisitor);
            }
            System.out.println();
        } catch (IOException | RuntimeException ex) {
            System.out.println(ex);
        }
    }
}
