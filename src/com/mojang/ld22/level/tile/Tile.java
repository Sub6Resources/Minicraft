/*    */ package com.mojang.ld22.level.tile;
/*    */ 
/*    */ import com.mojang.ld22.entity.Entity;
/*    */ import com.mojang.ld22.entity.Mob;
/*    */ import com.mojang.ld22.entity.Player;
/*    */ import com.mojang.ld22.gfx.Screen;
/*    */ import com.mojang.ld22.item.Item;
/*    */ import com.mojang.ld22.item.resource.Resource;
/*    */ import com.mojang.ld22.level.Level;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class Tile
/*    */ {
/* 14 */   public static int tickCount = 0;
/* 15 */   protected Random random = new Random();
/*    */   
/* 17 */   public static Tile[] tiles = new Tile[256];
/* 18 */   public static Tile grass = new GrassTile(0);
/* 19 */   public static Tile rock = new RockTile(1);
/* 20 */   public static Tile water = new WaterTile(2);
/* 21 */   public static Tile flower = new FlowerTile(3);
/* 22 */   public static Tile tree = new TreeTile(4);
/* 23 */   public static Tile dirt = new DirtTile(5);
/* 24 */   public static Tile sand = new SandTile(6);
/* 25 */   public static Tile cactus = new CactusTile(7);
/* 26 */   public static Tile hole = new HoleTile(8);
/* 27 */   public static Tile treeSapling = new SaplingTile(9, grass, tree);
/* 28 */   public static Tile cactusSapling = new SaplingTile(10, sand, cactus);
/* 29 */   public static Tile farmland = new FarmTile(11);
/* 30 */   public static Tile wheat = new WheatTile(12);
/* 31 */   public static Tile lava = new LavaTile(13);
/* 32 */   public static Tile stairsDown = new StairsTile(14, false);
/* 33 */   public static Tile stairsUp = new StairsTile(15, true);
/* 34 */   public static Tile infiniteFall = new InfiniteFallTile(16);
/* 35 */   public static Tile cloud = new CloudTile(17);
/* 36 */   public static Tile hardRock = new HardRockTile(18);
/* 37 */   public static Tile ironOre = new OreTile(19, Resource.ironOre);
/* 38 */   public static Tile goldOre = new OreTile(20, Resource.goldOre);
/* 39 */   public static Tile gemOre = new OreTile(21, Resource.gem);
/* 40 */   public static Tile cloudCactus = new CloudCactusTile(22);
/*    */   
/*    */   public final byte id;
/*    */   
/* 44 */   public boolean connectsToGrass = false;
/* 45 */   public boolean connectsToSand = false;
/* 46 */   public boolean connectsToLava = false;
/* 47 */   public boolean connectsToWater = false;
/*    */   
/*    */   public Tile(int id) {
/* 50 */     this.id = ((byte)id);
/* 51 */     if (tiles[id] != null) throw new RuntimeException("Duplicate tile ids!");
/* 52 */     tiles[id] = this;
/*    */   }
/*    */   
/*    */   public void render(Screen screen, Level level, int x, int y) {}
/*    */   
/*    */   public boolean mayPass(Level level, int x, int y, Entity e)
/*    */   {
/* 59 */     return true;
/*    */   }
/*    */   
/*    */   public int getLightRadius(Level level, int x, int y) {
/* 63 */     return 0;
/*    */   }
/*    */   
/*    */ 
/*    */   public void hurt(Level level, int x, int y, Mob source, int dmg, int attackDir) {}
/*    */   
/*    */ 
/*    */   public void bumpedInto(Level level, int xt, int yt, Entity entity) {}
/*    */   
/*    */ 
/*    */   public void tick(Level level, int xt, int yt) {}
/*    */   
/*    */   public void steppedOn(Level level, int xt, int yt, Entity entity) {}
/*    */   
/*    */   public boolean interact(Level level, int xt, int yt, Player player, Item item, int attackDir)
/*    */   {
/* 79 */     return false;
/*    */   }
/*    */   
/*    */   public boolean use(Level level, int xt, int yt, Player player, int attackDir) {
/* 83 */     return false;
/*    */   }
/*    */   
/*    */   public boolean connectsToLiquid() {
/* 87 */     return (this.connectsToWater) || (this.connectsToLava);
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\level\tile\Tile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */