package com.temple.util.file.media.stream;

public final class EmptyAudioStreamMetadata implements AudioStreamMetadata {

	private static final long serialVersionUID = 1L;
	
	public static final AudioStreamMetadata INSTANCE = new EmptyAudioStreamMetadata(); 
	
	private EmptyAudioStreamMetadata() {}
	
	@Override
	public long getSize() {
		return 0;
	}
	
	@Override
	public double getPreciseDuration() {
		return 0;
	}
	
	@Override
	public int getDuration() {
		return 0;
	}
	
	@Override
	public String getCodecName() {
		return null;
	}
	
	@Override
	public String getCodecLongName() {
		return null;
	}
	
	@Override
	public int getBitRate() {
		return 0;
	}
	
	@Override
	public Long getSampleRate() {
		return Long.valueOf(0);
	}
	
	@Override
	public int getChannelsCount() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public String toString() {
		return "Empty audio stream";
	}
}
