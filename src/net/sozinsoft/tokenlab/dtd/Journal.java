
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
@XmlRootElement(name = "journal")
public class Journal {

    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String name;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String cp;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String gamedate;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String gp;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String pp;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String prestigeaward;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String prestigespend;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String realdate;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String sp;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String xp;
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
     * Gets the value of the cp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCp() {
        return cp;
    }

    /**
     * Sets the value of the cp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCp(String value) {
        this.cp = value;
    }

    /**
     * Gets the value of the gamedate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGamedate() {
        return gamedate;
    }

    /**
     * Sets the value of the gamedate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGamedate(String value) {
        this.gamedate = value;
    }

    /**
     * Gets the value of the gp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGp() {
        return gp;
    }

    /**
     * Sets the value of the gp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGp(String value) {
        this.gp = value;
    }

    /**
     * Gets the value of the pp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPp() {
        return pp;
    }

    /**
     * Sets the value of the pp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPp(String value) {
        this.pp = value;
    }

    /**
     * Gets the value of the prestigeaward property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrestigeaward() {
        return prestigeaward;
    }

    /**
     * Sets the value of the prestigeaward property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrestigeaward(String value) {
        this.prestigeaward = value;
    }

    /**
     * Gets the value of the prestigespend property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrestigespend() {
        return prestigespend;
    }

    /**
     * Sets the value of the prestigespend property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrestigespend(String value) {
        this.prestigespend = value;
    }

    /**
     * Gets the value of the realdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRealdate() {
        return realdate;
    }

    /**
     * Sets the value of the realdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRealdate(String value) {
        this.realdate = value;
    }

    /**
     * Gets the value of the sp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSp() {
        return sp;
    }

    /**
     * Sets the value of the sp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSp(String value) {
        this.sp = value;
    }

    /**
     * Gets the value of the xp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXp() {
        return xp;
    }

    /**
     * Sets the value of the xp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXp(String value) {
        this.xp = value;
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
