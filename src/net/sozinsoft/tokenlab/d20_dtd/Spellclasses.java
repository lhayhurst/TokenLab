
package net.sozinsoft.tokenlab.d20_dtd;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "spellclass"
})
@XmlRootElement(name = "spellclasses")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
public class Spellclasses {

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected List<Spellclass> spellclass;

    /**
     * Gets the value of the spellclass property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the spellclass property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpellclass().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Spellclass }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public List<Spellclass> getSpellclass() {
        if (spellclass == null) {
            spellclass = new ArrayList<Spellclass>();
        }
        return this.spellclass;
    }

}
