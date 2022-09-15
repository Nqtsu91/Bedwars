package fr.natsu.bedwars.utils;

import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Tool, to send 1.8 over-hotbar-chatmessages to players.
 * And this even constantly!
 * Requires at least Spigot 1.8 Snapshot1!
 * Example: http://puu.sh/daHjL/1ce637d81b.png
 *
 * @author Zeno
 */
public class HotbarMessage {

    /**
     * A location of constant messages.
     */
    private HashMap<Player, String> constantMessages;

    /**
     * Constructor
     */
    public HotbarMessage() {
        this.constantMessages = new HashMap<>();
        this.startSending();
    }

    /**
     * Sends a packet with formatted text to a player.
     * Displayed above the hotbar.
     *
     * @param target Player, the packet has to be sent to.
     * @param message Formatted text to be sent.
     */
    public static void send( Player target, String message ) {
        PacketPlayOutChat packet = new PacketPlayOutChat();
        setValue(packet, "a", ChatSerializer.a("{\"text\":\"" + message + "\",\"color\":\"white\"}"));
        setValue(packet, "b", (byte) 2);
        ((CraftPlayer)target).getHandle().playerConnection.sendPacket(packet);
    }

    /**
     * Sends an empty message to the player, in order to remove the latest message.
     *
     * @param target Player, the packet has to be sent to.
     */
    public static void clear( Player target ) {
        send(target, " ");
    }

    /**
     * Sets a message permanently for a certain player.
     *
     * @param target Player, the packet will be send to.
     * @param message Message, which constantly will  be sent.
     */
    public void setStaticMessage( Player target, String message ) {
        clear(target);
        this.constantMessages.put(target, message);
        send(target, message);
    }

    /**
     * Clears the latest message and removes the player from the constant messages list.
     *
     * @param target Player, of whom the packets won't be sent anymore.
     */
    public void removeStaticMessage( Player target ) {
        clear(target);
        this.constantMessages.remove(target);
    }

    /**
     * Starts repeating sending constant messages.
     */
    private void startSending() {
        new Thread() {
            int state = 0;
            @Override
            public void run() {
                while ( true ) {
                    for (Player player : constantMessages.keySet())
                        send(player, (state == 0 ? "§r" : "") + constantMessages.get(player));
                    state++;
                    if (state > 1)
                        state = 0;
                    try {
                        Thread.sleep(250L);
                    } catch( InterruptedException e ) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    private static void setValue( Object instance, String fieldName, Object value ) {
        try {
            Field field = instance.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(instance, value);
        } catch(NoSuchFieldException e) {
            e.printStackTrace();
        } catch(IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}