//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.16 at 09:48:33 AM CET 
//


package org.eclipse.basyx.onem2m.xml.protocols;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.onem2m.org/xml/protocols}mgmtResource"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="od" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *         &lt;element name="ror" type="{http://www.onem2m.org/xml/protocols}listOfM2MID"/&gt;
 *         &lt;element name="rct" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *         &lt;element name="rctn" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="rch" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *         &lt;element name="lec" type="{http://www.onem2m.org/xml/protocols}listOfEventCat"/&gt;
 *         &lt;element name="lqet" type="{http://www.onem2m.org/xml/protocols}listOfMinMax"/&gt;
 *         &lt;element name="lset" type="{http://www.onem2m.org/xml/protocols}listOfMinMax"/&gt;
 *         &lt;element name="loet" type="{http://www.onem2m.org/xml/protocols}listOfMinMax"/&gt;
 *         &lt;element name="lrp" type="{http://www.onem2m.org/xml/protocols}listOfMinMax"/&gt;
 *         &lt;element name="lda"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token"&gt;
 *               &lt;enumeration value="0"/&gt;
 *               &lt;enumeration value="1"/&gt;
 *               &lt;enumeration value="0 1"/&gt;
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
@XmlType(name = "", propOrder = {
    "od",
    "ror",
    "rct",
    "rctn",
    "rch",
    "lec",
    "lqet",
    "lset",
    "loet",
    "lrp",
    "lda"
})
public class Cml
    extends MgmtResource
{

    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger od;
    @XmlList
    @XmlElement(required = true)
    protected List<String> ror;
    @XmlElement(required = true)
    protected Object rct;
    protected Boolean rctn;
    @XmlElement(required = true)
    protected Object rch;
    @XmlList
    @XmlElement(required = true)
    protected List<String> lec;
    @XmlList
    @XmlElement(type = Long.class)
    protected List<Long> lqet;
    @XmlList
    @XmlElement(type = Long.class)
    protected List<Long> lset;
    @XmlList
    @XmlElement(type = Long.class)
    protected List<Long> loet;
    @XmlList
    @XmlElement(type = Long.class)
    protected List<Long> lrp;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String lda;

    /**
     * Gets the value of the od property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getOd() {
        return od;
    }

    /**
     * Sets the value of the od property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setOd(BigInteger value) {
        this.od = value;
    }

    /**
     * Gets the value of the ror property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ror property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getRor() {
        if (ror == null) {
            ror = new ArrayList<String>();
        }
        return this.ror;
    }

    /**
     * Gets the value of the rct property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getRct() {
        return rct;
    }

    /**
     * Sets the value of the rct property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setRct(Object value) {
        this.rct = value;
    }

    /**
     * Gets the value of the rctn property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRctn() {
        return rctn;
    }

    /**
     * Sets the value of the rctn property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRctn(Boolean value) {
        this.rctn = value;
    }

    /**
     * Gets the value of the rch property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getRch() {
        return rch;
    }

    /**
     * Sets the value of the rch property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setRch(Object value) {
        this.rch = value;
    }

    /**
     * Gets the value of the lec property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lec property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLec().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getLec() {
        if (lec == null) {
            lec = new ArrayList<String>();
        }
        return this.lec;
    }

    /**
     * Gets the value of the lqet property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lqet property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLqet().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getLqet() {
        if (lqet == null) {
            lqet = new ArrayList<Long>();
        }
        return this.lqet;
    }

    /**
     * Gets the value of the lset property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lset property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLset().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getLset() {
        if (lset == null) {
            lset = new ArrayList<Long>();
        }
        return this.lset;
    }

    /**
     * Gets the value of the loet property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the loet property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLoet().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getLoet() {
        if (loet == null) {
            loet = new ArrayList<Long>();
        }
        return this.loet;
    }

    /**
     * Gets the value of the lrp property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lrp property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLrp().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getLrp() {
        if (lrp == null) {
            lrp = new ArrayList<Long>();
        }
        return this.lrp;
    }

    /**
     * Gets the value of the lda property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLda() {
        return lda;
    }

    /**
     * Sets the value of the lda property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLda(String value) {
        this.lda = value;
    }

}
