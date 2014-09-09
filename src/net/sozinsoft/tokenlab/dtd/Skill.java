
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
    "situationalmodifiers"
})
@XmlRootElement(name = "skill")
public class Skill {

    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String name;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String value;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String ranks;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String attrbonus;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String attrname;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String tools;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String armorcheck;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String classskill;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String trainedonly;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String usable;
    @XmlElement(required = true)
    protected String description;
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
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the ranks property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRanks() {
        return ranks;
    }

    /**
     * Sets the value of the ranks property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRanks(String value) {
        this.ranks = value;
    }

    /**
     * Gets the value of the attrbonus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttrbonus() {
        return attrbonus;
    }

    /**
     * Sets the value of the attrbonus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttrbonus(String value) {
        this.attrbonus = value;
    }

    /**
     * Gets the value of the attrname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
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
    public void setAttrname(String value) {
        this.attrname = value;
    }

    /**
     * Gets the value of the tools property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTools() {
        return tools;
    }

    /**
     * Sets the value of the tools property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTools(String value) {
        this.tools = value;
    }

    /**
     * Gets the value of the armorcheck property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArmorcheck() {
        if (armorcheck == null) {
            return "no";
        } else {
            return armorcheck;
        }
    }

    /**
     * Sets the value of the armorcheck property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArmorcheck(String value) {
        this.armorcheck = value;
    }

    /**
     * Gets the value of the classskill property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassskill() {
        if (classskill == null) {
            return "no";
        } else {
            return classskill;
        }
    }

    /**
     * Sets the value of the classskill property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassskill(String value) {
        this.classskill = value;
    }

    /**
     * Gets the value of the trainedonly property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrainedonly() {
        if (trainedonly == null) {
            return "no";
        } else {
            return trainedonly;
        }
    }

    /**
     * Sets the value of the trainedonly property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrainedonly(String value) {
        this.trainedonly = value;
    }

    /**
     * Gets the value of the usable property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsable() {
        if (usable == null) {
            return "no";
        } else {
            return usable;
        }
    }

    /**
     * Sets the value of the usable property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsable(String value) {
        this.usable = value;
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
