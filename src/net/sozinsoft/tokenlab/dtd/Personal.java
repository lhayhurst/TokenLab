
package net.sozinsoft.tokenlab.dtd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "description",
    "charheight",
    "charweight"
})
@XmlRootElement(name = "personal")
public class Personal {

    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String age;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String eyes;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String gender;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String hair;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String skin;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(required = true)
    protected Charheight charheight;
    @XmlElement(required = true)
    protected Charweight charweight;

    /**
     * Gets the value of the age property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAge() {
        return age;
    }

    /**
     * Sets the value of the age property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAge(String value) {
        this.age = value;
    }

    /**
     * Gets the value of the eyes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEyes() {
        return eyes;
    }

    /**
     * Sets the value of the eyes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEyes(String value) {
        this.eyes = value;
    }

    /**
     * Gets the value of the gender property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGender(String value) {
        this.gender = value;
    }

    /**
     * Gets the value of the hair property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHair() {
        return hair;
    }

    /**
     * Sets the value of the hair property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHair(String value) {
        this.hair = value;
    }

    /**
     * Gets the value of the skin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSkin() {
        return skin;
    }

    /**
     * Sets the value of the skin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSkin(String value) {
        this.skin = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the charheight property.
     * 
     * @return
     *     possible object is
     *     {@link Charheight }
     *     
     */
    public Charheight getCharheight() {
        return charheight;
    }

    /**
     * Sets the value of the charheight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Charheight }
     *     
     */
    public void setCharheight(Charheight value) {
        this.charheight = value;
    }

    /**
     * Gets the value of the charweight property.
     * 
     * @return
     *     possible object is
     *     {@link Charweight }
     *     
     */
    public Charweight getCharweight() {
        return charweight;
    }

    /**
     * Sets the value of the charweight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Charweight }
     *     
     */
    public void setCharweight(Charweight value) {
        this.charweight = value;
    }

}
