
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
    "skilltrick"
})
@XmlRootElement(name = "skilltricks")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
public class Skilltricks {

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected List<Skilltrick> skilltrick;

    /**
     * Gets the value of the skilltrick property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the skilltrick property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSkilltrick().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Skilltrick }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public List<Skilltrick> getSkilltrick() {
        if (skilltrick == null) {
            skilltrick = new ArrayList<Skilltrick>();
        }
        return this.skilltrick;
    }

}
