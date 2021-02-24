package hbaproject.hbaproject;

import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Hyunsolapi {
    public void openGUI(Player p, int slot, String name) {
        PlayerGlobal.inv.put(p.getUniqueId(),Bukkit.createInventory(null, slot, name));
        p.openInventory(PlayerGlobal.inv.get(p.getUniqueId()));
    }

    public void putItem(Player p, int slot, ItemStack item) {
        PlayerGlobal.inv.get(p.getUniqueId()).setItem(slot,item);
    }

    public void clearGUI(Player p,int slot) {
        ItemStack item = new ItemStack(Material.AIR);
        for(int i=0;i<slot;i++){
            PlayerGlobal.inv.get(p.getUniqueId()).setItem(i,item);
        }
    }

    public String getBetweenStr(String str, int startIndex, int endIndex) {
        String result="";

        for(int i=startIndex;i<endIndex;i++){
            try {
                result += str.charAt(i);
            } catch (Exception e){
                break;
            }
        }

        return result;
    }

    public String[] getTime() {
        SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd-HH-mm-ss");
        Date time = new Date();

        return format.format(time).split("-");
    }

    public void setBlock(Location loc, Material mat) { World world = loc.getWorld(); Block b = world.getBlockAt(loc); b.setType(mat); }

    public void fill(Location loc1, Location loc2, Material m) {
        int x1 = Integer.parseInt(""+Math.round(loc1.getX())); int x2 = Integer.parseInt(""+Math.round(loc2.getX()));
        int y1 = Integer.parseInt(""+Math.round(loc1.getY())); int y2 = Integer.parseInt(""+Math.round(loc2.getY()));
        int z1 = Integer.parseInt(""+Math.round(loc1.getZ())); int z2 = Integer.parseInt(""+Math.round(loc2.getZ()));

        // 반복문 변수 선언
        int I_X; int G_X;
        int I_Y; int G_Y;
        int I_Z; int G_Z;

        // 시작지점 및 기준 정의 ================
        if(x1 > x2) { I_X = x2; G_X = x1; } else { I_X=x1; G_X=x2; }
        if(y1 > y2) { I_Y = y2; G_Y = y1; } else { I_Y=y1; G_Y=y2; }
        if(z1 > z2) { I_Z = z2; G_Z = z1; } else { I_Z=z1; G_Z=z2; }

        // 반복문 시작 ========================
        for(int x=I_X;x < (G_X+1);x++){
            for(int y=I_Y;y < (G_Y+1);y++){
                for(int z=I_Z;z < (G_Z+1);z++){
                    setBlock(new Location(loc1.getWorld(), x,y,z), m);
                }
            }
        }
    }

    public boolean isBetween(int GiJun, int num1, int num2) {
        for(int i=num1;i<num2;i++){
            if(GiJun == i) return true;
        }
        return false;

        // isBetween : GiJun이 num1~num2 사이에 있는 정수인지를 확인 후 boolean을 반환.
    }

    public boolean isNearPlayer(Player me, Player targetPlayer, double x, double y, double z){
        for(Entity e: me.getLocation().getNearbyEntities(x,y,z)){
            if(e.getName().equals(targetPlayer.getName())) return true;
        }
        return false;

        // isNeraPlayer : 근처에 특정 플레이어가 있는지를 확인 후 boolean을 반환.
    }

    public ArrayList<Block> getNearBlock(Location loc, int radius, Boolean containsAir) {
        ArrayList<Block> blocks = new ArrayList<Block>();

        for (int x = (loc.getBlockX()-radius); x <= (loc.getBlockX()+radius); x++) {
            for (int y = (loc.getBlockY()-radius); y <= (loc.getBlockY()+radius); y++) {
                for (int z = (loc.getBlockZ()-radius); z <= (loc.getBlockZ()+radius); z++) {
                    Location l = new Location(loc.getWorld(), x, y, z);
                    if (l.distance(loc) <= radius) {
                        if(containsAir && l.getBlock().getType() == Material.AIR){
                            blocks.add(l.getBlock());
                        }

                        blocks.add(l.getBlock());
                    }
                }
            }
        }

        return blocks;

        // getNearBlock : 특정 위치의 radius안에 있는 블럭들을 반환함.
    }

    public Boolean isNearBlock(Location loc, Material b, int radius) {
        for(Block tmp : getNearBlock(loc,radius,false)){
            if(tmp.getType() == b) return true;
        }
        return false;

        // isNearBlock : 특정 위치의 radius안에 특정 블럭이 있는지 확인하고 Boolean값을 반환함.
    }

    public static String getPlayerBiome(Player p) {
        Location loc = p.getLocation();

        return ""+loc.getWorld().getBiome((int)(Math.round(loc.getX())),(int)(Math.round(loc.getY())),(int)(Math.round(loc.getZ())));
    }

    public boolean isWithin(Location targetLoc, Location pos1, Location pos2) {
        // 타깃 플레이어 위치 x,y,z 정의
        int x = Integer.parseInt(""+Math.round(targetLoc.getX()));
        int y = Integer.parseInt(""+Math.round(targetLoc.getY()));
        int z = Integer.parseInt(""+Math.round(targetLoc.getZ()));

        // loc1,2 위치 x,y,z 정의
        int x1 = Integer.parseInt(""+Math.round(pos1.getX())); int x2 = Integer.parseInt(""+Math.round(pos2.getX()));
        int y1 = Integer.parseInt(""+Math.round(pos1.getY())); int y2 = Integer.parseInt(""+Math.round(pos2.getY()));
        int z1 = Integer.parseInt(""+Math.round(pos1.getZ())); int z2 = Integer.parseInt(""+Math.round(pos2.getZ()));

        if(((x1 > x && x2 < x) || (x1 < x && x2 > x) || (x1 == x || x2 == x))){
            if(((y1 > y && y2 < y) || (y1 < y && y2 > y)) || (y1 == y || y2 == y)){
                if(((z1 > z && z2 < z) || (z1 < z && z2 > z)) || (z1 == z || z2 == z)){
                    return true;
                }
            }
        }
        return false;

        // isWithin : targetPlayer 가 loc1,2 사이에 있는지 확인후 boolean을 반환함.
    }

    public void bs(String str) {
        Bukkit.getConsoleSender().sendMessage(str);

        // bs : 버킷에게 메세지를 보냄. (Bukkit, sendMessage 의 약자)
    }

    public void push(Player p, int m) {
        p.setVelocity(p.getLocation().getDirection().multiply(m));
    }

    public void sendMessage(Player p, String msg, String clickEvent, String hoverEvent){
        TextComponent message = new TextComponent(msg);
        if(!clickEvent.equals("")){
            message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, clickEvent));
        }
        if(!hoverEvent.equals("")){
            message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hoverEvent).create()));
        }
        p.sendMessage(message);
    }

    public boolean isNum(String str) {
        try {
            double d = Double.parseDouble(str);
            return true;
        } catch (Exception e){
            return false;
        }

        // isNum : 문자열 str이 실수로 호환되는지 확인 후 boolean값을 반환함.
    }

    // HashMap Save & Load ===================================
    public String sans;

    private String ObjectToByte(Object e) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
            dataOutput.writeObject(e);
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private Object ByteToObject(String str) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(str));
        try {
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            dataInput.close();
            return dataInput.readObject();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public void save(HashMap map, String fileName) {
        File f = new File(HBAProject.getInstace().getDataFolder()+"/"+fileName+".hyun");

        try {
            FileWriter w = new FileWriter(f);
            map.forEach((key, value) -> {

                try {
                    w.write(ObjectToByte(key)+"|"+ObjectToByte(value)+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });

            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(HashMap map, String fileName) {
        String line = "";

        File f = new File(HBAProject.getInstace().getDataFolder()+"/"+fileName);

        try {
            FileReader r = new FileReader(HBAProject.getInstace().getDataFolder()+"/"+fileName+".hyun");
            BufferedReader buf = new BufferedReader(r);
            String readString = "";

            while((line = buf.readLine()) != null) {
                readString += line;
            }

            map.put(ByteToObject(readString.split("\\|")[0]),ByteToObject(readString.split("\\|")[1]));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
