package org.eclipse.basyx.submodel.metamodel.api.submodelelement.dataelement;

/**
 * A BLOB is a data element that represents a file that is contained with its
 * source code in the value attribute.
 * 
 * @author rajashek, schnicke
 *
 */
public interface IBlob extends IDataElement {
	/**
	 * Gets the value of the Blob instance of a blob data element.
	 * The returned value is Base64 encoded.
	 * 
	 * @return
	 */
	@Override
	public String getValue();
	
	/**
	 * Sets a Base64 encoded value of the BLOB instance of a blob data element.
	 * 
	 * @param bytes
	 */
	public void setValue(String value);

	/**
	 * Gets the value of the Blob instance of a blob data element.
	 * 
	 * @return
	 */
	public byte[] getByteArrayValue();

	/**
	 * Sets the value of the Blob instance of a blob data element.
	 * 
	 * @param bytes
	 */
	public void setByteArrayValue(byte[] value);

	/**
	 * Returns the UTF8 String representation of the byte array BLOB value
	 *
	 * @return
	 */
	public String getUTF8String();

	/**
	 * Sets an UTF8 string as an encoded byte array in the BLOB data element value
	 * 
	 * @param text
	 */
	public void setUTF8String(String text);

	/**
	 * Gets the mime type of the content of the BLOB. The mime type states which
	 * file extension the file has. <br />
	 * Valid values are e.g. “application/json”, “application/xls”, ”image/jpg”.
	 * <br />
	 * The allowed values are defined as in RFC2046.
	 * 
	 * @return
	 */
	public String getMimeType();
}
