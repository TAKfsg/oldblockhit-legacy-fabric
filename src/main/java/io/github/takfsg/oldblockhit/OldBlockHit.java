package io.github.takfsg.oldblockhit;

import io.github.takfsg.oldblockhit.config.Config;
import net.fabricmc.api.ModInitializer;

public class OldBlockHit implements ModInitializer {

	@Override
	public void onInitialize() {
		Config.init();
	}
}
