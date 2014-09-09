
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
    "armor"
})
@XmlRootElement(name = "defenses")
public class Defenses {

    protected List<Armor> armor;

    /**
     * Gets the value of the armor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the armor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArmor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Armor }
     * 
     * 
     */
    public List<Armor> getArmor() {
        if (armor == null) {
            armor = new ArrayList<Armor>();
        }
        return this.armor;
    }

}
