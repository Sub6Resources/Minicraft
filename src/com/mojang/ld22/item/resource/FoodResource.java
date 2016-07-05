/*    */ package com.mojang.ld22.item.resource;
/*    */ 
/*    */ import com.mojang.ld22.entity.Player;
/*    */ 
/*    */ public class FoodResource extends Resource
/*    */ {
/*    */   private int heal;
/*    */   private int staminaCost;
/*    */   
/*    */   public FoodResource(String name, int sprite, int color, int heal, int staminaCost)
/*    */   {
/* 12 */     super(name, sprite, color);
/* 13 */     this.heal = heal;
/* 14 */     this.staminaCost = staminaCost;
/*    */   }
/*    */   
/*    */   public boolean interactOn(com.mojang.ld22.level.tile.Tile tile, com.mojang.ld22.level.Level level, int xt, int yt, Player player, int attackDir) {
/* 18 */     if ((player.health < player.maxHealth) && (player.payStamina(this.staminaCost))) {
/* 19 */       player.heal(this.heal);
/* 20 */       return true;
/*    */     }
/* 22 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\item\resource\FoodResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */