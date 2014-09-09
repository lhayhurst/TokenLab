
package net.sozinsoft.tokenlab.dtd;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "spell"
})
@XmlRootElement(name = "spellbook")
public class Spellbook {

    protected List<Spell> spell;

    /**
     * Gets the value of the spell property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the spell property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpell().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Spell }
     * 
     * 
     */
    public List<Spell> getSpell() {
        if (spell == null) {
            spell = new ArrayList<Spell>();
        }
        return this.spell;
    }

}
