/*    */ package com.mojang.ld22.screen;
/*    */ 
/*    */ import com.mojang.ld22.InputHandler;
/*    */ import com.mojang.ld22.InputHandler.Key;
/*    */ import com.mojang.ld22.entity.Inventory;
/*    */ 
/*    */ public class ContainerMenu extends Menu
/*    */ {
/*    */   private com.mojang.ld22.entity.Player player;
/*    */   private Inventory container;
/* 11 */   private int selected = 0;
/*    */   private String title;
/*    */   private int oSelected;
/* 14 */   private int window = 0;
/*    */   
/*    */   public ContainerMenu(com.mojang.ld22.entity.Player player, String title, Inventory container) {
/* 17 */     this.player = player;
/* 18 */     this.title = title;
/* 19 */     this.container = container;
/*    */   }
/*    */   
/*    */   public void tick() {
/* 23 */     if (this.input.menu.clicked) { this.game.setMenu(null);
/*    */     }
/* 25 */     if (this.input.left.clicked) {
/* 26 */       this.window = 0;
/* 27 */       int tmp = this.selected;
/* 28 */       this.selected = this.oSelected;
/* 29 */       this.oSelected = tmp;
/*    */     }
/* 31 */     if (this.input.right.clicked) {
/* 32 */       this.window = 1;
/* 33 */       int tmp = this.selected;
/* 34 */       this.selected = this.oSelected;
/* 35 */       this.oSelected = tmp;
/*    */     }
/*    */     
/* 38 */     Inventory i = this.window == 1 ? this.player.inventory : this.container;
/* 39 */     Inventory i2 = this.window == 0 ? this.player.inventory : this.container;
/*    */     
/* 41 */     int len = i.items.size();
/* 42 */     if (this.selected < 0) this.selected = 0;
/* 43 */     if (this.selected >= len) { this.selected = (len - 1);
/*    */     }
/* 45 */     if (this.input.up.clicked) this.selected -= 1;
/* 46 */     if (this.input.down.clicked) { this.selected += 1;
/*    */     }
/* 48 */     if (len == 0) this.selected = 0;
/* 49 */     if (this.selected < 0) this.selected += len;
/* 50 */     if (this.selected >= len) { this.selected -= len;
/*    */     }
/* 52 */     if ((this.input.attack.clicked) && (len > 0)) {
/* 53 */       i2.add(this.oSelected, (com.mojang.ld22.item.Item)i.items.remove(this.selected));
/* 54 */       if (this.selected >= i.items.size()) this.selected = (i.items.size() - 1);
/*    */     }
/*    */   }
/*    */   
/*    */   public void render(com.mojang.ld22.gfx.Screen screen) {
/* 59 */     if (this.window == 1) screen.setOffset(48, 0);
/* 60 */     com.mojang.ld22.gfx.Font.renderFrame(screen, this.title, 1, 1, 12, 11);
/* 61 */     renderItemList(screen, 1, 1, 12, 11, this.container.items, this.window == 0 ? this.selected : -this.oSelected - 1);
/*    */     
/* 63 */     com.mojang.ld22.gfx.Font.renderFrame(screen, "inventory", 13, 1, 24, 11);
/* 64 */     renderItemList(screen, 13, 1, 24, 11, this.player.inventory.items, this.window == 1 ? this.selected : -this.oSelected - 1);
/* 65 */     screen.setOffset(0, 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\screen\ContainerMenu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */