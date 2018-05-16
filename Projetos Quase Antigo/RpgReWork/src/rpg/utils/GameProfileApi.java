package rpg.utils;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.HttpAuthenticationService;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilMinecraftSessionService;
import com.mojang.authlib.yggdrasil.response.MinecraftProfilePropertiesResponse;
import com.mojang.util.UUIDTypeAdapter;


import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;

import java.lang.reflect.Method;
import java.net.URL;
public class GameProfileApi {
	public static GameProfile fillProfileProperties(GameProfile profile,
			boolean requireSecure) throws Exception {
			
			//if (!Bukkit.isPrimaryThread())
			//throw new IllegalStateException("NMS.fillProfileProperties cannot be invoked from the main thread.");
			
			MinecraftSessionService sessionService = ((CraftServer) Bukkit.getServer()).getServer().aD();
			
			YggdrasilAuthenticationService auth = ((YggdrasilMinecraftSessionService) sessionService)
			.getAuthenticationService();
			
			URL url = HttpAuthenticationService.constantURL(
			"https://sessionserver.mojang.com/session/minecraft/profile/" +
			UUIDTypeAdapter.fromUUID(profile.getId()));
			
			url = HttpAuthenticationService.concatenateURL(url, "unsigned=" + !requireSecure);
			Method MAKE_REQUEST = null;
			try {
			    MAKE_REQUEST = YggdrasilAuthenticationService.class.getDeclaredMethod("makeRequest", URL.class,
			            Object.class, Class.class);
			    MAKE_REQUEST.setAccessible(true);
			} catch (Exception ex) {
			    ex.printStackTrace();
			}
			MinecraftProfilePropertiesResponse response = (MinecraftProfilePropertiesResponse)
			MAKE_REQUEST.invoke(auth, url, null, MinecraftProfilePropertiesResponse.class);
			if (response == null)
			return profile;
			
			GameProfile result = new GameProfile(profile.getId(), profile.getName());
			result.getProperties().putAll(response.getProperties());
			profile.getProperties().putAll(response.getProperties());
			
			return result;
	}
}
