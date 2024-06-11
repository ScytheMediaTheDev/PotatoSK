package com.mcdragonmasters.myaddon.skript.effects;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.util.Kleenean;
import org.bukkit.plugin.Plugin;
import org.bukkit.Server;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.bukkit.permissions.Permissible;

import javax.security.auth.kerberos.KerberosTicket;

import static org.bukkit.Bukkit.getServer;

// Documentation
@Name("Command With Permission")
@Description("Make a Player execute a command with a permission")
@Examples({ "none rn \t test" })
@Since("0.1.0")

public class EffCommandWithPermission extends Effect{

    static {
        Skript.registerEffect(EffCommandWithPermission.class,
                "execute %player% command %string% with permission %string%"
        );
    }
    private Expression<String> command;
    private Expression<String> perm;
    private Expression<Player> effplayer;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, @NotNull ParseResult parseResult) {
        effplayer = (Expression<Player>) exprs[1];
        command = (Expression<String>) exprs[2];
        perm = (Expression<String>) exprs[3];
        return true;
    }
    @Override
    protected void execute(Event e) {
        Plugin skript = getServer().getPluginManager().getPlugin("Skript");
        Player player = (Player) effplayer;
        player.addAttachment(skript, String.valueOf(perm), true, 1);
        System.out.println("test effect used");

    }

    @Override
    public @NotNull String toString(Event e, boolean debug) {
        return "execute " + effplayer + " command " + command + "with permission " + perm; // In case of using a variable, use your variable as "yourvariable.toString(e, debug)"
    }

}

