
package net.sozinsoft.tokenlab.d20_dtd;

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
    "space",
    "reach"
})
@XmlRootElement(name = "size")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
public class Size {

    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String name;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Space space;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Reach reach;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the space property.
     * 
     * @return
     *     possible object is
     *     {@link Space }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Space getSpace() {
        return space;
    }

    /**
     * Sets the value of the space property.
     * 
     * @param value
     *     allowed object is
     *     {@link Space }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSpace(Space value) {
        this.space = value;
    }

    /**
     * Gets the value of the reach property.
     * 
     * @return
     *     possible object is
     *     {@link Reach }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Reach getReach() {
        return reach;
    }

    /**
     * Sets the value of the reach property.
     * 
     * @param value
     *     allowed object is
     *     {@link Reach }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setReach(Reach value) {
        this.reach = value;
    }

}
