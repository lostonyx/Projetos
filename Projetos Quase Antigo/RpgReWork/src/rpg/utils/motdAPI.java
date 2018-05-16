package rpg.utils;

import org.bukkit.plugin.Plugin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedServerPing;

public class motdAPI {
	static ProtocolManager pmr;
	static Plugin pl;
	static String versionname, motddesc;
	static int versionprotocol;

	public motdAPI(ProtocolManager pmr, Plugin pl) {
		this.pmr = pmr;
		this.pl = pl;
	}

	public static motdAPI setVersionProtocol(int version) {
		versionprotocol = version;
		return null;
	}

	public static motdAPI setVersionName(String versionstring) {
		versionname = versionstring;
		return null;
	}

	public static motdAPI setMotD(String desc) {
		motddesc = desc;
		return null;
	}

	public static void send() {
		pmr.addPacketListener(new PacketAdapter(pl, PacketType.Status.Server.OUT_SERVER_INFO) {
			@Override
			public void onPacketSending(PacketEvent event) {
				WrappedServerPing ping = event.getPacket().getServerPings().read(0);
				if (versionname != null) {
					ping.setVersionName(versionname);
				} else if (versionprotocol != 0) {
					ping.setVersionProtocol(versionprotocol);
				} else if (motddesc != null) {
					ping.setMotD(motddesc);
				}
			}
		});
	}

}
