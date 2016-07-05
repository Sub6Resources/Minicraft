/*    */ package com.mojang.ld22.crafting;
/*    */ 
/*    */ import com.mojang.ld22.entity.Anvil;
/*    */ import com.mojang.ld22.entity.Chest;
/*    */ import com.mojang.ld22.entity.Furnace;
/*    */ import com.mojang.ld22.entity.Lantern;
		 import com.mojang.ld22.entity.Torch;
/*    */ import com.mojang.ld22.entity.Oven;
/*    */ import com.mojang.ld22.entity.Workbench;
/*    */ import com.mojang.ld22.item.ToolType;
/*    */ import com.mojang.ld22.item.resource.Resource;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class Crafting
/*    */ {
/* 16 */   public static final List<Recipe> anvilRecipes = new ArrayList();
/* 17 */   public static final List<Recipe> ovenRecipes = new ArrayList();
/* 18 */   public static final List<Recipe> furnaceRecipes = new ArrayList();
/* 19 */   public static final List<Recipe> workbenchRecipes = new ArrayList();
/*    */   
/*    */   static {
/*    */     try {
/* 23 */       workbenchRecipes.add(new FurnitureRecipe(Lantern.class).addCost(Resource.wood, 5).addCost(Resource.slime, 10).addCost(Resource.glass, 4));
/* 23.5 */     workbenchRecipes.add(new FurnitureRecipe(Torch.class).addCost(Resource.wood, 1));
/*    */       
/* 25 */       workbenchRecipes.add(new FurnitureRecipe(Oven.class).addCost(Resource.stone, 15));
/* 26 */       workbenchRecipes.add(new FurnitureRecipe(Furnace.class).addCost(Resource.stone, 20));
/* 27 */       workbenchRecipes.add(new FurnitureRecipe(Workbench.class).addCost(Resource.wood, 20));
/* 28 */       workbenchRecipes.add(new FurnitureRecipe(Chest.class).addCost(Resource.wood, 20));
/* 29 */       workbenchRecipes.add(new FurnitureRecipe(Anvil.class).addCost(Resource.ironIngot, 5));
/*    */       
/* 31 */       workbenchRecipes.add(new ToolRecipe(ToolType.sword, 0).addCost(Resource.wood, 5));
/* 32 */       workbenchRecipes.add(new ToolRecipe(ToolType.axe, 0).addCost(Resource.wood, 5));
/* 33 */       workbenchRecipes.add(new ToolRecipe(ToolType.hoe, 0).addCost(Resource.wood, 5));
/* 34 */       workbenchRecipes.add(new ToolRecipe(ToolType.pickaxe, 0).addCost(Resource.wood, 5));
/* 35 */       workbenchRecipes.add(new ToolRecipe(ToolType.shovel, 0).addCost(Resource.wood, 5));
/* 36 */       workbenchRecipes.add(new ToolRecipe(ToolType.sword, 1).addCost(Resource.wood, 5).addCost(Resource.stone, 5));
/* 37 */       workbenchRecipes.add(new ToolRecipe(ToolType.axe, 1).addCost(Resource.wood, 5).addCost(Resource.stone, 5));
/* 38 */       workbenchRecipes.add(new ToolRecipe(ToolType.hoe, 1).addCost(Resource.wood, 5).addCost(Resource.stone, 5));
/* 39 */       workbenchRecipes.add(new ToolRecipe(ToolType.pickaxe, 1).addCost(Resource.wood, 5).addCost(Resource.stone, 5));
/* 40 */       workbenchRecipes.add(new ToolRecipe(ToolType.shovel, 1).addCost(Resource.wood, 5).addCost(Resource.stone, 5));
/*    */       
/* 42 */       anvilRecipes.add(new ToolRecipe(ToolType.sword, 2).addCost(Resource.wood, 5).addCost(Resource.ironIngot, 5));
/* 43 */       anvilRecipes.add(new ToolRecipe(ToolType.axe, 2).addCost(Resource.wood, 5).addCost(Resource.ironIngot, 5));
/* 44 */       anvilRecipes.add(new ToolRecipe(ToolType.hoe, 2).addCost(Resource.wood, 5).addCost(Resource.ironIngot, 5));
/* 45 */       anvilRecipes.add(new ToolRecipe(ToolType.pickaxe, 2).addCost(Resource.wood, 5).addCost(Resource.ironIngot, 5));
/* 46 */       anvilRecipes.add(new ToolRecipe(ToolType.shovel, 2).addCost(Resource.wood, 5).addCost(Resource.ironIngot, 5));
/*    */       
/* 48 */       anvilRecipes.add(new ToolRecipe(ToolType.sword, 3).addCost(Resource.wood, 5).addCost(Resource.goldIngot, 5));
/* 49 */       anvilRecipes.add(new ToolRecipe(ToolType.axe, 3).addCost(Resource.wood, 5).addCost(Resource.goldIngot, 5));
/* 50 */       anvilRecipes.add(new ToolRecipe(ToolType.hoe, 3).addCost(Resource.wood, 5).addCost(Resource.goldIngot, 5));
/* 51 */       anvilRecipes.add(new ToolRecipe(ToolType.pickaxe, 3).addCost(Resource.wood, 5).addCost(Resource.goldIngot, 5));
/* 52 */       anvilRecipes.add(new ToolRecipe(ToolType.shovel, 3).addCost(Resource.wood, 5).addCost(Resource.goldIngot, 5));
/*    */       
/* 54 */       anvilRecipes.add(new ToolRecipe(ToolType.sword, 4).addCost(Resource.wood, 5).addCost(Resource.gem, 50));
/* 55 */       anvilRecipes.add(new ToolRecipe(ToolType.axe, 4).addCost(Resource.wood, 5).addCost(Resource.gem, 50));
/* 56 */       anvilRecipes.add(new ToolRecipe(ToolType.hoe, 4).addCost(Resource.wood, 5).addCost(Resource.gem, 50));
/* 57 */       anvilRecipes.add(new ToolRecipe(ToolType.pickaxe, 4).addCost(Resource.wood, 5).addCost(Resource.gem, 50));
/* 58 */       anvilRecipes.add(new ToolRecipe(ToolType.shovel, 4).addCost(Resource.wood, 5).addCost(Resource.gem, 50));
/*    */       
/* 60 */       furnaceRecipes.add(new ResourceRecipe(Resource.ironIngot).addCost(Resource.ironOre, 4).addCost(Resource.coal, 1));
/* 61 */       furnaceRecipes.add(new ResourceRecipe(Resource.goldIngot).addCost(Resource.goldOre, 4).addCost(Resource.coal, 1));
/* 62 */       furnaceRecipes.add(new ResourceRecipe(Resource.glass).addCost(Resource.sand, 4).addCost(Resource.coal, 1));
/*    */       
/* 64 */       ovenRecipes.add(new ResourceRecipe(Resource.bread).addCost(Resource.wheat, 4));
			   ovenRecipes.add(new ResourceRecipe(Resource.taco).addCost(Resource.beef, 2));
/*    */     } catch (Exception e) {
/* 66 */       throw new RuntimeException(e);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\crafting\Crafting.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */