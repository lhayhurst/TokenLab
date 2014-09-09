
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
    "weapon"
})
@XmlRootElement(name = "ranged")
public class Ranged {

    protected List<Weapon> weapon;

    /**
     * Gets the value of the weapon property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the weapon property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWeapon().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Weapon }
     * 
     * 
     */
    public List<Weapon> getWeapon() {
        if (weapon == null) {
            weapon = new ArrayList<Weapon>();
        }
        return this.weapon;
    }

}
