package absimple;

public abstract class Player {
	
	abstract void play(int pos);	//추상메서드
	abstract void stop();
}
class AudioPlayer extends Player {
	void play(int pos) {}
	void stop() {}
	
}
abstract class AbstractPlayer extends Player {
	void play(int pos) {}
}
