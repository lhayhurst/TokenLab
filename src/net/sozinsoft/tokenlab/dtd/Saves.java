
package net.sozinsoft.tokenlab.dtd;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "save"
})
@XmlRootElement(name = "saves")
public class Saves {

    @XmlElement(required = true)
    protected List<Save> save;

    /**
     * Gets the value of the save property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the save property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSave().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Save }
     * 
     * 
     */
    public List<Save> getSave() {
        if (save == null) {
            save = new ArrayList<Save>();
        }
        return this.save;
    }

}
