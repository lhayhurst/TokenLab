
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
    "_public"
})
@XmlRootElement(name = "document")
public class Document {

    @XmlElement(name = "public", required = true)
    protected Public _public;

    /**
     * Gets the value of the public property.
     * 
     * @return
     *     possible object is
     *     {@link Public }
     *     
     */
    public Public getPublic() {
        return _public;
    }

    /**
     * Sets the value of the public property.
     * 
     * @param value
     *     allowed object is
     *     {@link Public }
     *     
     */
    public void setPublic(Public value) {
        this._public = value;
    }

}
