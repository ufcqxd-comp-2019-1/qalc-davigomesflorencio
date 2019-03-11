package br.ufc.comp.qalc.frontend.token;

public class PlusToken extends Token {

    protected char Simbol='+';

    public PlusToken(long line, long start, String value) throws IllegalArgumentException {
        super(line, start, value);
    }

    @Override
    public void interpretAttributes() {
    }

    @Override
    public String getTokenIdentifier() {
        return "PLUS";
    }
}
