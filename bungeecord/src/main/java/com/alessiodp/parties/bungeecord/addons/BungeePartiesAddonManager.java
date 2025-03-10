package com.alessiodp.parties.bungeecord.addons;

import com.alessiodp.core.bungeecord.addons.external.BungeeRedisBungeeHandler;
import com.alessiodp.parties.bungeecord.addons.external.BungeeAdvancedBanHandler;
import com.alessiodp.parties.bungeecord.addons.external.BungeeChatHandler;
import com.alessiodp.parties.bungeecord.addons.external.PartiesBungeeRedisBungeeHandler;
import com.alessiodp.parties.bungeecord.addons.external.PremiumVanishHandler;
import com.alessiodp.parties.bungeecord.configuration.data.BungeeConfigMain;
import com.alessiodp.parties.common.PartiesPlugin;
import com.alessiodp.parties.common.addons.PartiesAddonManager;

public class BungeePartiesAddonManager extends PartiesAddonManager {
	private final BungeeAdvancedBanHandler advancedBanHandler;
	private final BungeeChatHandler bungeeChat;
	private final PremiumVanishHandler premiumVanish;
	private final BungeeRedisBungeeHandler redisBungee;
	
	public BungeePartiesAddonManager(PartiesPlugin plugin) {
		super(plugin);
		
		advancedBanHandler = new BungeeAdvancedBanHandler(plugin);
		bungeeChat = new BungeeChatHandler(plugin);
		premiumVanish = new PremiumVanishHandler(plugin);
		redisBungee = new PartiesBungeeRedisBungeeHandler(plugin);
	}
	
	@Override
	public void loadAddons() {
		super.loadAddons();
		
		redisBungee.init(BungeeConfigMain.PARTIES_BUNGEECORD_REDIS);
		
		// Schedule sync later (post load plugins)
		plugin.getScheduler().getSyncExecutor().execute(this::postLoadAddons);
	}
	
	public void postLoadAddons() {
		advancedBanHandler.init();
		bungeeChat.init();
		premiumVanish.init();
	}
}
