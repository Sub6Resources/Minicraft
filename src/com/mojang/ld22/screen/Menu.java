/*    */ package com.mojang.ld22.screen;
/*    */ 
/*    */ import com.mojang.ld22.Game;
/*    */ import com.mojang.ld22.InputHandler;
/*    */ import com.mojang.ld22.gfx.Font;
/*    */ import com.mojang.ld22.gfx.Screen;
/*    */ import java.util.List;
/*    */ 
/*    */ public class Menu
/*    */ {
/*    */   protected Game game;
/*    */   protected InputHandler input;
/*    */   
/*    */   public void init(Game game, InputHandler input)
/*    */   {
/* 16 */     this.input = input;
/* 17 */     this.game = game;
/*    */   }
/*    */   
/*    */ 
/*    */   public void tick() {}
/*    */   
/*    */   public void render(Screen screen) {}
/*    */   
/*    */   public void renderItemList(Screen screen, int xo, int yo, int x1, int y1, List<? extends ListItem> listItems, int selected)
/*    */   {
/* 27 */     boolean renderCursor = true;
/* 28 */     if (selected < 0) {
/* 29 */       selected = -selected - 1;
/* 30 */       renderCursor = false;
/*    */     }
/* 32 */     int w = x1 - xo;
/* 33 */     int h = y1 - yo - 1;
/* 34 */     int i0 = 0;
/* 35 */     int i1 = listItems.size();
/* 36 */     if (i1 > h) i1 = h;
/* 37 */     int io = selected - h / 2;
/* 38 */     if (io > listItems.size() - h) io = listItems.size() - h;
/* 39 */     if (io < 0) { io = 0;
/*    */     }
/* 41 */     for (int i = i0; i < i1; i++) {
/* 42 */       ((ListItem)listItems.get(i + io)).renderInventory(screen, (1 + xo) * 8, (i + 1 + yo) * 8);
/*    */     }
/*    */     
/* 45 */     if (renderCursor) {
/* 46 */       int yy = selected + 1 - io + yo;
/* 47 */       Font.draw(">", screen, (xo + 0) * 8, yy * 8, com.mojang.ld22.gfx.Color.get(5, 555, 555, 555));
/* 48 */       Font.draw("<", screen, (xo + w) * 8, yy * 8, com.mojang.ld22.gfx.Color.get(5, 555, 555, 555));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\screen\Menu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */