package net.sozinsoft.tokenlab;


import net.sozinsoft.tokenlab.dtd.Spell;

import java.io.IOException;
import java.util.*;

public class ClassSpells {

    private static SpellDB _pfSRDSpells;

    static {
        try {
            _pfSRDSpells = new SpellDB( ResourceManager.getSpells().getAbsolutePath() );
        }
        catch ( IOException ioe ) {
            ; //TODO: how to handle properly?
        }
    }

    private SortedMap<Integer, SpellsByLevel> _spellsByLevel = new TreeMap<Integer, SpellsByLevel>();
    private String className;

    public ClassSpells( String className ) {
        this.className = className;
    }

    public void addSpell( Spell s ) {
        PFSRDSpell pfsrdSpell =  _pfSRDSpells.getSpell( s.getName() );
        if ( s == null || pfsrdSpell == null ) {
            return;  //TODO: has to be a better way to react to this.
        }
        pfsrdSpell.spellDC = s.getDc() != null && s.getDc().length() > 0 ? s.getDc() : "";
        pfsrdSpell.casterLevel = s.getCasterlevel() != null && s.getCasterlevel().length() > 0 ? s.getCasterlevel() : "";
        SpellsByLevel sbl = getLevel( Integer.parseInt( s.getLevel() ) );
        sbl.addSpell( pfsrdSpell );

    }

    public SpellsByLevel getLevel( Integer level ) {
        SpellsByLevel spellByLevel = null;
        if ( ! _spellsByLevel.containsKey(level)) {
            spellByLevel = new SpellsByLevel( level );
            _spellsByLevel.put(level, spellByLevel);
        }
        else {
            spellByLevel = _spellsByLevel.get( level );
        }
        return spellByLevel;
    }

    public HashMap<String, PFSRDSpell> getAllSpells() {
        HashMap<String, PFSRDSpell> ret = new HashMap<String, PFSRDSpell>();
        for( SpellsByLevel sbl : _spellsByLevel.values()) {
            for( PFSRDSpell spell : sbl.getSpells() ) {
                ret.put( spell.name, spell );
            }
        }
        return ret;
    }

    public SortedMap<Integer, HashMap<String, PFSRDSpell>> getAllSpellsByLevel() {
        SortedMap<Integer, HashMap<String, PFSRDSpell>> ret = new TreeMap<Integer, HashMap<String, PFSRDSpell>>();
        for( Integer level : _spellsByLevel.keySet()) {
            SpellsByLevel sbl = _spellsByLevel.get( level );
            HashMap<String, PFSRDSpell> levelSpells = new HashMap<String, PFSRDSpell>();
            for( PFSRDSpell spell : sbl.getSpells() ) {
                levelSpells.put( spell.name, spell  );
            }
            ret.put( level, levelSpells);
        }
        return ret;
    }


    public class SpellsByLevel {
        private HashMap<String, PFSRDSpell > _spells = new HashMap<String, PFSRDSpell>();
        private Integer level;

        public SpellsByLevel( Integer level ) {
            this.level = level;
        }

        public void addSpell(PFSRDSpell spell ) {
            _spells.put( spell.name, spell );
        }

        public Integer getLevel() {
            return level;
        }

        public Collection<PFSRDSpell> getSpells() {
            return _spells.values();
        }



    }


}
