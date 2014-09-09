
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
    "faction"
})
@XmlRootElement(name = "factions")
public class Factions {

    protected List<Faction> faction;

    /**
     * Gets the value of the faction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the faction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFaction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Faction }
     * 
     * 
     */
    public List<Faction> getFaction() {
        if (faction == null) {
            faction = new ArrayList<Faction>();
        }
        return this.faction;
    }

}
