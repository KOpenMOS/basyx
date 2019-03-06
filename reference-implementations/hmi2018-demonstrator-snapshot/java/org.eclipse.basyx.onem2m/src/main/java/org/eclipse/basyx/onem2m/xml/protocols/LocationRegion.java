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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for locationRegion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="locationRegion"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="accc"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction&gt;
 *               &lt;simpleType&gt;
 *                 &lt;list itemType="{http://www.onem2m.org/xml/protocols}countryCode" /&gt;
 *               &lt;/simpleType&gt;
 *               &lt;minLength value="1"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="accr"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction&gt;
 *               &lt;simpleType&gt;
 *                 &lt;list itemType="{http://www.w3.org/2001/XMLSchema}float" /&gt;
 *               &lt;/simpleType&gt;
 *               &lt;length value="3"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "locationRegion", propOrder = {
    "accc",
    "accr"
})
public class LocationRegion {

    @XmlList
    protected List<String> accc;
    @XmlList
    @XmlElement(type = Float.class)
    protected List<Float> accr;

    /**
     * Gets the value of the accc property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accc property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccc().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAccc() {
        if (accc == null) {
            accc = new ArrayList<String>();
        }
        return this.accc;
    }

    /**
     * Gets the value of the accr property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accr property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccr().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Float }
     * 
     * 
     */
    public List<Float> getAccr() {
        if (accr == null) {
            accr = new ArrayList<Float>();
        }
        return this.accr;
    }

}