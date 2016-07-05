/*    */ package com.mojang.ld22.item.resource;
/*    */ 
/*    */ import com.mojang.ld22.entity.Player;
/*    */ import com.mojang.ld22.level.Level;
/*    */ import com.mojang.ld22.level.tile.Tile;
/*    */ import java.util.List;
/*    */ 
/*    */ public class PlantableResource extends Resource
/*    */ {
/*    */   private List<Tile> sourceTiles;
/*    */   private Tile targetTile;
/*    */   
/*    */   public PlantableResource(String name, int sprite, int color, Tile targetTile, Tile... sourceTiles1)
/*    */   {
/* 15 */     this(name, sprite, color, targetTile, java.util.Arrays.asList(sourceTiles1));
/*    */   }
/*    */   
/*    */   public PlantableResource(String name, int sprite, int color, Tile targetTile, List<Tile> sourceTiles) {
/* 19 */     super(name, sprite, color);
/* 20 */     this.sourceTiles = sourceTiles;
/* 21 */     this.targetTile = targetTile;
/*    */   }
/*    */   
/*    */   public boolean interactOn(Tile tile, Level level, int xt, int yt, Player player, int attackDir) {
/* 25 */     if (this.sourceTiles.contains(tile)) {
/* 26 */       level.setTile(xt, yt, this.targetTile, 0);
/* 27 */       return true;
/*    */     }
/* 29 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\item\resource\PlantableResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */