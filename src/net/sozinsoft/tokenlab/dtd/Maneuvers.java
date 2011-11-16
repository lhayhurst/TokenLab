
package net.sozinsoft.tokenlab.dtd;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "maneuvertype"
})
@XmlRootElement(name = "maneuvers")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-11-15T04:01:32-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
public class Maneuvers {

    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-11-15T04:01:32-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String cmb;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-11-15T04:01:32-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String cmd;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-11-15T04:01:32-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String cmdflatfooted;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-11-15T04:01:32-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected List<Maneuvertype> maneuvertype;

    /**
     * Gets the value of the cmb property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-11-15T04:01:32-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public String getCmb() {
        return cmb;
    }

    /**
     * Sets the value of the cmb property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-11-15T04:01:32-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setCmb(String value) {
        this.cmb = value;
    }

    /**
     * Gets the value of the cmd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-11-15T04:01:32-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public String getCmd() {
        return cmd;
    }

    /**
     * Sets the value of the cmd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-11-15T04:01:32-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setCmd(String value) {
        this.cmd = value;
    }

    /**
     * Gets the value of the cmdflatfooted property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-11-15T04:01:32-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public String getCmdflatfooted() {
        return cmdflatfooted;
    }

    /**
     * Sets the value of the cmdflatfooted property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-11-15T04:01:32-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setCmdflatfooted(String value) {
        this.cmdflatfooted = value;
    }

    /**
     * Gets the value of the maneuvertype property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the maneuvertype property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getManeuvertype().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Maneuvertype }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-11-15T04:01:32-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public List<Maneuvertype> getManeuvertype() {
        if (maneuvertype == null) {
            maneuvertype = new ArrayList<Maneuvertype>();
        }
        return this.maneuvertype;
    }

}
