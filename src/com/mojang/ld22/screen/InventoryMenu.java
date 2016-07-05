/*    */ package com.mojang.ld22.screen;
/*    */ 
/*    */ import com.mojang.ld22.InputHandler;
/*    */ import com.mojang.ld22.entity.Inventory;
/*    */ import com.mojang.ld22.entity.Player;
/*    */ 
/*    */ public class InventoryMenu extends Menu
/*    */ {
/*    */   private Player player;
/* 10 */   private int selected = 0;
/*    */   
/*    */   public InventoryMenu(Player player) {
/* 13 */     this.player = player;
/*    */     
/* 15 */     if (player.activeItem != null) {
/* 16 */       player.inventory.items.add(0, player.activeItem);
/* 17 */       player.activeItem = null;
/*    */     }
/*    */   }
/*    */   
/*    */   public void tick() {
/* 22 */     if (this.input.menu.clicked) { this.game.setMenu(null);
/*    */     }
/* 24 */     if (this.input.up.clicked) this.selected -= 1;
/* 25 */     if (this.input.down.clicked) { this.selected += 1;
/*    */     }
/* 27 */     int len = this.player.inventory.items.size();
/* 28 */     if (len == 0) this.selected = 0;
/* 29 */     if (this.selected < 0) this.selected += len;
/* 30 */     if (this.selected >= len) { this.selected -= len;
/*    */     }
/* 32 */     if ((this.input.attack.clicked) && (len > 0)) {
/* 33 */       com.mojang.ld22.item.Item item = (com.mojang.ld22.item.Item)this.player.inventory.items.remove(this.selected);
/* 34 */       this.player.activeItem = item;
/* 35 */       this.game.setMenu(null);
/*    */     }
/*    */   }
/*    */   
/*    */   public void render(com.mojang.ld22.gfx.Screen screen) {
/* 40 */     com.mojang.ld22.gfx.Font.renderFrame(screen, "inventory", 1, 1, 12, 11);
/* 41 */     renderItemList(screen, 1, 1, 12, 11, this.player.inventory.items, this.selected);
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\screen\InventoryMenu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */