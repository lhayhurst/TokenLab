package net.sozinsoft.tokenlab;


import org.omg.CORBA.PRIVATE_MEMBER;

public class DefaultReplacer implements IMacroReplacer {
    private static final String SELF_ONLY = "$SELF";
    private static final String SELF_MACRO_TEXT = "/self";
    private boolean isNPC ;

    public DefaultReplacer( boolean isNPC ) {
        this.isNPC = isNPC;
    }
    public String replace( String target ) {
        if ( isNPC ) {
            target = target.replace( SELF_ONLY, SELF_MACRO_TEXT );
        }
        else {
            target = target.replace( SELF_ONLY, "" );
        }
        return target;
    }
}
