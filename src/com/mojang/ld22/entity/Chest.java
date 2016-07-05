/*    */ package com.mojang.ld22.entity;
/*    */ 
/*    */ import com.mojang.ld22.Game;
/*    */ 
/*    */ public class Chest extends Furniture
/*    */ {
/*  7 */   public Inventory inventory = new Inventory();
/*    */   
/*    */   public Chest() {
/* 10 */     super("Chest");
/* 11 */     this.col = com.mojang.ld22.gfx.Color.get(-1, 110, 331, 552);
/* 12 */     this.sprite = 1;
/*    */   }
/*    */   
/*    */   public boolean use(Player player, int attackDir) {
/* 16 */     player.game.setMenu(new com.mojang.ld22.screen.ContainerMenu(player, "Chest", this.inventory));
/* 17 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\entity\Chest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */