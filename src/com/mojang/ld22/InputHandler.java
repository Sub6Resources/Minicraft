/*    */ package com.mojang.ld22;
/*    */ 
/*    */ import java.awt.event.KeyEvent;
/*    */ 
/*    */ public class InputHandler implements java.awt.event.KeyListener
/*    */ {
/*    */   public class Key {
/*    */     public int presses;
/*    */     public int absorbs;
/*    */     public boolean down;
/*    */     public boolean clicked;
/*    */     
/*    */     public Key() {
/* 14 */       InputHandler.this.keys.add(this);
/*    */     }
/*    */     
/*    */     public void toggle(boolean pressed) {
/* 18 */       if (pressed != this.down) {
/* 19 */         this.down = pressed;
/*    */       }
/* 21 */       if (pressed) {
/* 22 */         this.presses += 1;
/*    */       }
/*    */     }
/*    */     
/*    */     public void tick() {
/* 27 */       if (this.absorbs < this.presses) {
/* 28 */         this.absorbs += 1;
/* 29 */         this.clicked = true;
/*    */       } else {
/* 31 */         this.clicked = false;
/*    */       }
/*    */     }
/*    */   }
/*    */   
/* 36 */   public java.util.List<Key> keys = new java.util.ArrayList();
/*    */   
/* 38 */   public Key up = new Key();
/* 39 */   public Key down = new Key();
/* 40 */   public Key left = new Key();
/* 41 */   public Key right = new Key();
/* 42 */   public Key attack = new Key();
/* 43 */   public Key menu = new Key();
/*    */   
/*    */   public void releaseAll() {
/* 46 */     for (int i = 0; i < this.keys.size(); i++) {
/* 47 */       ((Key)this.keys.get(i)).down = false;
/*    */     }
/*    */   }
/*    */   
/*    */   public void tick() {
/* 52 */     for (int i = 0; i < this.keys.size(); i++) {
/* 53 */       ((Key)this.keys.get(i)).tick();
/*    */     }
/*    */   }
/*    */   
/*    */   public InputHandler(Game game) {
/* 58 */     game.addKeyListener(this);
/*    */   }
/*    */   
/*    */   public void keyPressed(KeyEvent ke) {
/* 62 */     toggle(ke, true);
/*    */   }
/*    */   
/*    */   public void keyReleased(KeyEvent ke) {
/* 66 */     toggle(ke, false);
/*    */   }
/*    */   
/*    */   private void toggle(KeyEvent ke, boolean pressed) {
/* 70 */     if (ke.getKeyCode() == 104) this.up.toggle(pressed);
/* 71 */     if (ke.getKeyCode() == 98) this.down.toggle(pressed);
/* 72 */     if (ke.getKeyCode() == 100) this.left.toggle(pressed);
/* 73 */     if (ke.getKeyCode() == 102) this.right.toggle(pressed);
/* 74 */     if (ke.getKeyCode() == 87) this.up.toggle(pressed);
/* 75 */     if (ke.getKeyCode() == 83) this.down.toggle(pressed);
/* 76 */     if (ke.getKeyCode() == 65) this.left.toggle(pressed);
/* 77 */     if (ke.getKeyCode() == 68) this.right.toggle(pressed);
/* 78 */     if (ke.getKeyCode() == 38) this.up.toggle(pressed);
/* 79 */     if (ke.getKeyCode() == 40) this.down.toggle(pressed);
/* 80 */     if (ke.getKeyCode() == 37) this.left.toggle(pressed);
/* 81 */     if (ke.getKeyCode() == 39) { this.right.toggle(pressed);
/*    */     }
/* 83 */     if (ke.getKeyCode() == 9) this.menu.toggle(pressed);
/* 84 */     if (ke.getKeyCode() == 18) this.menu.toggle(pressed);
/* 85 */     if (ke.getKeyCode() == 65406) this.menu.toggle(pressed);
/* 86 */     if (ke.getKeyCode() == 32) this.attack.toggle(pressed);
/* 87 */     if (ke.getKeyCode() == 17) this.attack.toggle(pressed);
/* 88 */     if (ke.getKeyCode() == 96) this.attack.toggle(pressed);
/* 89 */     if (ke.getKeyCode() == 155) this.attack.toggle(pressed);
/* 90 */     if (ke.getKeyCode() == 10) { this.menu.toggle(pressed);
/*    */     }
/* 92 */     if (ke.getKeyCode() == 88) this.menu.toggle(pressed);
/* 93 */     if (ke.getKeyCode() == 67) this.attack.toggle(pressed);
/*    */   }
/*    */   
/*    */   public void keyTyped(KeyEvent ke) {}
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\InputHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */