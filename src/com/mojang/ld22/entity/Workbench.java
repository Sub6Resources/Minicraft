/*    */ package com.mojang.ld22.entity;
/*    */ 
/*    */ import com.mojang.ld22.screen.CraftingMenu;
/*    */ 
/*    */ public class Workbench extends Furniture
/*    */ {
/*    */   public Workbench()
/*    */   {
/*  9 */     super("Workbench");
/* 10 */     this.col = com.mojang.ld22.gfx.Color.get(-1, 100, 321, 431);
/* 11 */     this.sprite = 4;
/* 12 */     this.xr = 3;
/* 13 */     this.yr = 2;
/*    */   }
/*    */   
/*    */   public boolean use(Player player, int attackDir) {
/* 17 */     player.game.setMenu(new CraftingMenu(com.mojang.ld22.crafting.Crafting.workbenchRecipes, player));
/* 18 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\entity\Workbench.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */