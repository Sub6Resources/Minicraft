/*    */ package com.mojang.ld22.entity;
/*    */ 
/*    */ import com.mojang.ld22.screen.CraftingMenu;
/*    */ 
/*    */ public class Furnace extends Furniture
/*    */ {
/*    */   public Furnace()
/*    */   {
/*  9 */     super("Furnace");
/* 10 */     this.col = com.mojang.ld22.gfx.Color.get(-1, 0, 222, 333);
/* 11 */     this.sprite = 3;
/* 12 */     this.xr = 3;
/* 13 */     this.yr = 2;
/*    */   }
/*    */   
/*    */   public boolean use(Player player, int attackDir) {
/* 17 */     player.game.setMenu(new CraftingMenu(com.mojang.ld22.crafting.Crafting.furnaceRecipes, player));
/* 18 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\entity\Furnace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */