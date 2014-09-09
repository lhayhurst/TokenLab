
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
    "trait"
})
@XmlRootElement(name = "traits")
public class Traits {

    protected List<Trait> trait;

    /**
     * Gets the value of the trait property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trait property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrait().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Trait }
     * 
     * 
     */
    public List<Trait> getTrait() {
        if (trait == null) {
            trait = new ArrayList<Trait>();
        }
        return this.trait;
    }

}
