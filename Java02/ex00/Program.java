
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;

class SignaturesSet {
	Map<String, byte[]> signatures = new HashMap<>();
	int max_signature_length = 0;
	byte[] parse_signature(String signature) throws IOException {
		String[] parts = signature.split(" ");
		byte[] result = new byte[parts.length];
		for (int i = 0; i < parts.length; i++) {
			if (parts[i].length() != 2) {
				throw new IOException("Signature must be a sequence of bytes in hexadecimal format");
			}
			try {
				result[i] = (byte) Integer.parseInt(parts[i], 16);
			} catch (NumberFormatException e) {
				throw new IOException("Signature must be a sequence of bytes in hexadecimal format");
			}
		}
		return result;
	}

	void parse_fline(String line) throws IOException {
		String extension = null;
		byte[] signature = null;
		String parts[] = line.split(",");
		if (parts.length != 2) {
			throw new IOException("Invalid line: " + line + ": Expecting '[extension],[SI GN AT UR EE]` format");
		}
		extension = parts[0];
		signature = parse_signature(parts[1]);
		if (signature.length > max_signature_length) {
			max_signature_length = signature.length;
		}
		signatures.put(extension, signature);
	}
	public int get_signature_length() {
		return max_signature_length;
	}

	public SignaturesSet(String fname) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fname));
		String line;
		while ((line = br.readLine()) != null) {
			try {
				parse_fline(line);
			} catch (IOException e) {
				br.close();
				throw e;
			}
		}
		br.close();
	}

	public void print_signatures() {
		for (Map.Entry<String, byte[]> entry : signatures.entrySet()) {
			System.out.print(entry.getKey() + ": ");
			for (byte b : entry.getValue()) {
				System.out.printf("%02X ", b);
			}
			System.out.println();
		}
	}

	public String get_extension(byte[] fbytestream) {
		for (Map.Entry<String, byte[]> entry : signatures.entrySet()) {
			byte[] sig = entry.getValue();
			for (int i = 0; i < sig.length; i++) {
				if (i >= fbytestream.length) {
					break;
				}
				if (sig[i] != fbytestream[i]) {
					break;
				}
				if (i == sig.length - 1) {
					return entry.getKey();
				}
			}
		}
		return "UNKNOWN";
	}
}

public class Program {
	private static final String SIGNATURESFILE = "signatures.txt";
	private static final String RESULTSFILE = "results.txt";

	public static void main(String[] args) {
		SignaturesSet sigs = null;
		FileWriter fw = null;
		try {
			sigs = new SignaturesSet(SIGNATURESFILE);
			fw = new FileWriter(RESULTSFILE);
		} catch (IOException e) {
			System.err.println("Error while preparing file: " + e.getMessage());
			System.exit(1);
		}
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			String inpline = scanner.nextLine();
			if (inpline.equals("42"))
				break;
			try {
				// open inpline as a file and read bytes from it
				FileInputStream fis = new FileInputStream(inpline);
				byte[] buffer = new byte[sigs.get_signature_length()];
				int read = fis.read(buffer);
				if (read == -1) {
					System.out.println("File is empty");
					continue;
				}
				// compare read bytes with signatures
				String extension = sigs.get_extension(buffer);
				// write result to RESULTSFILE
				fw.write(inpline + " " + extension + "\n");
				fw.flush();
				System.out.println("PROCESSED");
			}
			catch (IOException e) {
				System.out.println("Error while reading file: " + e.getMessage());
			}
		
		}
	}
}

