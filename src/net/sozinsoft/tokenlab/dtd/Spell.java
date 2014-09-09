
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
    "description",
    "spellcomp",
    "spellschool",
    "spellsubschool",
    "spelldescript"
})
@XmlRootElement(name = "spell")
public class Spell {

    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String name;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String level;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String area;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String casterlevel;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String castsleft;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String casttime;
    @XmlAttribute(name = "class")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String clazz;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String componenttext;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String dc;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String descriptor;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String duration;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String effect;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String range;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String resist;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String save;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String schooltext;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String subschooltext;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String descriptortext;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String savetext;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String resisttext;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String target;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String useradded;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String spontaneous;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String unlimited;
    @XmlElement(required = true)
    protected String description;
    protected List<Spellcomp> spellcomp;
    protected List<Spellschool> spellschool;
    protected List<Spellsubschool> spellsubschool;
    protected List<Spelldescript> spelldescript;

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
     * Gets the value of the area property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArea() {
        return area;
    }

    /**
     * Sets the value of the area property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArea(String value) {
        this.area = value;
    }

    /**
     * Gets the value of the casterlevel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCasterlevel() {
        return casterlevel;
    }

    /**
     * Sets the value of the casterlevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCasterlevel(String value) {
        this.casterlevel = value;
    }

    /**
     * Gets the value of the castsleft property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCastsleft() {
        return castsleft;
    }

    /**
     * Sets the value of the castsleft property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCastsleft(String value) {
        this.castsleft = value;
    }

    /**
     * Gets the value of the casttime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCasttime() {
        return casttime;
    }

    /**
     * Sets the value of the casttime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCasttime(String value) {
        this.casttime = value;
    }

    /**
     * Gets the value of the clazz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClazz() {
        return clazz;
    }

    /**
     * Sets the value of the clazz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClazz(String value) {
        this.clazz = value;
    }

    /**
     * Gets the value of the componenttext property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComponenttext() {
        return componenttext;
    }

    /**
     * Sets the value of the componenttext property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComponenttext(String value) {
        this.componenttext = value;
    }

    /**
     * Gets the value of the dc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDc() {
        return dc;
    }

    /**
     * Sets the value of the dc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDc(String value) {
        this.dc = value;
    }

    /**
     * Gets the value of the descriptor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescriptor() {
        return descriptor;
    }

    /**
     * Sets the value of the descriptor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescriptor(String value) {
        this.descriptor = value;
    }

    /**
     * Gets the value of the duration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDuration(String value) {
        this.duration = value;
    }

    /**
     * Gets the value of the effect property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEffect() {
        return effect;
    }

    /**
     * Sets the value of the effect property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEffect(String value) {
        this.effect = value;
    }

    /**
     * Gets the value of the range property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRange() {
        return range;
    }

    /**
     * Sets the value of the range property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRange(String value) {
        this.range = value;
    }

    /**
     * Gets the value of the resist property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResist() {
        return resist;
    }

    /**
     * Sets the value of the resist property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResist(String value) {
        this.resist = value;
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
     * Gets the value of the schooltext property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSchooltext() {
        return schooltext;
    }

    /**
     * Sets the value of the schooltext property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSchooltext(String value) {
        this.schooltext = value;
    }

    /**
     * Gets the value of the subschooltext property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubschooltext() {
        return subschooltext;
    }

    /**
     * Sets the value of the subschooltext property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubschooltext(String value) {
        this.subschooltext = value;
    }

    /**
     * Gets the value of the descriptortext property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescriptortext() {
        return descriptortext;
    }

    /**
     * Sets the value of the descriptortext property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescriptortext(String value) {
        this.descriptortext = value;
    }

    /**
     * Gets the value of the savetext property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSavetext() {
        return savetext;
    }

    /**
     * Sets the value of the savetext property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSavetext(String value) {
        this.savetext = value;
    }

    /**
     * Gets the value of the resisttext property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResisttext() {
        return resisttext;
    }

    /**
     * Sets the value of the resisttext property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResisttext(String value) {
        this.resisttext = value;
    }

    /**
     * Gets the value of the target property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTarget() {
        return target;
    }

    /**
     * Sets the value of the target property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTarget(String value) {
        this.target = value;
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
     * Gets the value of the spontaneous property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpontaneous() {
        if (spontaneous == null) {
            return "no";
        } else {
            return spontaneous;
        }
    }

    /**
     * Sets the value of the spontaneous property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpontaneous(String value) {
        this.spontaneous = value;
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
     * Gets the value of the spellcomp property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the spellcomp property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpellcomp().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Spellcomp }
     * 
     * 
     */
    public List<Spellcomp> getSpellcomp() {
        if (spellcomp == null) {
            spellcomp = new ArrayList<Spellcomp>();
        }
        return this.spellcomp;
    }

    /**
     * Gets the value of the spellschool property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the spellschool property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpellschool().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Spellschool }
     * 
     * 
     */
    public List<Spellschool> getSpellschool() {
        if (spellschool == null) {
            spellschool = new ArrayList<Spellschool>();
        }
        return this.spellschool;
    }

    /**
     * Gets the value of the spellsubschool property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the spellsubschool property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpellsubschool().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Spellsubschool }
     * 
     * 
     */
    public List<Spellsubschool> getSpellsubschool() {
        if (spellsubschool == null) {
            spellsubschool = new ArrayList<Spellsubschool>();
        }
        return this.spellsubschool;
    }

    /**
     * Gets the value of the spelldescript property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the spelldescript property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpelldescript().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Spelldescript }
     * 
     * 
     */
    public List<Spelldescript> getSpelldescript() {
        if (spelldescript == null) {
            spelldescript = new ArrayList<Spelldescript>();
        }
        return this.spelldescript;
    }

}
