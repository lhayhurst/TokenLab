
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
@XmlRootElement(name = "spelllevel")
public class Spelllevel {

    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String level;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String maxcasts;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String used;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String unlimited;

    /**
     * Gets the value of the level property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLevel() {
        return level;
    }

    /**
     * Sets the value of the level property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLevel(String value) {
        this.level = value;
    }

    /**
     * Gets the value of the maxcasts property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxcasts() {
        if (maxcasts == null) {
            return "0";
        } else {
            return maxcasts;
        }
    }

    /**
     * Sets the value of the maxcasts property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxcasts(String value) {
        this.maxcasts = value;
    }

    /**
     * Gets the value of the used property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsed() {
        if (used == null) {
            return "0";
        } else {
            return used;
        }
    }

    /**
     * Sets the value of the used property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsed(String value) {
        this.used = value;
    }

    /**
     * Gets the value of the unlimited property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnlimited() {
        if (unlimited == null) {
            return "no";
        } else {
            return unlimited;
        }
    }

    /**
     * Sets the value of the unlimited property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnlimited(String value) {
        this.unlimited = value;
    }

}
