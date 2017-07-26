package it.besmartbeopen.gitlab;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLConnection;

import feign.form.MultipartEncodedDataProcessor;
import it.besmartbeopen.gitlab.model.UploadFile;

/**
 * @author marco
 *
 */
public class UploadMultipartEncodedDataProcessor
    extends MultipartEncodedDataProcessor {

  private static final String CRLF = "\r\n";

  @Override
  protected boolean isPayload(Object value) {
    return super.isPayload(value) || value instanceof UploadFile;
  }

  @Override
  protected void writeByteOrFile(OutputStream output, PrintWriter writer, String name,
      Object value) {

    if (value instanceof UploadFile) {
      writeFileMeta(writer, name, ((UploadFile) value).getName(), null);

      // writeFile:
      try {
        output.write(((UploadFile) value).getData());
        writer.flush();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    } else {
      super.writeByteOrFile(output, writer, name, value);
    }
  }

  /**
   * Argh! feign.form MultipartEncodedDataProcessor#writeFileMeta is private
   * @param writer
   * @param name
   * @param fileName
   * @param contentValue
   */
  protected void writeFileMeta(PrintWriter writer, String name, String fileName,
      String contentValue) {

    String contentDesposition =
        "Content-Disposition: form-data; name=\"" + name + "\"; " + "filename=\"" + fileName + "\"";

    if (contentValue == null) {
      contentValue = (fileName != null) ? URLConnection.guessContentTypeFromName(fileName)
          : "application/octet-stream";
    }

    String contentType = "Content-Type: " + contentValue;

    writer.append(contentDesposition).append(CRLF);
    writer.append(contentType).append(CRLF);
    writer.append("Content-Transfer-Encoding: binary").append(CRLF);
    writer.append(CRLF).flush();
  }
}
