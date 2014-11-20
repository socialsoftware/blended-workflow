package pt.ist.socialsoftware.blendedworkflow.shared;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class StringUtils {

	private static Logger _log = Logger.getLogger(StringUtils.class);

	// So that anyone instantiate the class
	private StringUtils() {
	}

	/**
	 * Transforms a file into a String. Copied from YAWL.
	 * 
	 * @see www.yawlfoundation.org
	 * @param filename
	 *            the filename of the file.
	 * @return the file in a string.
	 */
	public static String fileToString(String filename) {
		File file = new File(filename);

		return fileToString(file);
	}

	/**
	 * Transforms a file into a String. Copied from YAWL.
	 * 
	 * @see www.yawlfoundation.org
	 * @param file
	 *            the file.
	 * @return the file in a string.
	 */
	public static String fileToString(File file) {
		try {
			if (!file.exists()) {
				_log.info("File does not exist");
				file.createNewFile();
			}

			int bufsize = (int) file.length();
			FileInputStream fis = new FileInputStream(file);

			// read into buffered byte stream - to preserve UTF-8
			BufferedInputStream inStream = new BufferedInputStream(fis);
			ByteArrayOutputStream outStream = new ByteArrayOutputStream(bufsize);
			byte[] buffer = new byte[bufsize];

			// read chunks from the input stream and write them out
			int bytesRead = 0;
			while ((bytesRead = inStream.read(buffer, 0, bufsize)) > 0) {
				outStream.write(buffer, 0, bytesRead);
			}
			outStream.flush();

			// convert the bytes to a UTF-8 string
			return outStream.toString("UTF-8");
		} catch (Exception e) {
			return null;
		}
	}

	public static String bufferToString(ByteArrayOutputStream buffer) {
		try {
			buffer.flush();
			return buffer.toString("UTF-8");
		} catch (IOException e) {
			return null;
		}
	}

	public static Document stringToDoc(String string) {
		SAXBuilder _builder = new SAXBuilder(
				"org.apache.xerces.parsers.SAXParser");

		// _builder.setIgnoringBoundaryWhitespace(true);

		_log.info("XXXXX: stringToDoc");

		try {
			return (string != null) ? _builder.build(new StringReader(string))
					: null;
		} catch (JDOMException e) {
			_log.error("Could not build JDOM document", e);
			return null;
		} catch (IOException e) {
			_log.error("Could not buil JDOM document", e);
			return null;
		}
	}
}