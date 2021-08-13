package helpers

import org.assertj.core.api.Assertions
import org.hexworks.zircon.api.application.AppConfig

object JsonTestHelper {
	val appConfigDefaultJson = """
		{ 
			"blinkLengthInMilliSeconds": 500,
			"cursorStyle": "FIXED_BACKGROUND",
			"cursorColor": {
				"red": 192, 
				"green": 192, 
				"blue": 192, 
				"alpha":255
			}, 
			"isCursorBlinking": false, 
			"isClipboardAvailable": true, 
			"defaultTileset": { 
				"id": "d12c5222-a13a-4157-965a-58156e464bcd", 
				"tileType": 0,
				"tilesetType": 0, 
				"tilesetSourceType": 1, 
				"width": 16, 
				"height": 16, 
				"path" :"/cp_437_tilesets/wanderlust_16x16.png"
			},
			"defaultGraphicalTileset": { 
				"id":"39fd5b3d-e552-4932-8c29-af97b4ee58c1", 
				"tileType": 1, 
				"tilesetType": 2, 
				"tilesetSourceType": 1, 
				"width": 16, "height": 16,
				"path": "/graphic_tilesets/nethack_16x16.zip"
				},
			"defaultColorTheme": { 
				"primaryForegroundColor": { 
					"red": 192,
					"green": 192,
					"blue": 192,
					"alpha": 255
				},
				"secondaryForegroundColor": { 
					"red": 192, 
					"green": 192,
					"blue": 192, 
					"alpha":255
				},
				"primaryBackgroundColor": {
					"red": 0,
					"green": 0,
					"blue": 0,
					"alpha": 255
				},
				"secondaryBackgroundColor": {
					"red": 0,
					"green": 0,
					"blue": 0,
					"alpha": 255
				},
				"accentColor": {
					"red": 192,
					"green": 192,
					"blue": 192,
					"alpha": 255
				}
			},
			"debugMode": false,
			"size": {
				"width": 60,
				"height": 30
			},
			"fullScreen": false,
			"betaEnabled": false,
			"title": "Zircon Application", 
			"fpsLimit": 60,
			"debugConfig": {
				"displayGrid": false,
				"displayCoordinates": false,
				"displayFps": false
			}
		}
	""".trimIndent()

