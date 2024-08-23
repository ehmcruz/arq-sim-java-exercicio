import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {
	public void memory_write (short addr, short value)
	{
	}

	void load_binary (String binary_name)
	{
		try {
			FileInputStream fileInputStream = new FileInputStream(binary_name);
			DataInputStream dataInputStream = new DataInputStream(fileInputStream);

			long tamanhoArquivo = fileInputStream.getChannel().size();

			int numShorts = (int) (tamanhoArquivo / 2);

			for (int i = 0; i < numShorts; i++) {
				int low = dataInputStream.readByte() & 0x000000FF;
				int high = dataInputStream.readByte() & 0x000000FF;
				int value = (low | (high << 8)) & 0x0000FFFF;

				this.memory_write((short)i, (short)value);
			}

			dataInputStream.close();
			fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
