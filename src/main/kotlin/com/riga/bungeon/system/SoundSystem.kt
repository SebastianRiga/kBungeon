package com.riga.bungeon.system

import com.riga.bungeon.meta.extensions.schedule
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip

/**
 * Base object to handle the interactions with sound files.
 *
 * Supported file formats: `wav`, `ogg`
 *
 * Please note: All mpeg formats are currently unsupported!
 */
object SoundSystem {

	/**
	 * Backing field for the background music [Clip].
	 */
	private var backgroundMusic: Clip? = null

	/**
	 * Internal convenience function to crate a new audio [Clip] from the
	 * `JVM`'s [AudioSystem]. To load the audio file as an [AudioInputStream]
	 * and pass it to the [Clip] the [ResourceLoader.loadAudio] function is used.
	 * 
	 * @param name The name of the audio file to be played. Important: Name only, no path or extension!
	 * @param loop Flag to continuously loop the audio clip. Default false.
	 * 
	 * @see ResourceLoader.loadAudio
	 */
	private fun newClip(name: String, loop: Boolean = false): Clip = AudioSystem.getClip().also {
		ResourceLoader.loadAudio(name).apply {
			it.open(this)
	
			if (loop) {
				it.loop(Clip.LOOP_CONTINUOUSLY)
			}
		}
	}

	/**
	 * Stops the currently running background music and creates a new [Clip]
	 * based on the supplied music name and starts it.
	 * 
	 * The loop parameter is set to true, since background
	 * music needs to repeat.
	 * 
	 * @param name The name of the audio file to be played as background music. 
	 * Important: Name only, no path or extension!
	 * 
	 * @see backgroundMusic
	 * @see stopBackGroundMusic
	 */
	fun playBackgroundMusic(name: String) {
		stopBackGroundMusic().also {
			backgroundMusic = newClip(name, true).also {
				it.start()
			}
		}
	}

	/**
	 * Stops the currently running background music, closes the
	 * underlying [AudioInputStream] and flushes it.
	 * 
	 * Note: This makes the existing background music [Clip] unusable!
	 */
	fun stopBackGroundMusic() = backgroundMusic?.let { 
		it.stop()
		it.close()
		it.flush()
	}

	/**
	 * 
	 */
	fun playSoundEffect(name: String) = newClip(name).start()
	
	fun playSoundEffectDelayed(name: String, delay: Long = 1000L) = schedule(delay, "Delaying sound effect") {
		playSoundEffect(name)
	} 
}