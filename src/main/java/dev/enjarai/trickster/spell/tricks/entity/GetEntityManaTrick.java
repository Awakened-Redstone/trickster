package dev.enjarai.trickster.spell.tricks.entity;

import dev.enjarai.trickster.cca.ModEntityCumponents;
import dev.enjarai.trickster.spell.Fragment;
import dev.enjarai.trickster.spell.Pattern;
import dev.enjarai.trickster.spell.SpellContext;
import dev.enjarai.trickster.spell.fragment.FragmentType;
import dev.enjarai.trickster.spell.fragment.NumberFragment;
import dev.enjarai.trickster.spell.tricks.Trick;
import dev.enjarai.trickster.spell.tricks.blunder.BlunderException;
import dev.enjarai.trickster.spell.tricks.blunder.EntityInvalidBlunder;
import dev.enjarai.trickster.spell.tricks.blunder.UnknownEntityBlunder;
import net.minecraft.entity.LivingEntity;

import java.util.List;

public class GetEntityManaTrick extends Trick {
    public GetEntityManaTrick() {
        super(Pattern.of(6, 7, 3, 8, 4, 0, 5, 1, 2, 4, 6));
    }

    @Override
    public Fragment activate(SpellContext ctx, List<Fragment> fragments) throws BlunderException {
        var entity = expectInput(fragments, FragmentType.ENTITY, 0).getEntity(ctx);

        if (entity.isPresent()) {
            var entity2 = entity.get();

            if (entity2 instanceof LivingEntity living) {
                return new NumberFragment(ModEntityCumponents.MANA.get(living).get());
            }

            throw new EntityInvalidBlunder(this);
        }

        throw new UnknownEntityBlunder(this);
    }
}
