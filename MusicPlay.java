package FightAgainstLandlords;
import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;


public class MusicPlay {
	public String music_name;
	public MusicPlay(String music_name) {
		this.music_name = music_name;
	}
	void music_play() {
		String path = "./Music/" + this.music_name + ".wav";
		AudioInputStream as;
		try {
			as = AudioSystem.getAudioInputStream(new File(path));
			AudioFormat format = as.getFormat();
			SourceDataLine sdl = null;
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
			sdl = (SourceDataLine) AudioSystem.getLine(info);
			sdl.open(format);
			sdl.start();
			int nBytesRead = 0;
			byte[] abData = new byte[512];
			while (nBytesRead != -1) {
				nBytesRead = as.read(abData, 0, abData.length);
				if (nBytesRead >= 0)
					sdl.write(abData, 0, nBytesRead);
			}
		    //关闭SourceDataLine
			sdl.drain();
			sdl.close();
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
