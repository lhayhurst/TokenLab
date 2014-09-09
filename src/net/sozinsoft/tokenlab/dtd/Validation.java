
package net.sozinsoft.tokenlab.dtd;

import java.util.ArrayList;
import java.util.List;
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
    "report",
    "validmessage"
})
@XmlRootElement(name = "validation")
public class Validation {

    @XmlElement(required = true)
    protected String report;
    protected List<Validmessage> validmessage;

    /**
     * Gets the value of the report property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReport() {
        return report;
    }

    /**
     * Sets the value of the report property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReport(String value) {
        this.report = value;
    }

    /**
     * Gets the value of the validmessage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the validmessage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValidmessage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Validmessage }
     * 
     * 
     */
    public List<Validmessage> getValidmessage() {
        if (validmessage == null) {
            validmessage = new ArrayList<Validmessage>();
        }
        return this.validmessage;
    }

}
