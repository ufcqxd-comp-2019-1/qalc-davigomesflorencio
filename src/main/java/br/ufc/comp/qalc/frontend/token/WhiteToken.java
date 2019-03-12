package br.ufc.comp.qalc.frontend.token;

public class WhiteToken extends Token {
    public WhiteToken(long line, long start, String value) throws IllegalArgumentException {
        super(line, start, value);
    }

    @Override
    public void interpretAttributes() {
        if(stringValue!=null){
            stringValue=" ";
        }
    }

    @Override
    public String getTokenIdentifier() {
        return "WHITE";
    }
}
