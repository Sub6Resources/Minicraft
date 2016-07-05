/*    */ package com.mojang.ld22.crafting;
/*    */ 
/*    */ import com.mojang.ld22.entity.Inventory;
/*    */ import com.mojang.ld22.entity.Player;
/*    */ import com.mojang.ld22.gfx.Color;
/*    */ import com.mojang.ld22.gfx.Font;
/*    */ import com.mojang.ld22.gfx.Screen;
/*    */ import com.mojang.ld22.item.Item;
/*    */ import com.mojang.ld22.item.ResourceItem;
/*    */ import com.mojang.ld22.item.resource.Resource;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public abstract class Recipe implements com.mojang.ld22.screen.ListItem
/*    */ {
/* 16 */   public List<Item> costs = new ArrayList();
/* 17 */   public boolean canCraft = false;
/*    */   public Item resultTemplate;
/*    */   
/*    */   public Recipe(Item resultTemplate) {
/* 21 */     this.resultTemplate = resultTemplate;
/*    */   }
/*    */   
/*    */   public Recipe addCost(Resource resource, int count) {
/* 25 */     this.costs.add(new ResourceItem(resource, count));
/* 26 */     return this;
/*    */   }
/*    */   
/*    */   public void checkCanCraft(Player player) {
/* 30 */     for (int i = 0; i < this.costs.size(); i++) {
/* 31 */       Item item = (Item)this.costs.get(i);
/* 32 */       if ((item instanceof ResourceItem)) {
/* 33 */         ResourceItem ri = (ResourceItem)item;
/* 34 */         if (!player.inventory.hasResources(ri.resource, ri.count)) {
/* 35 */           this.canCraft = false;
/* 36 */           return;
/*    */         }
/*    */       }
/*    */     }
/* 40 */     this.canCraft = true;
/*    */   }
/*    */   
/*    */   public void renderInventory(Screen screen, int x, int y) {
/* 44 */     screen.render(x, y, this.resultTemplate.getSprite(), this.resultTemplate.getColor(), 0);
/* 45 */     int textColor = this.canCraft ? Color.get(-1, 555, 555, 555) : Color.get(-1, 222, 222, 222);
/* 46 */     Font.draw(this.resultTemplate.getName(), screen, x + 8, y, textColor);
/*    */   }
/*    */   
/*    */   public abstract void craft(Player paramPlayer);
/*    */   
/*    */   public void deductCost(Player player) {
/* 52 */     for (int i = 0; i < this.costs.size(); i++) {
/* 53 */       Item item = (Item)this.costs.get(i);
/* 54 */       if ((item instanceof ResourceItem)) {
/* 55 */         ResourceItem ri = (ResourceItem)item;
/* 56 */         player.inventory.removeResource(ri.resource, ri.count);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\crafting\Recipe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */