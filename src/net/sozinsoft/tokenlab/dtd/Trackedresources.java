
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
    "trackedresource"
})
@XmlRootElement(name = "trackedresources")
public class Trackedresources {

    protected List<Trackedresource> trackedresource;

    /**
     * Gets the value of the trackedresource property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trackedresource property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrackedresource().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Trackedresource }
     * 
     * 
     */
    public List<Trackedresource> getTrackedresource() {
        if (trackedresource == null) {
            trackedresource = new ArrayList<Trackedresource>();
        }
        return this.trackedresource;
    }

}
