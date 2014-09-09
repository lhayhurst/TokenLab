
package net.sozinsoft.tokenlab.dtd;

import java.util.ArrayList;
import java.util.List;
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
@XmlRootElement(name = "health")
public class Health {

    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String currenthp;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String damage;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String hitdice;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String hitpoints;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String nonlethal;
    protected List<Special> special;

    /**
     * Gets the value of the currenthp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrenthp() {
        return currenthp;
    }

    /**
     * Sets the value of the currenthp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrenthp(String value) {
        this.currenthp = value;
    }

    /**
     * Gets the value of the damage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDamage() {
        return damage;
    }

    /**
     * Sets the value of the damage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDamage(String value) {
        this.damage = value;
    }

    /**
     * Gets the value of the hitdice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHitdice() {
        return hitdice;
    }

    /**
     * Sets the value of the hitdice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHitdice(String value) {
        this.hitdice = value;
    }

    /**
     * Gets the value of the hitpoints property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHitpoints() {
        return hitpoints;
    }

    /**
     * Sets the value of the hitpoints property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHitpoints(String value) {
        this.hitpoints = value;
    }

    /**
     * Gets the value of the nonlethal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNonlethal() {
        return nonlethal;
    }

    /**
     * Sets the value of the nonlethal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNonlethal(String value) {
        this.nonlethal = value;
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
    public List<Special> getSpecial() {
        if (special == null) {
            special = new ArrayList<Special>();
        }
        return this.special;
    }

}
