package com.epam.cdp.network.server.engine.world;

import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import com.epam.cdp.network.server.engine.message.Listener;
import com.epam.cdp.network.server.engine.message.Message;
import com.epam.cdp.network.server.engine.player.Player;

public class World implements PlayerContainer {

	private static final long worlSize = 100;

	private Map<Long, Map<Long, Player>> map;

	private Set<Listener> listeners;

	public World() {
		map = new TreeMap<Long, Map<Long, Player>>();
		listeners = new HashSet<Listener>();
	}

	public void addPlayer(Player player) {
		choosePositionForPlayer(player);
		registerListener(player);
	}

	private void choosePositionForPlayer(Player player) {
		long x = generatePosition();
		long y;
		Map<Long, Player> xPlayers = map.get(Long.valueOf(x));

		if (xPlayers != null) {
			Player yPlayer;
			do {
				y = generatePosition();
				yPlayer = xPlayers.get(Long.valueOf(y));
			} while (yPlayer != null);

			xPlayers.put(Long.valueOf(y), player);
		} else {
			xPlayers = new TreeMap<Long, Player>();
			y = generatePosition();
			xPlayers.put(Long.valueOf(y), player);
			map.put(Long.valueOf(x), xPlayers);
		}
	}

	private long generatePosition() {
		return new Random(worlSize).nextLong();
	}

	@Override
	public void registerListener(Listener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(Listener listener) {
		listeners.remove(listener);
	}

	@Override
	public void sendMessage(Message message) {
		for (Listener listener : listeners) {
			listener.sendMessage(message);
		}
	}
}
