/*    */ package com.mojang.ld22.crafting;
/*    */ 
/*    */ import com.mojang.ld22.entity.Inventory;
/*    */ import com.mojang.ld22.entity.Player;
/*    */ import com.mojang.ld22.item.ResourceItem;
/*    */ import com.mojang.ld22.item.resource.Resource;
/*    */ 
/*    */ public class ResourceRecipe extends Recipe
/*    */ {
/*    */   private Resource resource;
/*    */   
/*    */   public ResourceRecipe(Resource resource)
/*    */   {
/* 14 */     super(new ResourceItem(resource, 1));
/* 15 */     this.resource = resource;
/*    */   }
/*    */   
/*    */   public void craft(Player player) {
/* 19 */     player.inventory.add(0, new ResourceItem(this.resource, 1));
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\crafting\ResourceRecipe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */