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
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for announceableResource complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="announceableResource"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.onem2m.org/xml/protocols}regularResource"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="at" type="{http://www.onem2m.org/xml/protocols}listOfURIs" minOccurs="0"/&gt;
 *         &lt;element name="aa" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction&gt;
 *               &lt;simpleType&gt;
 *                 &lt;list itemType="{http://www.w3.org/2001/XMLSchema}NCName" /&gt;
 *               &lt;/simpleType&gt;
 *               &lt;minLength value="1"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "announceableResource", propOrder = {
    "at",
    "aa"
})
@XmlSeeAlso({
    Smd.class,
    Cnt.class,
    Grp.class,
    Ts.class,
    Trpt.class,
    Ae.class,
    MgmtResource.class,
    Nod.class,
    Lcp.class,
    Csr.class
})
public class AnnounceableResource
    extends RegularResource
{

    @XmlList
    protected List<String> at;
    @XmlList
    protected List<String> aa;

    /**
     * Gets the value of the at property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the at property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAt().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAt() {
        if (at == null) {
            at = new ArrayList<String>();
        }
        return this.at;
    }

    /**
     * Gets the value of the aa property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aa property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAa().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAa() {
        if (aa == null) {
            aa = new ArrayList<String>();
        }
        return this.aa;
    }

}