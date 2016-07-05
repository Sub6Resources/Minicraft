/*    */ package com.mojang.ld22.entity;
/*    */ 
/*    */ import com.mojang.ld22.item.Item;
/*    */ import com.mojang.ld22.item.ResourceItem;
/*    */ import com.mojang.ld22.item.resource.Resource;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class Inventory
/*    */ {
/* 11 */   public List<Item> items = new ArrayList();
/*    */   
/*    */   public void add(Item item) {
/* 14 */     add(this.items.size(), item);
/*    */   }
/*    */   
/*    */   public void add(int slot, Item item) {
/* 18 */     if ((item instanceof ResourceItem)) {
/* 19 */       ResourceItem toTake = (ResourceItem)item;
/* 20 */       ResourceItem has = findResource(toTake.resource);
/* 21 */       if (has == null) {
/* 22 */         this.items.add(slot, toTake);
/*    */       } else {
/* 24 */         has.count += toTake.count;
/*    */       }
/*    */     } else {
/* 27 */       this.items.add(slot, item);
/*    */     }
/*    */   }
/*    */   
/*    */   private ResourceItem findResource(Resource resource) {
/* 32 */     for (int i = 0; i < this.items.size(); i++) {
/* 33 */       if ((this.items.get(i) instanceof ResourceItem)) {
/* 34 */         ResourceItem has = (ResourceItem)this.items.get(i);
/* 35 */         if (has.resource == resource) return has;
/*    */       }
/*    */     }
/* 38 */     return null;
/*    */   }
/*    */   
/*    */   public boolean hasResources(Resource r, int count) {
/* 42 */     ResourceItem ri = findResource(r);
/* 43 */     if (ri == null) return false;
/* 44 */     return ri.count >= count;
/*    */   }
/*    */   
/*    */   public boolean removeResource(Resource r, int count) {
/* 48 */     ResourceItem ri = findResource(r);
/* 49 */     if (ri == null) return false;
/* 50 */     if (ri.count < count) return false;
/* 51 */     ri.count -= count;
/* 52 */     if (ri.count <= 0) this.items.remove(ri);
/* 53 */     return true;
/*    */   }
/*    */   
/*    */   public int count(Item item) {
/* 57 */     if ((item instanceof ResourceItem)) {
/* 58 */       ResourceItem ri = findResource(((ResourceItem)item).resource);
/* 59 */       if (ri != null) return ri.count;
/*    */     } else {
/* 61 */       int count = 0;
/* 62 */       for (int i = 0; i < this.items.size(); i++) {
/* 63 */         if (((Item)this.items.get(i)).matches(item)) count++;
/*    */       }
/* 65 */       return count;
/*    */     }
/* 67 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\entity\Inventory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */