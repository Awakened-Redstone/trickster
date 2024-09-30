package dev.enjarai.trickster.spell.trick.map;

import dev.enjarai.trickster.spell.Fragment;
import dev.enjarai.trickster.spell.Pattern;
import dev.enjarai.trickster.spell.SpellContext;
import dev.enjarai.trickster.spell.fragment.Map.MapFragment;
import dev.enjarai.trickster.spell.fragment.VoidFragment;
import dev.enjarai.trickster.spell.trick.Trick;
import dev.enjarai.trickster.spell.blunder.BlunderException;

import java.util.List;

public class MapRemoveTrick extends Trick {
    public MapRemoveTrick() {
        super(Pattern.of(0, 3, 6, 4, 2, 5, 8));
    }

    @Override
    public Fragment activate(SpellContext ctx, List<Fragment> fragments) throws BlunderException {
        var map = expectInput(fragments, MapFragment.class, 0).map();
        Fragment key = expectInput(fragments, 1);

        var newMap = map.dissoc(key);

        return new MapFragment(newMap);
    }
}