	fun compareAppConfigs(actual: AppConfig?, expected: AppConfig?) {
		actual?.apply {
			Assertions.assertThat(blinkLengthInMilliSeconds).isEqualTo(expected?.blinkLengthInMilliSeconds)
			Assertions.assertThat(cursorStyle).isEqualTo(expected?.cursorStyle)
			Assertions.assertThat(isCursorBlinking).isEqualTo(expected?.isCursorBlinking)
			Assertions.assertThat(isClipboardAvailable).isEqualTo(expected?.isClipboardAvailable)
			Assertions.assertThat(debugMode).isEqualTo(expected?.debugMode)
			Assertions.assertThat(size).isEqualTo(expected?.size)
			Assertions.assertThat(fullScreen).isEqualTo(expected?.fullScreen)
			Assertions.assertThat(betaEnabled).isEqualTo(expected?.betaEnabled)
			Assertions.assertThat(title).isEqualTo(expected?.title)
			Assertions.assertThat(fpsLimit).isEqualTo(expected?.fpsLimit)

			cursorColor.apply {
				Assertions.assertThat(red).isEqualTo(expected?.cursorColor?.red)
				Assertions.assertThat(green).isEqualTo(expected?.cursorColor?.green)
				Assertions.assertThat(blue).isEqualTo(expected?.cursorColor?.blue)
				Assertions.assertThat(alpha).isEqualTo(expected?.cursorColor?.alpha)
			}

			defaultTileset.apply {
				Assertions.assertThat(tileType).isEqualTo(expected?.defaultTileset?.tileType)
				Assertions.assertThat(tilesetType).isEqualTo(expected?.defaultTileset?.tilesetType)
				Assertions.assertThat(tilesetSourceType).isEqualTo(expected?.defaultTileset?.tilesetSourceType)
				Assertions.assertThat(width).isEqualTo(expected?.defaultTileset?.width)
				Assertions.assertThat(height).isEqualTo(expected?.defaultTileset?.height)
				Assertions.assertThat(path).isEqualTo(expected?.defaultTileset?.path)
			}

			defaultGraphicalTileset.apply {
				Assertions.assertThat(tileType).isEqualTo(expected?.defaultGraphicalTileset?.tileType)
				Assertions.assertThat(tilesetType).isEqualTo(expected?.defaultGraphicalTileset?.tilesetType)
				Assertions.assertThat(tilesetSourceType).isEqualTo(expected?.defaultGraphicalTileset?.tilesetSourceType)
				Assertions.assertThat(width).isEqualTo(expected?.defaultGraphicalTileset?.width)
				Assertions.assertThat(height).isEqualTo(expected?.defaultGraphicalTileset?.height)
				Assertions.assertThat(path).isEqualTo(expected?.defaultGraphicalTileset?.path)
			}

			defaultColorTheme.apply {
				primaryForegroundColor.apply {
					Assertions.assertThat(red).isEqualTo(expected?.defaultColorTheme?.primaryForegroundColor?.red)
					Assertions.assertThat(green).isEqualTo(expected?.defaultColorTheme?.primaryForegroundColor?.green)
					Assertions.assertThat(blue).isEqualTo(expected?.defaultColorTheme?.primaryForegroundColor?.blue)
					Assertions.assertThat(alpha).isEqualTo(expected?.defaultColorTheme?.primaryForegroundColor?.alpha)
				}

				secondaryForegroundColor.apply {
					Assertions.assertThat(red).isEqualTo(expected?.defaultColorTheme?.secondaryForegroundColor?.red)
					Assertions.assertThat(green).isEqualTo(expected?.defaultColorTheme?.secondaryForegroundColor?.green)
					Assertions.assertThat(blue).isEqualTo(expected?.defaultColorTheme?.secondaryForegroundColor?.blue)
					Assertions.assertThat(alpha).isEqualTo(expected?.defaultColorTheme?.secondaryForegroundColor?.alpha)
				}

				primaryBackgroundColor.apply {
					Assertions.assertThat(red).isEqualTo(expected?.defaultColorTheme?.primaryBackgroundColor?.red)
					Assertions.assertThat(green).isEqualTo(expected?.defaultColorTheme?.primaryBackgroundColor?.green)
					Assertions.assertThat(blue).isEqualTo(expected?.defaultColorTheme?.primaryBackgroundColor?.blue)
					Assertions.assertThat(alpha).isEqualTo(expected?.defaultColorTheme?.primaryBackgroundColor?.alpha)
				}

				secondaryBackgroundColor.apply {
					Assertions.assertThat(red).isEqualTo(expected?.defaultColorTheme?.secondaryBackgroundColor?.red)
					Assertions.assertThat(green).isEqualTo(expected?.defaultColorTheme?.secondaryBackgroundColor?.green)
					Assertions.assertThat(blue).isEqualTo(expected?.defaultColorTheme?.secondaryBackgroundColor?.blue)
					Assertions.assertThat(alpha).isEqualTo(expected?.defaultColorTheme?.secondaryBackgroundColor?.alpha)
				}

				accentColor.apply {
					Assertions.assertThat(red).isEqualTo(expected?.defaultColorTheme?.accentColor?.red)
					Assertions.assertThat(green).isEqualTo(expected?.defaultColorTheme?.accentColor?.green)
					Assertions.assertThat(blue).isEqualTo(expected?.defaultColorTheme?.accentColor?.blue)
					Assertions.assertThat(alpha).isEqualTo(expected?.defaultColorTheme?.accentColor?.alpha)
				}
			}
		}
	}
}