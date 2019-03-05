
package gov.illinois.ies.business.rules.co;

import gov.illinois.ies.business.rules.co.CoDebugger;
import gov.illinois.ies.business.entities.correspondence.COCorrespondence;
import gov.illinois.ies.business.entities.correspondence.DCCargo;
import gov.illinois.ies.business.entities.correspondence.DocumentCargo;

/**
 * The class <code>FXX401Assembler</code> assembles the form "IL444-3430 Congratulations on Your New Job!"
 * This class prepares feed file for OPUS servers.
 */
public class FXX401Assembler extends DCAssembler implements CoAssembler {

	/**
	 * Constructor for FXX401Assembler. 
	 */
	public FXX401Assembler() {
		super();
	}

	/**
	 * The generate document method of the assembler which in turn calls all
	 * appropriate methods of this class and the parent classes to build the
	 * printString.
	 * 
	 * @param coCorrespondence CO trigger detail Object
	 * @param templateSeqNo document page number
	 * @return Contains case detail information for pending case number
	 * @throws CoException
	 */
	public StringBuffer genarateDocument(COCorrespondence coCorrespondence, long templateSeqNo)	throws CoException {
		this.addressLabelRequired = true;
		StringBuffer printString = new StringBuffer();			
		if (templateSeqNo == 1) {
			documentCargo = new DCCargo();
			this.setCoCorrespondence(coCorrespondence);

			documentCargo = (DocumentCargo) super.buildCargo();
			if (super.getCoCorrespondence().getIsManualyGenerated() == true){
				try {
					documentCargo.setDefault();	
				} catch (Exception e) {
					throw new CoException(
							"Exception in FXX401Assembler:setDefault()", e);
				}
			}
			printString = this.getPrintString(new StringBuffer(),templateSeqNo);	
		}return printString;
	}

	/**
	 * This method prepares and formats pending case information.
	 * 
	 * @param source default caseApp information
	 * @param templateSeqNumber document page number 
	 * @return Contains case detail information for pending case number
	 */
	public StringBuffer getPrintString(StringBuffer source, 
			long templateSeqNumber) throws CoException {
		try {
			if (templateSeqNumber == 1 ) {
				source.append(super.getStdCoHeaderBlock());
			}
		}catch (Exception e) {
			throw new CoException(
					"Exception in FXX401Assembler:getPrintString()", e);
		}
		CoDebugger.debugInformation("Exit FXX401Assembler.getPrintString()");
		return source;
	}
}

