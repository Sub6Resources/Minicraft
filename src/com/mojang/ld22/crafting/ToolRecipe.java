/*    */ package com.mojang.ld22.crafting;
/*    */ 
/*    */ import com.mojang.ld22.item.ToolType;
/*    */ 
/*    */ public class ToolRecipe extends Recipe
/*    */ {
/*    */   private ToolType type;
/*    */   private int level;
/*    */   
/*    */   public ToolRecipe(ToolType type, int level)
/*    */   {
/* 12 */     super(new com.mojang.ld22.item.ToolItem(type, level));
/* 13 */     this.type = type;
/* 14 */     this.level = level;
/*    */   }
/*    */   
/*    */   public void craft(com.mojang.ld22.entity.Player player) {
/* 18 */     player.inventory.add(0, new com.mojang.ld22.item.ToolItem(this.type, this.level));
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\crafting\ToolRecipe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */