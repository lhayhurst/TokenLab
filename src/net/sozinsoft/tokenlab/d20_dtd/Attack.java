
package net.sozinsoft.tokenlab.d20_dtd;

import java.util.ArrayList;
import java.util.List;
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
@XmlType(name = "", propOrder = {
    "special"
})
@XmlRootElement(name = "attack")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
public class Attack {

    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String attackbonus;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String baseattack;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String meleeattack;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String rangedattack;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected List<Special> special;

    /**
     * Gets the value of the attackbonus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public String getAttackbonus() {
        return attackbonus;
    }

    /**
     * Sets the value of the attackbonus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setAttackbonus(String value) {
        this.attackbonus = value;
    }

    /**
     * Gets the value of the baseattack property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public String getBaseattack() {
        return baseattack;
    }

    /**
     * Sets the value of the baseattack property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setBaseattack(String value) {
        this.baseattack = value;
    }

    /**
     * Gets the value of the meleeattack property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public String getMeleeattack() {
        return meleeattack;
    }

    /**
     * Sets the value of the meleeattack property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setMeleeattack(String value) {
        this.meleeattack = value;
    }

    /**
     * Gets the value of the rangedattack property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public String getRangedattack() {
        return rangedattack;
    }

    /**
     * Sets the value of the rangedattack property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setRangedattack(String value) {
        this.rangedattack = value;
    }

    /**
     * Gets the value of the special property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the special property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpecial().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Special }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-12-05T10:28:09-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public List<Special> getSpecial() {
        if (special == null) {
            special = new ArrayList<Special>();
        }
        return this.special;
    }

}
