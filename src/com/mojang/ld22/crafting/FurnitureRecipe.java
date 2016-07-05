/*    */ package com.mojang.ld22.crafting;
/*    */ 
/*    */ import com.mojang.ld22.entity.Furniture;
/*    */ 
/*    */ public class FurnitureRecipe extends Recipe
/*    */ {
/*    */   private Class<? extends Furniture> clazz;
/*    */   
/*    */   public FurnitureRecipe(Class<? extends Furniture> clazz) throws InstantiationException, IllegalAccessException
/*    */   {
/* 11 */     super(new com.mojang.ld22.item.FurnitureItem((Furniture)clazz.newInstance()));
/* 12 */     this.clazz = clazz;
/*    */   }
/*    */   
/*    */   public void craft(com.mojang.ld22.entity.Player player) {
/*    */     try {
/* 17 */       player.inventory.add(0, new com.mojang.ld22.item.FurnitureItem((Furniture)this.clazz.newInstance()));
/*    */     } catch (Exception e) {
/* 19 */       throw new RuntimeException(e);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\crafting\FurnitureRecipe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */