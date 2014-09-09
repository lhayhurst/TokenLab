
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
    "description"
})
@XmlRootElement(name = "itempower")
public class Itempower {

    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String name;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String pricebonusvalue;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String pricebonustext;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String pricecashvalue;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String pricecashtext;
    @XmlElement(required = true)
    protected String description;

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
     * Gets the value of the pricebonusvalue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPricebonusvalue() {
        return pricebonusvalue;
    }

    /**
     * Sets the value of the pricebonusvalue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricebonusvalue(String value) {
        this.pricebonusvalue = value;
    }

    /**
     * Gets the value of the pricebonustext property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPricebonustext() {
        return pricebonustext;
    }

    /**
     * Sets the value of the pricebonustext property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricebonustext(String value) {
        this.pricebonustext = value;
    }

    /**
     * Gets the value of the pricecashvalue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPricecashvalue() {
        return pricecashvalue;
    }

    /**
     * Sets the value of the pricecashvalue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricecashvalue(String value) {
        this.pricecashvalue = value;
    }

    /**
     * Gets the value of the pricecashtext property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPricecashtext() {
        return pricecashtext;
    }

    /**
     * Sets the value of the pricecashtext property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricecashtext(String value) {
        this.pricecashtext = value;
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

}
