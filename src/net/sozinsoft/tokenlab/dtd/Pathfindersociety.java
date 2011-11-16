
package net.sozinsoft.tokenlab.dtd;

import javax.annotation.Generated;
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
@XmlRootElement(name = "pathfindersociety")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-11-15T04:01:32-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
public class Pathfindersociety {

    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-11-15T04:01:32-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String playernum;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-11-15T04:01:32-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String characternum;

    /**
     * Gets the value of the playernum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-11-15T04:01:32-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public String getPlayernum() {
        return playernum;
    }

    /**
     * Sets the value of the playernum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-11-15T04:01:32-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPlayernum(String value) {
        this.playernum = value;
    }

    /**
     * Gets the value of the characternum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-11-15T04:01:32-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public String getCharacternum() {
        return characternum;
    }

    /**
     * Sets the value of the characternum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2011-11-15T04:01:32-06:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setCharacternum(String value) {
        this.characternum = value;
    }

}
