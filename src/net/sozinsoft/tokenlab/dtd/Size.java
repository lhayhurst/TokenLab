
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
    "space",
    "reach"
})
@XmlRootElement(name = "size")
public class Size {

    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String name;
    @XmlElement(required = true)
    protected Space space;
    @XmlElement(required = true)
    protected Reach reach;

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
     * Gets the value of the space property.
     * 
     * @return
     *     possible object is
     *     {@link Space }
     *     
     */
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
    public void setReach(Reach value) {
        this.reach = value;
    }

}
