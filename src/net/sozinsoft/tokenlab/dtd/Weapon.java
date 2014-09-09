
package net.sozinsoft.tokenlab.dtd;

import java.util.ArrayList;
import java.util.List;
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
    "rangedattack",
    "weight",
    "cost",
    "description",
    "itemslot",
    "itempower",
    "wepcategory",
    "weptype",
    "situationalmodifiers"
})
@XmlRootElement(name = "weapon")
public class Weapon {

    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String name;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String attack;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String damage;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String crit;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String categorytext;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String typetext;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String size;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String flurryattack;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String equipped;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String useradded;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String quantity;
    protected Rangedattack rangedattack;
    protected Weight weight;
    protected Cost cost;
    @XmlElement(required = true)
    protected String description;
    protected String itemslot;
    protected List<Itempower> itempower;
    protected List<Wepcategory> wepcategory;
    protected List<Weptype> weptype;
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
     * Gets the value of the attack property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttack() {
        return attack;
    }

    /**
     * Sets the value of the attack property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttack(String value) {
        this.attack = value;
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
     * Gets the value of the crit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrit() {
        return crit;
    }

    /**
     * Sets the value of the crit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrit(String value) {
        this.crit = value;
    }

    /**
     * Gets the value of the categorytext property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategorytext() {
        return categorytext;
    }

    /**
     * Sets the value of the categorytext property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategorytext(String value) {
        this.categorytext = value;
    }

    /**
     * Gets the value of the typetext property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypetext() {
        return typetext;
    }

    /**
     * Sets the value of the typetext property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypetext(String value) {
        this.typetext = value;
    }

    /**
     * Gets the value of the size property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets the value of the size property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSize(String value) {
        this.size = value;
    }

    /**
     * Gets the value of the flurryattack property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlurryattack() {
        return flurryattack;
    }

    /**
     * Sets the value of the flurryattack property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlurryattack(String value) {
        this.flurryattack = value;
    }

    /**
     * Gets the value of the equipped property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEquipped() {
        if (equipped == null) {
            return "no";
        } else {
            return equipped;
        }
    }

    /**
     * Sets the value of the equipped property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEquipped(String value) {
        this.equipped = value;
    }

    /**
     * Gets the value of the useradded property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseradded() {
        if (useradded == null) {
            return "yes";
        } else {
            return useradded;
        }
    }

    /**
     * Sets the value of the useradded property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseradded(String value) {
        this.useradded = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuantity() {
        if (quantity == null) {
            return "1";
        } else {
            return quantity;
        }
    }

    /**
     * Sets the value of the quantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuantity(String value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the rangedattack property.
     * 
     * @return
     *     possible object is
     *     {@link Rangedattack }
     *     
     */
    public Rangedattack getRangedattack() {
        return rangedattack;
    }

    /**
     * Sets the value of the rangedattack property.
     * 
     * @param value
     *     allowed object is
     *     {@link Rangedattack }
     *     
     */
    public void setRangedattack(Rangedattack value) {
        this.rangedattack = value;
    }

    /**
     * Gets the value of the weight property.
     * 
     * @return
     *     possible object is
     *     {@link Weight }
     *     
     */
    public Weight getWeight() {
        return weight;
    }

    /**
     * Sets the value of the weight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Weight }
     *     
     */
    public void setWeight(Weight value) {
        this.weight = value;
    }

    /**
     * Gets the value of the cost property.
     * 
     * @return
     *     possible object is
     *     {@link Cost }
     *     
     */
    public Cost getCost() {
        return cost;
    }

    /**
     * Sets the value of the cost property.
     * 
     * @param value
     *     allowed object is
     *     {@link Cost }
     *     
     */
    public void setCost(Cost value) {
        this.cost = value;
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
     * Gets the value of the itemslot property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemslot() {
        return itemslot;
    }

    /**
     * Sets the value of the itemslot property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemslot(String value) {
        this.itemslot = value;
    }

    /**
     * Gets the value of the itempower property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itempower property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItempower().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Itempower }
     * 
     * 
     */
    public List<Itempower> getItempower() {
        if (itempower == null) {
            itempower = new ArrayList<Itempower>();
        }
        return this.itempower;
    }

    /**
     * Gets the value of the wepcategory property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wepcategory property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWepcategory().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Wepcategory }
     * 
     * 
     */
    public List<Wepcategory> getWepcategory() {
        if (wepcategory == null) {
            wepcategory = new ArrayList<Wepcategory>();
        }
        return this.wepcategory;
    }

    /**
     * Gets the value of the weptype property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the weptype property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWeptype().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Weptype }
     * 
     * 
     */
    public List<Weptype> getWeptype() {
        if (weptype == null) {
            weptype = new ArrayList<Weptype>();
        }
        return this.weptype;
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
