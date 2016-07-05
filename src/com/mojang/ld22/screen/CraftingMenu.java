/*    */ package com.mojang.ld22.screen;
/*    */ 
/*    */ import com.mojang.ld22.InputHandler;
/*    */ import com.mojang.ld22.InputHandler.Key;
/*    */ import com.mojang.ld22.crafting.Recipe;
/*    */ import com.mojang.ld22.entity.Inventory;
/*    */ import com.mojang.ld22.entity.Player;
/*    */ import com.mojang.ld22.gfx.Color;
/*    */ import com.mojang.ld22.gfx.Font;
/*    */ import com.mojang.ld22.gfx.Screen;
/*    */ import com.mojang.ld22.item.Item;
/*    */ import com.mojang.ld22.item.ResourceItem;
/*    */ import com.mojang.ld22.sound.Sound;
/*    */ import java.util.List;
/*    */ 
/*    */ public class CraftingMenu extends Menu
/*    */ {
/*    */   private Player player;
/* 19 */   private int selected = 0;
/*    */   private List<Recipe> recipes;
/*    */   
/*    */   public CraftingMenu(List<Recipe> recipes, Player player)
/*    */   {
/* 24 */     this.recipes = new java.util.ArrayList(recipes);
/* 25 */     this.player = player;
/*    */     
/* 27 */     for (int i = 0; i < recipes.size(); i++) {
/* 28 */       ((Recipe)this.recipes.get(i)).checkCanCraft(player);
/*    */     }
/*    */     
/* 31 */     java.util.Collections.sort(this.recipes, new java.util.Comparator<Recipe>() {
/*    */       public int compare(Recipe r1, Recipe r2) {
/* 33 */         if ((r1.canCraft) && (!r2.canCraft)) return -1;
/* 34 */         if ((!r1.canCraft) && (r2.canCraft)) return 1;
/* 35 */         return 0;
/*    */       }
/*    */     });
/*    */   }
/*    */   
/*    */   public void tick() {
/* 41 */     if (this.input.menu.clicked) { this.game.setMenu(null);
/*    */     }
/* 43 */     if (this.input.up.clicked) this.selected -= 1;
/* 44 */     if (this.input.down.clicked) { this.selected += 1;
/*    */     }
/* 46 */     int len = this.recipes.size();
/* 47 */     if (len == 0) this.selected = 0;
/* 48 */     if (this.selected < 0) this.selected += len;
/* 49 */     if (this.selected >= len) { this.selected -= len;
/*    */     }
/* 51 */     if ((this.input.attack.clicked) && (len > 0)) {
/* 52 */       Recipe r = (Recipe)this.recipes.get(this.selected);
/* 53 */       r.checkCanCraft(this.player);
/* 54 */       if (r.canCraft) {
/* 55 */         r.deductCost(this.player);
/* 56 */         r.craft(this.player);
/* 57 */         Sound.craft.play();
/*    */       }
/* 59 */       for (int i = 0; i < this.recipes.size(); i++) {
/* 60 */         ((Recipe)this.recipes.get(i)).checkCanCraft(this.player);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public void render(Screen screen) {
/* 66 */     Font.renderFrame(screen, "Have", 12, 1, 19, 3);
/* 67 */     Font.renderFrame(screen, "Cost", 12, 4, 19, 11);
/* 68 */     Font.renderFrame(screen, "Crafting", 0, 1, 11, 11);
/* 69 */     renderItemList(screen, 0, 1, 11, 11, this.recipes, this.selected);
/*    */     
/* 71 */     if (this.recipes.size() > 0) {
/* 72 */       Recipe recipe = (Recipe)this.recipes.get(this.selected);
/* 73 */       int hasResultItems = this.player.inventory.count(recipe.resultTemplate);
/* 74 */       int xo = 104;
/* 75 */       screen.render(xo, 16, recipe.resultTemplate.getSprite(), recipe.resultTemplate.getColor(), 0);
/* 76 */       Font.draw(""+hasResultItems, screen, xo + 8, 16, Color.get(-1, 555, 555, 555));
/*    */       
/* 78 */       List<Item> costs = recipe.costs;
/* 79 */       for (int i = 0; i < costs.size(); i++) {
/* 80 */         Item item = (Item)costs.get(i);
/* 81 */         int yo = (5 + i) * 8;
/* 82 */         screen.render(xo, yo, item.getSprite(), item.getColor(), 0);
/* 83 */         int requiredAmt = 1;
/* 84 */         if ((item instanceof ResourceItem)) {
/* 85 */           requiredAmt = ((ResourceItem)item).count;
/*    */         }
/* 87 */         int has = this.player.inventory.count(item);
/* 88 */         int color = Color.get(-1, 555, 555, 555);
/* 89 */         if (has < requiredAmt) {
/* 90 */           color = Color.get(-1, 222, 222, 222);
/*    */         }
/* 92 */         if (has > 99) has = 99;
/* 93 */         Font.draw(requiredAmt + "/" + has, screen, xo + 8, yo, color);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\screen\CraftingMenu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */