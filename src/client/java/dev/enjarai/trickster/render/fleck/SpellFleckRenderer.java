package dev.enjarai.trickster.render.fleck;

import dev.enjarai.trickster.render.SpellCircleRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.minecraft.client.render.*;
import dev.enjarai.trickster.fleck.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3f;

public class SpellFleckRenderer implements FleckRenderer<SpellFleck> {
    private final SpellCircleRenderer renderer = new SpellCircleRenderer(false, 1);

    @Override
    public void render(
            SpellFleck fleck, SpellFleck lastFleck, WorldRenderContext context, ClientWorld world, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int color
    ) {

        matrices.push();

        var position = fleck.pos();
        var facing = fleck.facing();
        var spell = fleck.spell();
        var size = fleck.size();

        var oldPosition = fleck.pos();
        var oldFacing = fleck.facing();
        var oldSize = fleck.size();

        if (lastFleck != null) {
            oldPosition = lastFleck.pos();
            oldFacing = lastFleck.facing();
            oldSize = lastFleck.size();
        }

        //only lerp if change is small?
        //float lerpAmount = position.distance(oldPosition) > 1 ? 1.0f : tickDelta

        var targetPosition = oldPosition.lerp(position, tickDelta, new Vector3f()).sub(context.camera().getPos().toVector3f());
        var targetFacing = oldFacing.lerp(facing, tickDelta, new Vector3f()).normalize();
        var targetSize = MathHelper.lerp(tickDelta, oldSize, size);

        var yaw = (float) Math.atan2(targetFacing.x(), targetFacing.z());
        var pitch = (float) (Math.asin(-targetFacing.y()) + Math.PI);

        matrices.translate(targetPosition.x(), targetPosition.y(), targetPosition.z()); //translate over to the position of the spellfleck.
        matrices.multiply(RotationAxis.POSITIVE_Y.rotation(yaw));
        matrices.multiply(RotationAxis.POSITIVE_X.rotation(pitch));

        //TODO WOBBLE

        renderer.renderPart(
                matrices,
                vertexConsumers,
                spell,
                0,
                0,
                targetSize / 2,
                0,
                tickDelta,
                size1 -> 1.0f,
                new Vec3d(facing.x(), facing.y(), facing.z())
        );

        matrices.pop();
    }
}
