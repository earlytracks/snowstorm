package org.ihtsdo.elasticsnomed.core.rf2.export;

import org.ihtsdo.elasticsnomed.core.data.domain.Description;

import java.io.BufferedWriter;
import java.io.IOException;

class DescriptionExportWriter extends ExportWriter<Description> {

	static final String HEADER = "id\teffectiveTime\tactive\tmoduleId\tconceptId\tlanguageCode\ttypeId\tterm\tcaseSignificanceId";

	DescriptionExportWriter(BufferedWriter bufferedWriter) {
		super(bufferedWriter);
	}

	void writeHeader() throws IOException {
		bufferedWriter.write(HEADER);
		bufferedWriter.newLine();
	}

	void flush() {
		try {
			for (Description description : componentBuffer) {
				bufferedWriter.write(description.getDescriptionId());
				bufferedWriter.write(TAB);
				bufferedWriter.write(description.getEffectiveTime() != null ? description.getEffectiveTime() : "");
				bufferedWriter.write(TAB);
				bufferedWriter.write(description.isActive() ? "1" : "0");
				bufferedWriter.write(TAB);
				bufferedWriter.write(description.getModuleId());
				bufferedWriter.write(TAB);
				bufferedWriter.write(description.getConceptId());
				bufferedWriter.write(TAB);
				bufferedWriter.write(description.getLanguageCode());
				bufferedWriter.write(TAB);
				bufferedWriter.write(description.getTypeId());
				bufferedWriter.write(TAB);
				bufferedWriter.write(description.getTerm());
				bufferedWriter.write(TAB);
				bufferedWriter.write(description.getCaseSignificanceId());
				bufferedWriter.newLine();
			}
			componentBuffer.clear();
		} catch (IOException e) {
			throw new ExportException("Failed to write Description to RF2 file.", e);
		}
	}

}