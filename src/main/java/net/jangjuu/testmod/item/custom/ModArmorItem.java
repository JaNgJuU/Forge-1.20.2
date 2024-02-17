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
                    .put(ModArmorMaterials.FROZONE, new MobEffectInstance(MobEffects.NIGHT_VISION, 200, 1,
                            false,false, true)).build();

    public ModArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }
    
    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if(!world.isClientSide()) {
            if(hasBootsOn(player)) {
                evaluateArmorEffects(player);
            }
        }
    }

    private void evaluateArmorEffects(Player player) {
        for (Map.Entry<ArmorMaterial, MobEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            MobEffectInstance mapStatusEffect = entry.getValue();

            if(hasCorrectBootsOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);
            }
        }
    }

    private void addStatusEffectForMaterial(Player player, ArmorMaterial mapArmorMaterial,
                                            MobEffectInstance mapStatusEffect) {
        boolean hasPlayerEffect = player.hasEffect(mapStatusEffect.getEffect());

        if(hasCorrectBootsOn(mapArmorMaterial, player) && !hasPlayerEffect) {
            player.addEffect(new MobEffectInstance(mapStatusEffect));
        }
    }

    private boolean hasBootsOn(Player player) {
        ItemStack boots = player.getInventory().getArmor(0);

        return !boots.isEmpty();
    }

    private boolean hasCorrectBootsOn(ArmorMaterial material, Player player) {
        for (ItemStack armorStack : player.getInventory().armor) {
            if(!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }

        ArmorItem boots = ((ArmorItem)player.getInventory().getArmor(0).getItem());

        return boots.getMaterial() == material;
    }
}
