
package net.sozinsoft.tokenlab.dtd;

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
    "favoredclass"
})
@XmlRootElement(name = "favoredclasses")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-11-15T04:01:32-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
public class Favoredclasses {

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-11-15T04:01:32-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected List<Favoredclass> favoredclass;

    /**
     * Gets the value of the favoredclass property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the favoredclass property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFavoredclass().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Favoredclass }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-11-15T04:01:32-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public List<Favoredclass> getFavoredclass() {
        if (favoredclass == null) {
            favoredclass = new ArrayList<Favoredclass>();
        }
        return this.favoredclass;
    }

}
