package Music;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Music {

    private Clip backgroundMusic;
    private Clip effectMusic;
    private boolean isPaused = false;
    private String currentBackgroundMusicPath;

    public Music() {}

    /**
     * Toca uma música de fundo em loop
     */
    public void playBackgroundMusic(String filePath) {
        try {
            stopBackgroundMusic(); // Para qualquer música anterior
            
            File audioFile = new File(filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioInputStream);
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
            backgroundMusic.start();
            
            currentBackgroundMusicPath = filePath;
            isPaused = false;
            
            System.out.println("Música de fundo iniciada: " + filePath);
        } catch (Exception e) {
            System.err.println("Erro ao reproduzir música de fundo: " + e.getMessage());
        }
    }

    /**
     * Para a música de fundo
     */
    public void stopBackgroundMusic() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
            backgroundMusic.close();
            backgroundMusic = null;
            isPaused = false;
        }
    }

    /**
     * Pausa a música de fundo
     */
    public void pauseBackgroundMusic() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
            isPaused = true;
        }
    }

    /**
     * Retoma a música de fundo pausada
     */
    public void resumeBackgroundMusic() {
        if (isPaused && currentBackgroundMusicPath != null) {
            playBackgroundMusic(currentBackgroundMusicPath);
        }
    }

    /**
     * Toca um efeito sonoro (não em loop)
     */
    public void playEffectSound(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            effectMusic = AudioSystem.getClip();
            effectMusic.open(audioInputStream);
            effectMusic.start();
            
            System.out.println("Efeito sonoro tocado: " + filePath);
        } catch (Exception e) {
            System.err.println("Erro ao reproduzir efeito sonoro: " + e.getMessage());
        }
    }

    /**
     * Toca um efeito sonoro pausando a música de fundo, e depois retoma
     */
    public void playEffectSoundWithPause(String filePath) {
        pauseBackgroundMusic();
        playEffectSound(filePath);
        
        // Cria uma thread para retomar a música após o efeito terminar
        if (effectMusic != null) {
            new Thread(() -> {
                try {
                    while (effectMusic.isRunning()) {
                        Thread.sleep(100);
                    }
                    Thread.sleep(500); // Pequena pausa adicional
                    resumeBackgroundMusic();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
    }

    /**
     * Para todos os sons
     */
    public void stopAllSounds() {
        stopBackgroundMusic();
        if (effectMusic != null && effectMusic.isRunning()) {
            effectMusic.stop();
            effectMusic.close();
            effectMusic = null;
        }
    }

    /**
     * Verifica se há música de fundo tocando
     */
    public boolean isBackgroundMusicPlaying() {
        return backgroundMusic != null && backgroundMusic.isRunning();
    }
}
