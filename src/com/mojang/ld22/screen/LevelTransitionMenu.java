/*    */ package com.mojang.ld22.screen;
/*    */ 
/*    */ import com.mojang.ld22.gfx.Screen;
/*    */ 
/*    */ public class LevelTransitionMenu extends Menu {
/*    */   private int dir;
/*  7 */   private int time = 0;
/*    */   
/*    */   public LevelTransitionMenu(int dir) {
/* 10 */     this.dir = dir;
/*    */   }
/*    */   
/*    */   public void tick() {
/* 14 */     this.time += 2;
/* 15 */     if (this.time == 30) this.game.changeLevel(this.dir);
/* 16 */     if (this.time == 60) this.game.setMenu(null);
/*    */   }
/*    */   
/*    */   public void render(Screen screen) {
/* 20 */     for (int x = 0; x < 20; x++) {
/* 21 */       for (int y = 0; y < 15; y++) {
/* 22 */         int dd = y + x % 2 * 2 + x / 3 - this.time;
/* 23 */         if ((dd < 0) && (dd > -30)) {
/* 24 */           if (this.dir > 0) {
/* 25 */             screen.render(x * 8, y * 8, 0, 0, 0);
/*    */           } else {
/* 27 */             screen.render(x * 8, screen.h - y * 8 - 8, 0, 0, 0);
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\screen\LevelTransitionMenu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */