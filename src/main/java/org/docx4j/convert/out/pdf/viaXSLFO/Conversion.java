package org.docx4j.convert.out.pdf.viaXSLFO;

import java.io.File;
import java.io.OutputStream;

import org.docx4j.Docx4J;
import org.docx4j.convert.out.FOSettings;
import org.docx4j.convert.out.pdf.PdfConversion;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @deprecated
 */
public class Conversion extends PdfConversion {
	public static Logger log = LoggerFactory.getLogger(Conversion.class);
	protected WordprocessingMLPackage wordMLPackage = null;
	
	public Conversion(WordprocessingMLPackage wordMLPackage) {
		this.wordMLPackage = wordMLPackage;
	}
	
	private static File saveFO;
	public void setSaveFO(File save) {
		saveFO = save;
	}
	
	/** Create a pdf version of the document, using XSL FO. 
	 * 
	 * @param os
	 *            The OutputStream to write the pdf to 
	 * @param settings
	 *            The configuration for the conversion 
	 * 
	 * */     
	public void output(OutputStream os, FOSettings settings) throws Docx4JException {
		setupSettings(settings, FOSettings.INTERNAL_FO_MIME);
		Docx4J.toFO(settings, os, Docx4J.FLAG_EXPORT_PREFER_XSL);
	}
	
	public void outputXSLFO(OutputStream os, PdfSettings settings) throws Docx4JException {
		setupSettings(settings, FOSettings.MIME_PDF);
		Docx4J.toFO(settings, os, Docx4J.FLAG_EXPORT_PREFER_XSL);
	}
	
	protected void setupSettings(FOSettings settings, String mime) {
		if ((wordMLPackage != null) && (settings.getWmlPackage() == null)) {
			settings.setWmlPackage(wordMLPackage);
		}
		if ((saveFO != null) && (settings.getFoDumpFile() == null)) {
			settings.setFoDumpFile(saveFO);
		}
		settings.setApacheFopMime(mime);
	}
}
    
