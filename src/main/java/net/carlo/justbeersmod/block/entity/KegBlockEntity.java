package net.carlo.justbeersmod.block.entity;

import net.carlo.justbeersmod.recipe.KegRecipe;
import net.carlo.justbeersmod.screen.KegScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.DeadBushBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class KegBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {


    public static final int INVENTORY_SIZE = 6;
    public static final int WHEAT_SLOT= 1;
    public static final int SUGAR_SLOT = 2;
    public static final int WATER_SLOT = 3;
    public static final int INGREDIENT_SLOT = 4;
    public static final int MUG_SLOT = 0;
    public static final int OUTPUT_SLOT = 5;


    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(INVENTORY_SIZE, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 900;



    public KegBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.KEG, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                switch (index) {
                    case 0: return KegBlockEntity.this.progress;
                    case 1: return KegBlockEntity.this.maxProgress;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: KegBlockEntity.this.progress = value; break;
                    case 1: KegBlockEntity.this.maxProgress = value; break;
                }
            }

            public int size() {
                return 2;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Keg");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new KegScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("keg.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        progress = nbt.getInt("keg.progress");
    }

    private void resetProgress() {
        this.progress = 0;
    }

    public static void tick(World world, BlockPos blockPos, BlockState state, KegBlockEntity entity) {
        if(world.isClient()) {
            return;
        }

        if(hasRecipe(entity)) {
            entity.progress++;
            markDirty(world, blockPos, state);
            if(entity.progress >= entity.maxProgress) {
                craftItem(entity);
            }
        } else {
            entity.resetProgress();
            markDirty(world, blockPos, state);
        }
    }

    private static void craftItem(KegBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }
        Optional<KegRecipe> recipe = entity.getWorld().getRecipeManager()
                .getFirstMatch(KegRecipe.Type.INSTANCE, inventory, entity.getWorld());
        entity.removeStack(WHEAT_SLOT, 1);
        entity.removeStack(SUGAR_SLOT, 1);
        entity.removeStack(WATER_SLOT, 1);
        entity.removeStack(INGREDIENT_SLOT, 1);
        entity.removeStack(MUG_SLOT, 1);
        entity.setStack(OUTPUT_SLOT, new ItemStack(recipe.get().getOutput().getItem(),
                entity.getStack(5).getCount() + 1));
        entity.resetProgress();
    }

    private static boolean hasRecipe(KegBlockEntity entity) {


        if (!entity.getWorld().isClient()) {
            // For the sake of simplicity we draw the items off of the player's hands and create an inventory from that.
            // Usually you use an inventory of yours instead.
            SimpleInventory inventory = new SimpleInventory(entity.size());
            for (int i = 0; i < entity.size(); i++) {
                inventory.setStack(i, entity.getStack(i));
            }
            // Or use .getAllMatches if you want all of the matches
            Optional<KegRecipe> match = entity.getWorld().getRecipeManager()
                    .getFirstMatch(KegRecipe.Type.INSTANCE, inventory, entity.getWorld());

            if (match.isPresent() && canInsertAmountIntoOutputSlot(inventory) &&
                    canInsertItemIntoOutputSlot(inventory, match.get().getOutput().getItem())) {
                // Give the player the item and remove from what he has. Make sure to copy the ItemStack to not ruin it!
                return true;
            }
        }

        return true;

    }

    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, Item output) {
        return inventory.getStack(2).getItem() == output || inventory.getStack(2).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(2).getMaxCount() > inventory.getStack(2).getCount();
    }
}
