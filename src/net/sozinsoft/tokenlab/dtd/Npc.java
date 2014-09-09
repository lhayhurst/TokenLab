
package net.sozinsoft.tokenlab.dtd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "description",
    "basics",
    "tactics",
    "ecology",
    "additional"
})
@XmlRootElement(name = "npc")
public class Npc {

    @XmlElement(required = true)
    protected String description;
    @XmlElement(required = true)
    protected Basics basics;
    @XmlElement(required = true)
    protected Tactics tactics;
    @XmlElement(required = true)
    protected Ecology ecology;
    @XmlElement(required = true)
    protected Additional additional;

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
     * Gets the value of the basics property.
     * 
     * @return
     *     possible object is
     *     {@link Basics }
     *     
     */
    public Basics getBasics() {
        return basics;
    }

    /**
     * Sets the value of the basics property.
     * 
     * @param value
     *     allowed object is
     *     {@link Basics }
     *     
     */
    public void setBasics(Basics value) {
        this.basics = value;
    }

    /**
     * Gets the value of the tactics property.
     * 
     * @return
     *     possible object is
     *     {@link Tactics }
     *     
     */
    public Tactics getTactics() {
        return tactics;
    }

    /**
     * Sets the value of the tactics property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tactics }
     *     
     */
    public void setTactics(Tactics value) {
        this.tactics = value;
    }

    /**
     * Gets the value of the ecology property.
     * 
     * @return
     *     possible object is
     *     {@link Ecology }
     *     
     */
    public Ecology getEcology() {
        return ecology;
    }

    /**
     * Sets the value of the ecology property.
     * 
     * @param value
     *     allowed object is
     *     {@link Ecology }
     *     
     */
    public void setEcology(Ecology value) {
        this.ecology = value;
    }

    /**
     * Gets the value of the additional property.
     * 
     * @return
     *     possible object is
     *     {@link Additional }
     *     
     */
    public Additional getAdditional() {
        return additional;
    }

    /**
     * Sets the value of the additional property.
     * 
     * @param value
     *     allowed object is
     *     {@link Additional }
     *     
     */
    public void setAdditional(Additional value) {
        this.additional = value;
    }

}
