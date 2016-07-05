/*    */ package com.mojang.ld22.item.resource;
/*    */ 
/*    */ import com.mojang.ld22.gfx.Color;
/*    */ import com.mojang.ld22.level.Level;
/*    */ import com.mojang.ld22.level.tile.Tile;
/*    */ 
/*    */ public class Resource
/*    */ {
/*  9 */   public static Resource wood = new Resource("Wood", 129, Color.get(-1, 200, 531, 430));
/* 10 */   public static Resource stone = new Resource("Stone", 130, Color.get(-1, 111, 333, 555));
/* 11 */   public static Resource flower = new PlantableResource("Flower", 128, Color.get(-1, 10, 444, 330), Tile.flower, new Tile[] { Tile.grass });
/* 12 */   public static Resource acorn = new PlantableResource("Acorn", 131, Color.get(-1, 100, 531, 320), Tile.treeSapling, new Tile[] { Tile.grass });
/* 13 */   public static Resource dirt = new PlantableResource("Dirt", 130, Color.get(-1, 100, 322, 432), Tile.dirt, new Tile[] { Tile.hole, Tile.water, Tile.lava });
/* 14 */   public static Resource sand = new PlantableResource("Sand", 130, Color.get(-1, 110, 440, 550), Tile.sand, new Tile[] { Tile.grass, Tile.dirt });
/* 15 */   public static Resource cactusFlower = new PlantableResource("Cactus", 132, Color.get(-1, 10, 40, 50), Tile.cactusSapling, new Tile[] { Tile.sand });
/* 16 */   public static Resource seeds = new PlantableResource("Seeds", 133, Color.get(-1, 10, 40, 50), Tile.wheat, new Tile[] { Tile.farmland });
/* 17 */   public static Resource wheat = new Resource("Wheat", 134, Color.get(-1, 110, 330, 550));
/* 18 */   public static Resource bread = new FoodResource("Bread", 136, Color.get(-1, 110, 330, 550), 2, 5);
/* 19 */   public static Resource apple = new FoodResource("Apple", 137, Color.get(-1, 100, 300, 500), 1, 5);
		   public static Resource taco = new FoodResource("Taco", 142, Color.get(-1, 110, 330, 550), 3, 6);
/*    */   
/* 21 */   public static Resource coal = new Resource("COAL", 138, Color.get(-1, 0, 111, 111));
/* 22 */   public static Resource ironOre = new Resource("I.ORE", 138, Color.get(-1, 100, 322, 544));
/* 23 */   public static Resource goldOre = new Resource("G.ORE", 138, Color.get(-1, 110, 440, 553));
/* 24 */   public static Resource ironIngot = new Resource("IRON", 139, Color.get(-1, 100, 322, 544));
/* 25 */   public static Resource goldIngot = new Resource("GOLD", 139, Color.get(-1, 110, 330, 553));
/*    */   
/* 27 */   public static Resource slime = new Resource("SLIME", 138, Color.get(-1, 10, 30, 50));
/* 28 */   public static Resource glass = new Resource("glass", 140, Color.get(-1, 555, 555, 555));
/* 29 */   public static Resource cloth = new Resource("cloth", 129, Color.get(-1, 25, 252, 141));
           public static Resource beef = new Resource("beef", 142, Color.get(-1, 100, 531, 320));
/* 30 */   public static Resource cloud = new PlantableResource("cloud", 130, Color.get(-1, 222, 555, 444), Tile.cloud, new Tile[] { Tile.infiniteFall });
/* 31 */   public static Resource gem = new Resource("gem", 141, Color.get(-1, 101, 404, 545));
/*    */   public final String name;
/*    */   public final int sprite;
/*    */   public final int color;
/*    */   
/*    */   public Resource(String name, int sprite, int color)
/*    */   {
/* 38 */     if (name.length() > 6) throw new RuntimeException("Name cannot be longer than six characters!");
/* 39 */     this.name = name;
/* 40 */     this.sprite = sprite;
/* 41 */     this.color = color;
/*    */   }
/*    */   
/*    */   public boolean interactOn(Tile tile, Level level, int xt, int yt, com.mojang.ld22.entity.Player player, int attackDir) {
/* 45 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\item\resource\Resource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */