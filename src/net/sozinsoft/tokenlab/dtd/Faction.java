
package net.sozinsoft.tokenlab.dtd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "faction")
public class Faction {

    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String name;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String tpa;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String cpa;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String retired;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
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
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the tpa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpa() {
        return tpa;
    }

    /**
     * Sets the value of the tpa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpa(String value) {
        this.tpa = value;
    }

    /**
     * Gets the value of the cpa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpa() {
        return cpa;
    }

    /**
     * Sets the value of the cpa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpa(String value) {
        this.cpa = value;
    }

    /**
     * Gets the value of the retired property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRetired() {
        if (retired == null) {
            return "no";
        } else {
            return retired;
        }
    }

    /**
     * Sets the value of the retired property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRetired(String value) {
        this.retired = value;
    }

}
