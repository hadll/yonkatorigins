package haddle.yonkatorigins.item.papercraft;


import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Bucketable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtDouble;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class FishyPaperItem extends Item {

    public FishyPaperItem(Settings settings) {
        super(settings);
    }

    private void spawnEntity(ServerWorld world, ItemStack stack, BlockPos pos, EntityType<?> type, NbtCompound nbt) {

        Entity entity = type.spawnFromItemStack(world, stack, (PlayerEntity)null, pos, SpawnReason.BUCKET, true, false);
        nbt.put("Pos", new NbtList() {{
            add(NbtDouble.of(pos.getX()));
            add(NbtDouble.of(pos.getY()));
            add(NbtDouble.of(pos.getZ()));
        }});
        entity.readNbt(nbt);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        NbtCompound itemNbt = itemStack.getOrCreateNbt();
        String entityTypeName = itemNbt.getString("EntityType");
        if (entityTypeName.isEmpty()) {
            EntityHitResult hit = ProjectileUtil.raycast(
                    user,
                    user.getCameraPosVec(1.0F),
                    user.getCameraPosVec(1.0F).add(user.getRotationVec(1.0F).multiply(5.0D)),
                    user.getBoundingBox().stretch(user.getRotationVec(1.0F).multiply(5.0D)).expand(1.0D),
                    entity -> !entity.isSpectator() && entity.isAlive(),
                    5.0D
            );
            if (hit != null && hit.getType() == HitResult.Type.ENTITY) {
                Entity hitEntity = ((EntityHitResult) hit).getEntity();
                NbtCompound entityNbt = new NbtCompound();
                hitEntity.writeNbt(entityNbt);
                Identifier type = EntityType.getId(hitEntity.getType());
                NbtCompound storedEntityNbt = itemNbt.getCompound("StoredEntity");
                storedEntityNbt.copyFrom(entityNbt);
                storedEntityNbt.remove("Pos");
                itemNbt.put("StoredEntity", storedEntityNbt);
                itemNbt.putString("EntityType", type.toString());
                hitEntity.remove(Entity.RemovalReason.DISCARDED);
                return TypedActionResult.success(itemStack);
            } else {
                return TypedActionResult.pass(itemStack);
            }
        }else{
            Optional<EntityType<?>> type = EntityType.get(entityTypeName);
            EntityType<?> entityType = type.orElse(null);
            if (entityType == null){
                // this should not happen, if it does just bork the item
                return TypedActionResult.pass(itemStack);
            }
            if (world instanceof ServerWorld) {
                HitResult hit = user.raycast(5, 1f, false);
                if (hit.getType() == HitResult.Type.BLOCK) {
                    BlockPos pos = BlockPos.ofFloored(hit.getPos());
                    this.spawnEntity((ServerWorld) world, itemStack, pos, entityType, itemNbt.getCompound("StoredEntity"));
                    world.emitGameEvent(user, GameEvent.ENTITY_PLACE, pos);
                    itemNbt.remove("EntityType");
                    itemNbt.remove("StoredEntity");
                    return TypedActionResult.success(itemStack);
                }
            }
            return TypedActionResult.pass(itemStack);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        NbtCompound itemNbt = stack.getOrCreateNbt();
        String entityTypeName = itemNbt.getString("EntityType");
        if (!entityTypeName.isEmpty()) {
            tooltip.add(Text.of("Stored: [ " +entityTypeName+ " ]"));
        }

    }
}
