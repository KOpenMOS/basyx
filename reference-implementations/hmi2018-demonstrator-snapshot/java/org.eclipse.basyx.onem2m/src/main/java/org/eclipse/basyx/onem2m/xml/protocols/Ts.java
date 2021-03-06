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
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.onem2m.org/xml/protocols}announceableResource"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="st" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/&gt;
 *         &lt;element name="cr" type="{http://www.onem2m.org/xml/protocols}ID"/&gt;
 *         &lt;element name="mni" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/&gt;
 *         &lt;element name="mbs" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/&gt;
 *         &lt;element name="mia" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/&gt;
 *         &lt;element name="cni" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/&gt;
 *         &lt;element name="cbs" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/&gt;
 *         &lt;element name="pei" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/&gt;
 *         &lt;element name="mdd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="mdn" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/&gt;
 *         &lt;element name="mdl" type="{http://www.onem2m.org/xml/protocols}missingDataList" minOccurs="0"/&gt;
 *         &lt;element name="mdc" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/&gt;
 *         &lt;element name="mdt" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/&gt;
 *         &lt;element name="or" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/&gt;
 *         &lt;element name="la" type="{http://www.w3.org/2001/XMLSchema}anyURI"/&gt;
 *         &lt;element name="ol" type="{http://www.w3.org/2001/XMLSchema}anyURI"/&gt;
 *         &lt;choice minOccurs="0"&gt;
 *           &lt;element name="ch" type="{http://www.onem2m.org/xml/protocols}childResourceRef" maxOccurs="unbounded"/&gt;
 *           &lt;choice maxOccurs="unbounded"&gt;
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols}tsi"/&gt;
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols}sub"/&gt;
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols}smd"/&gt;
 *           &lt;/choice&gt;
 *         &lt;/choice&gt;
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
    "st",
    "cr",
    "mni",
    "mbs",
    "mia",
    "cni",
    "cbs",
    "pei",
    "mdd",
    "mdn",
    "mdl",
    "mdc",
    "mdt",
    "or",
    "la",
    "ol",
    "ch",
    "tsiOrSubOrSmd"
})
public class Ts
    extends AnnounceableResource
{

    @XmlElement(required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger st;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String cr;
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger mni;
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger mbs;
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger mia;
    @XmlElement(required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger cni;
    @XmlElement(required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger cbs;
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger pei;
    protected Boolean mdd;
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger mdn;
    @XmlList
    @XmlSchemaType(name = "anySimpleType")
    protected List<String> mdl;
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger mdc;
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger mdt;
    @XmlSchemaType(name = "anyURI")
    protected String or;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String la;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String ol;
    protected List<ChildResourceRef> ch;
    @XmlElements({
        @XmlElement(name = "tsi", namespace = "http://www.onem2m.org/xml/protocols", type = Tsi.class),
        @XmlElement(name = "sub", namespace = "http://www.onem2m.org/xml/protocols", type = Sub.class),
        @XmlElement(name = "smd", namespace = "http://www.onem2m.org/xml/protocols", type = Smd.class)
    })
    protected List<Resource> tsiOrSubOrSmd;

    /**
     * Gets the value of the st property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSt() {
        return st;
    }

    /**
     * Sets the value of the st property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSt(BigInteger value) {
        this.st = value;
    }

    /**
     * Gets the value of the cr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCr() {
        return cr;
    }

    /**
     * Sets the value of the cr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCr(String value) {
        this.cr = value;
    }

    /**
     * Gets the value of the mni property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMni() {
        return mni;
    }

    /**
     * Sets the value of the mni property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMni(BigInteger value) {
        this.mni = value;
    }

    /**
     * Gets the value of the mbs property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMbs() {
        return mbs;
    }

    /**
     * Sets the value of the mbs property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMbs(BigInteger value) {
        this.mbs = value;
    }

    /**
     * Gets the value of the mia property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMia() {
        return mia;
    }

    /**
     * Sets the value of the mia property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMia(BigInteger value) {
        this.mia = value;
    }

    /**
     * Gets the value of the cni property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCni() {
        return cni;
    }

    /**
     * Sets the value of the cni property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCni(BigInteger value) {
        this.cni = value;
    }

    /**
     * Gets the value of the cbs property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCbs() {
        return cbs;
    }

    /**
     * Sets the value of the cbs property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCbs(BigInteger value) {
        this.cbs = value;
    }

    /**
     * Gets the value of the pei property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPei() {
        return pei;
    }

    /**
     * Sets the value of the pei property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPei(BigInteger value) {
        this.pei = value;
    }

    /**
     * Gets the value of the mdd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMdd() {
        return mdd;
    }

    /**
     * Sets the value of the mdd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMdd(Boolean value) {
        this.mdd = value;
    }

    /**
     * Gets the value of the mdn property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMdn() {
        return mdn;
    }

    /**
     * Sets the value of the mdn property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMdn(BigInteger value) {
        this.mdn = value;
    }

    /**
     * Gets the value of the mdl property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mdl property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMdl().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getMdl() {
        if (mdl == null) {
            mdl = new ArrayList<String>();
        }
        return this.mdl;
    }

    /**
     * Gets the value of the mdc property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMdc() {
        return mdc;
    }

    /**
     * Sets the value of the mdc property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMdc(BigInteger value) {
        this.mdc = value;
    }

    /**
     * Gets the value of the mdt property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMdt() {
        return mdt;
    }

    /**
     * Sets the value of the mdt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMdt(BigInteger value) {
        this.mdt = value;
    }

    /**
     * Gets the value of the or property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOr() {
        return or;
    }

    /**
     * Sets the value of the or property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOr(String value) {
        this.or = value;
    }

    /**
     * Gets the value of the la property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLa() {
        return la;
    }

    /**
     * Sets the value of the la property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLa(String value) {
        this.la = value;
    }

    /**
     * Gets the value of the ol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOl() {
        return ol;
    }

    /**
     * Sets the value of the ol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOl(String value) {
        this.ol = value;
    }

    /**
     * Gets the value of the ch property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ch property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCh().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChildResourceRef }
     * 
     * 
     */
    public List<ChildResourceRef> getCh() {
        if (ch == null) {
            ch = new ArrayList<ChildResourceRef>();
        }
        return this.ch;
    }

    /**
     * Gets the value of the tsiOrSubOrSmd property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tsiOrSubOrSmd property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTsiOrSubOrSmd().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Tsi }
     * {@link Sub }
     * {@link Smd }
     * 
     * 
     */
    public List<Resource> getTsiOrSubOrSmd() {
        if (tsiOrSubOrSmd == null) {
            tsiOrSubOrSmd = new ArrayList<Resource>();
        }
        return this.tsiOrSubOrSmd;
    }

}
