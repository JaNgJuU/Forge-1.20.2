package net.jangjuu.testmod.item.custom;

import com.google.common.collect.ImmutableMap;
import net.jangjuu.testmod.ModArmorMaterials;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Map;

public class ModArmorItem extends ArmorItem {
    private static final Map<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
                    .put(ModArmorMaterials.FROZONE, new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 1,
                            false,false, true)).build();

    public ModArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }
    
    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if(!world.isClientSide()) {
            evaluateArmorEffects(player);
        }
    }

    private void evaluateArmorEffects(Player player) {
        ItemStack boots = player.getInventory().getArmor(0);
        if (!boots.isEmpty() && boots.getItem() instanceof ModArmorItem && ((ArmorItem) boots.getItem()).getMaterial() == ModArmorMaterials.FROZONE) {
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 500, 1, false, false, true));
        }
    }

}
