package Music;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/** 
 * A classe <i>Music</i> é responsável pelo controle completo do sistema de áudio do jogo.
 * Esta classe gerencia tanto a música de fundo quanto os efeitos sonoros, proporcionando
 * uma experiência sonora imersiva durante o gameplay.
 * <ul>
 * <li>Controla música de fundo com reprodução em loop contínuo
 * <li>Gerencia efeitos sonoros independentes da música principal
 * <li>Implementa sistema de pausa e retomada da música de fundo
 * <li>Fornece funcionalidade de efeitos sonoros com pausa automática da música
 * <li>Oferece controle total sobre todos os sons ativos no jogo
 * <li>Trata exceções de áudio para garantir estabilidade do sistema
 * <li>Utiliza threads para sincronização entre música e efeitos sonoros
 * </ul>  
 */
public class Music {

    private Clip backgroundMusic;
    private Clip effectMusic;
    private boolean isPaused = false;
    private String currentBackgroundMusicPath;

    /**
     * Construtor padrão da classe Music.
     * Inicializa o sistema de música sem reproduzir nenhum áudio,
     * preparando o gerenciador para futuras operações de som.
     */
    public Music() {}

    /**
     * Reproduz uma música de fundo em loop contínuo.
     * Para qualquer música anterior e inicia a nova música especificada.
     * A música tocará continuamente até ser interrompida manualmente.
     * 
     * @param filePath caminho para o arquivo de áudio (.wav) a ser reproduzido
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
     * Para completamente a música de fundo.
     * Interrompe a reprodução e libera os recursos de áudio utilizados.
     * Redefine o estado de pausa para falso.
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
     * Pausa temporariamente a música de fundo.
     * Mantém o estado atual para permitir retomada posterior no mesmo ponto.
     * A música pode ser retomada usando resumeBackgroundMusic().
     */
    public void pauseBackgroundMusic() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
            isPaused = true;
        }
    }

    /**
     * Retoma a música de fundo previamente pausada.
     * Reinicia a reprodução da última música que estava tocando,
     * desde que tenha sido pausada anteriormente.
     */
    public void resumeBackgroundMusic() {
        if (isPaused && currentBackgroundMusicPath != null) {
            playBackgroundMusic(currentBackgroundMusicPath);
        }
    }

    /**
     * Reproduz um efeito sonoro único (sem loop).
     * O efeito é tocado uma vez e não interfere na música de fundo.
     * Ideal para sons de ações pontuais como cliques ou notificações.
     * 
     * @param filePath caminho para o arquivo de áudio do efeito sonoro
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
     * Reproduz um efeito sonoro pausando temporariamente a música de fundo.
     * Pausa a música atual, toca o efeito especificado e automaticamente
     * retoma a música após o término do efeito. Utiliza threading para
     * sincronização automática.
     * 
     * @param filePath caminho para o arquivo de áudio do efeito sonoro
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
     * Para todos os sons ativos no sistema.
     * Interrompe tanto a música de fundo quanto qualquer efeito sonoro
     * em reprodução, liberando todos os recursos de áudio.
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
     * Verifica se há música de fundo sendo reproduzida atualmente.
     * Útil para verificar o estado do sistema de áudio antes de
     * executar outras operações musicais.
     * 
     * @return boolean true se há música de fundo tocando, false caso contrário
     */
    public boolean isBackgroundMusicPlaying() {
        return backgroundMusic != null && backgroundMusic.isRunning();
    }
}
