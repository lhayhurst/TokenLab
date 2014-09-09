
package net.sozinsoft.tokenlab.dtd;

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
    "situationalmodifiers"
})
@XmlRootElement(name = "save")
public class Save {

    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String name;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String abbr;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String save;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String base;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String fromattr;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String frommisc;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String fromresist;
    @XmlElement(required = true)
    protected Situationalmodifiers situationalmodifiers;

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
     * Gets the value of the abbr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbbr() {
        return abbr;
    }

    /**
     * Sets the value of the abbr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbbr(String value) {
        this.abbr = value;
    }

    /**
     * Gets the value of the save property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSave() {
        return save;
    }

    /**
     * Sets the value of the save property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSave(String value) {
        this.save = value;
    }

    /**
     * Gets the value of the base property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBase() {
        return base;
    }

    /**
     * Sets the value of the base property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBase(String value) {
        this.base = value;
    }

    /**
     * Gets the value of the fromattr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromattr() {
        return fromattr;
    }

    /**
     * Sets the value of the fromattr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromattr(String value) {
        this.fromattr = value;
    }

    /**
     * Gets the value of the frommisc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrommisc() {
        return frommisc;
    }

    /**
     * Sets the value of the frommisc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrommisc(String value) {
        this.frommisc = value;
    }

    /**
     * Gets the value of the fromresist property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromresist() {
        return fromresist;
    }

    /**
     * Sets the value of the fromresist property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromresist(String value) {
        this.fromresist = value;
    }

    /**
     * Gets the value of the situationalmodifiers property.
     * 
     * @return
     *     possible object is
     *     {@link Situationalmodifiers }
     *     
     */
    public Situationalmodifiers getSituationalmodifiers() {
        return situationalmodifiers;
    }

    /**
     * Sets the value of the situationalmodifiers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Situationalmodifiers }
     *     
     */
    public void setSituationalmodifiers(Situationalmodifiers value) {
        this.situationalmodifiers = value;
    }

}
