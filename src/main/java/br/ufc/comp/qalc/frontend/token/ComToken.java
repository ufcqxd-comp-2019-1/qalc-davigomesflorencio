package br.ufc.comp.qalc.frontend.token;

public class ComToken extends Token{

    public ComToken(long line, long start, String value) throws IllegalArgumentException {
        super(line, start, value);
    }

    @Override
    public void interpretAttributes() {
        if(stringValue !=null  && stringValue.charAt(0) == '#'){
            stringValue= stringValue.substring(1);
        }
    }

    @Override
    public String getTokenIdentifier() {
        return "COM";
    }
}
