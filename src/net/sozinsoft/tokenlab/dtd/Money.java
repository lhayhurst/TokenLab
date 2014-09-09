
package net.sozinsoft.tokenlab.dtd;

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
@XmlType(name = "")
@XmlRootElement(name = "money")
public class Money {

    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String cp;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String gp;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String pp;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String sp;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String total;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String valuables;

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
     * Gets the value of the total property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotal() {
        return total;
    }

    /**
     * Sets the value of the total property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotal(String value) {
        this.total = value;
    }

    /**
     * Gets the value of the valuables property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValuables() {
        return valuables;
    }

    /**
     * Sets the value of the valuables property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValuables(String value) {
        this.valuables = value;
    }

}
