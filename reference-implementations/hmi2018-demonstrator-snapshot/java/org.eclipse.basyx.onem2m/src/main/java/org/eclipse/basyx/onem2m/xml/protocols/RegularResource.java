//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.16 at 09:48:33 AM CET 
//


package org.eclipse.basyx.onem2m.xml.protocols;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for regularResource complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="regularResource"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.onem2m.org/xml/protocols}resource"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="acpi" type="{http://www.onem2m.org/xml/protocols}acpType" minOccurs="0"/&gt;
 *         &lt;element name="et" type="{http://www.onem2m.org/xml/protocols}timestamp"/&gt;
 *         &lt;element name="daci" type="{http://www.onem2m.org/xml/protocols}listOfURIs" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "regularResource", propOrder = {
    "acpi",
    "et",
    "daci"
})
@XmlSeeAlso({
    Ntpr.class,
    Sub.class,
    AnnounceableResource.class,
    Exin.class,
    Mgc.class,
    Evcg.class,
    Stcg.class,
    Stcl.class,
    Req.class,
    Dlv.class,
    Svsn.class,
    Mssp.class,
    Asar.class,
    Rol.class,
    Tk.class,
    Dac.class,
    Pdr.class,
    Ntp.class
})
public class RegularResource
    extends Resource
{

    @XmlList
    protected List<String> acpi;
    @XmlElement(required = true)
    protected String et;
    @XmlList
    protected List<String> daci;

    /**
     * Gets the value of the acpi property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the acpi property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAcpi().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAcpi() {
        if (acpi == null) {
            acpi = new ArrayList<String>();
        }
        return this.acpi;
    }

    /**
     * Gets the value of the et property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEt() {
        return et;
    }

    /**
     * Sets the value of the et property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEt(String value) {
        this.et = value;
    }

    /**
     * Gets the value of the daci property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the daci property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDaci().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDaci() {
        if (daci == null) {
            daci = new ArrayList<String>();
        }
        return this.daci;
    }

}
