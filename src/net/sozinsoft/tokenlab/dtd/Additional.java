
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
    "npcinfo"
})
@XmlRootElement(name = "additional")
public class Additional {

    protected List<Npcinfo> npcinfo;

    /**
     * Gets the value of the npcinfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the npcinfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNpcinfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Npcinfo }
     * 
     * 
     */
    public List<Npcinfo> getNpcinfo() {
        if (npcinfo == null) {
            npcinfo = new ArrayList<Npcinfo>();
        }
        return this.npcinfo;
    }

}
