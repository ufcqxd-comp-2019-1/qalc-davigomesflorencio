package br.ufc.comp.qalc.frontend;

import br.ufc.comp.qalc.frontend.token.*;

import java.io.IOException;
import java.util.Arrays;

/**
 * Analisador Léxico da linguagem.
 * <p>
 * Funciona como uma fonte de tokens, de onde o Analisador Sintático
 * deverá ler.
 *
 * @see Source
 */
public class Scanner {

    /**
     * Último token reconhecido.
     */
    protected Token currentToken;
    /**
     * Fonte de caracteres de onde extrair os tokens.
     */
    protected Source source;

    /**
     * Constrói um Analisador Léxico a partir de uma fonte de caracteres.
     *
     * @param sourceStream Fonte a ser utilizada.
     */
    public Scanner(Source sourceStream) {
        // FIXME Lidar corretamente com as exceções.
        this.source = sourceStream;
    }

    /**
     * Consome caracteres da fonte até que eles componham um lexema de
     * um dos tokens da linguagem, coinstrói um objeto representando esse
     * token associado ao lexema lido e o retorna.
     *
     * @return Token reconhecido.
     * @throws IOException Caso haja problema na leitura da fonte.
     */
    public Token getNextToken() throws IOException {
        // TODO Reconhecimento de tokens

        if (source.getCurrentChar() == Source.EOF) {
            return new EOFToken(source.getCurrentLine(), source.getCurrentColumn());

        } else if (Character.isDigit(source.getCurrentChar())) { // NumberToken
            StringBuilder lexema = new StringBuilder();

            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();

            do {
                lexema.append(source.getCurrentChar());
                source.advance();
            } while (Character.isDigit(source.getCurrentChar()));

            return new NumberToken(currentLine, lexemeStart,lexema.toString());

        }else if(source.getCurrentChar()=='$') {
            //VariavelToken , ResidToken
            StringBuilder lexema = new StringBuilder();
            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();
            lexema.append(source.getCurrentChar());
            source.advance();

            if(Character.isLetter(source.getCurrentChar())){
                do{
                    lexema.append(source.getCurrentChar());
                    source.advance();
                }while (Character.isLetter(source.getCurrentChar()));
                return new VariableIdentifierToken(currentLine,lexemeStart,lexema.toString());

            }else if(source.getCurrentChar()=='?' && lexema.length()==1) {
                lexema.append(source.getCurrentChar());
                return new ResidToken(currentLine,lexemeStart,lexema.toString());

            }else if(Character.isDigit(source.getCurrentChar())) {
                do{
                    lexema.append(source.getCurrentChar());
                    source.advance();
                } while (Character.isDigit(source.getCurrentChar()));
                if (lexema.charAt(lexema.length()-1) != '0'){
                    return new ResidToken(currentLine, lexemeStart,lexema.toString());
                }
            }

        }else if(Arrays.asList('=','-','*','/','%','^','+').contains(source.getCurrentChar())){
            //Operators
            StringBuilder lexema = new StringBuilder();

            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();
            lexema.append(source.getCurrentChar());

            switch (source.getCurrentChar()){
                case '+':
                    return new PlusToken(currentLine,lexemeStart,lexema.toString());
                case '=':
                    return new AtribToken(currentLine,lexemeStart,lexema.toString());
                case '-':
                    return new MinusToken(currentLine,lexemeStart,lexema.toString());
                case '*':
                    return new TimesToken(currentLine,lexemeStart,lexema.toString());
                case '/':
                    return new DivToken(currentLine,lexemeStart,lexema.toString());
                case '%':
                    return new ModToken(currentLine,lexemeStart,lexema.toString());
                case '^':
                    return new PowToken(currentLine,lexemeStart,lexema.toString());
            }
        }else if(source.getCurrentChar()=='@'){
            //FunctionToken
            StringBuilder lexema = new StringBuilder();
            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();

            do{
                lexema.append(source.getCurrentChar());
                source.advance();
            }while(Character.isLetterOrDigit(source.getCurrentChar()));
            return new FunctionToken(currentLine,lexemeStart,lexema.toString());
        }else if(source.getCurrentChar()=='(' || source.getCurrentChar()==')'){
            //Delimitadores
            StringBuilder lexema = new StringBuilder();
            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();
            lexema.append(source.getCurrentChar());
            return source.getCurrentChar()=='('?new LparenToken(currentLine,lexemeStart,lexema.toString()):new RparenToken(currentLine,lexemeStart,lexema.toString());
        }else if(source.getCurrentChar()==','){
            //CommaToken
            StringBuilder lexema = new StringBuilder();
            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();
            lexema.append(source.getCurrentChar());
            return new CommaToken(currentLine,lexemeStart,lexema.toString());
        }else if(source.getCurrentChar()==';'){
            //SemiToken
            StringBuilder lexema = new StringBuilder();
            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();
            lexema.append(source.getCurrentChar());
            return new SemiToken(currentLine,lexemeStart,lexema.toString());
        }

        // TODO Recuperação de erros.

        return null;
    }

    /**
     * Obtém o último token reconhecido.
     *
     * @return O último token reconhecido.
     */
    public Token getCurrentToken() {
        return currentToken;
    }
}
