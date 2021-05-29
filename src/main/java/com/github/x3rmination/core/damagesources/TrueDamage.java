package com.github.x3rmination.core.damagesources;

import net.minecraft.util.DamageSource;

public class TrueDamage extends DamageSource {
    public TrueDamage(String p_i1566_1_) {
        super(p_i1566_1_);
    }
    public static final DamageSource TRUE_DAMAGE = (new DamageSource("trueDamage")).setDamageBypassesArmor();
}
