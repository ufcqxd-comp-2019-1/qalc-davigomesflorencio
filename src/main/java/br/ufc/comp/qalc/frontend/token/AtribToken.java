package br.ufc.comp.qalc.frontend.token;

public class AtribToken extends Token{

    protected char Simbol='=';

    public AtribToken(long line, long start, String value) throws IllegalArgumentException {
        super(line, start, value);
    }

    @Override
    public void interpretAttributes() {
    }

    @Override
    public String getTokenIdentifier() {
        return "ATRIB";
    }
}

