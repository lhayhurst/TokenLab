
package net.sozinsoft.tokenlab.d20_dtd;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "initiative")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
public class Initiative {

    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String total;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String attrtext;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String misctext;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String attrname;

    /**
     * Gets the value of the total property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public String getTotal() {
        return total;
    }

    /**
     * Sets the value of the total property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setTotal(String value) {
        this.total = value;
    }

    /**
     * Gets the value of the attrtext property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public String getAttrtext() {
        return attrtext;
    }

    /**
     * Sets the value of the attrtext property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setAttrtext(String value) {
        this.attrtext = value;
    }

    /**
     * Gets the value of the misctext property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public String getMisctext() {
        return misctext;
    }

    /**
     * Sets the value of the misctext property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setMisctext(String value) {
        this.misctext = value;
    }

    /**
     * Gets the value of the attrname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public String getAttrname() {
        return attrname;
    }

    /**
     * Sets the value of the attrname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setAttrname(String value) {
        this.attrname = value;
    }

}
