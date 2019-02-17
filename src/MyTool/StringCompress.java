package MyTool;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class StringCompress {
	public static String compress(String original) {
		if (original == null || original == "") {
			return original;
		}
		byte[] dataByte = original.getBytes();
		Deflater def = new Deflater();
		def.setLevel(Deflater.BEST_COMPRESSION);
		def.setInput(dataByte);
		def.finish();
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(dataByte.length);
		byte[] buf = new byte[1024];
		while (!def.finished()) {
			int compByte = def.deflate(buf);
			byteArrayOutputStream.write(buf, 0, compByte);
		}
		try {
			byteArrayOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] compData = byteArrayOutputStream.toByteArray();
		String encoded = Base64.getEncoder().encodeToString(compData);
		return encoded;
	}

	public static String decompress(String encoded) {
		String outputString = "";
		if (encoded == null || encoded == "") {
			return encoded;
		}
		byte[] compressedData = Base64.getDecoder().decode(encoded);
		final Inflater decompresser = new Inflater();
		try {
			try (InputStream bis = new ByteArrayInputStream(compressedData)) {
				final byte[] inpBuf = new byte[512];
				final byte[] outBuf = new byte[512];
				int rd;
				do {
					rd = bis.read(inpBuf);
					if (rd > 0) {
						decompresser.setInput(inpBuf, 0, rd);
					}
					while (!decompresser.finished()) {
						int siz = decompresser.inflate(outBuf);
						if (siz > 0) {
							String out = new String(outBuf, 0, siz, "SJIS");
							outputString = outputString + out;
						} else {
							break;
						}
					}
				} while (rd > 0);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (DataFormatException e) {
				e.printStackTrace();
			}
		} finally {
			decompresser.end();
		}
		return outputString;
	}
}
