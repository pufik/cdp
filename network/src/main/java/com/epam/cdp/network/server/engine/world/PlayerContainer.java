package com.epam.cdp.network.server.engine.world;

import com.epam.cdp.network.server.engine.message.Listener;
import com.epam.cdp.network.server.engine.message.Message;
import com.epam.cdp.network.server.engine.player.Player;

public interface PlayerContainer {

	void addPlayer(Player player);

	void registerListener(Listener listener);
	
	void removeListener(Listener listener);

	void sendMessage(Message message);
}
