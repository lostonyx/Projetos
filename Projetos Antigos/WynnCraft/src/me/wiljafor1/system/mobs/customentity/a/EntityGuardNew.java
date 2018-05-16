package me.wiljafor1.system.mobs.customentity.a;

import net.minecraft.server.v1_8_R3.*;

import org.bukkit.Location;

import com.google.common.base.Predicate;

public class EntityGuardNew extends EntityGolem {

    private int b;
    Village a;
    private int c;
    private int bm;

    public EntityGuardNew(World world) {
        super(world);
        this.setSize(1.4F, 2.9F);
        ((Navigation) this.getNavigation()).a(true);
        this.goalSelector.a(1, new PathfinderGoalMeleeAttack(this, 1.0D, true));
        this.goalSelector.a(2, new PathfinderGoalMoveTowardsTarget(this, 0.9D, 32.0F));
        this.goalSelector.a(3, new PathfinderGoalMoveThroughVillage(this, 0.6D, true));
        this.goalSelector.a(4, new PathfinderGoalMoveTowardsRestriction(this, 1.0D));
        this.goalSelector.a(6, new PathfinderGoalRandomStroll(this, 0.6D));
        this.goalSelector.a(7, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 6.0F));
        this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
        this.targetSelector.a(2, new PathfinderGoalHurtByTarget(this, false, new Class[0]));
    }
    
    public EntityGuardNew(World world, Location lc) {
        super(world);
        this.setSize(1.4F, 2.9F);
        ((Navigation) this.getNavigation()).a(true);
        this.goalSelector.a(1, new PathfinderGoalWalktoTile(this, lc));
        this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, EntityZombie.class, 1.0D, true));
        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityZombie.class, 8.0F));
        this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, false));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityZombie.class, false));
        this.goalSelector.a(2, new PathfinderGoalMoveTowardsTarget(this, 0.9D, 32.0F));
        this.goalSelector.a(4, new PathfinderGoalMoveTowardsRestriction(this, 1.0D));
        this.goalSelector.a(6, new PathfinderGoalRandomStroll(this, 0.6D));
        this.goalSelector.a(7, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 6.0F));
        this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
        this.targetSelector.a(2, new PathfinderGoalHurtByTarget(this, false, new Class[0]));
    }

    protected void h() {
        super.h();
        this.datawatcher.a(16, Byte.valueOf((byte) 0));
    }

    protected void E() {
        if (--this.b <= 0) {
            this.b = 70 + this.random.nextInt(50);
            this.a = this.world.ae().getClosestVillage(new BlockPosition(this), 32);
            if (this.a == null) {
                this.cj();
            } else {
                BlockPosition blockposition = this.a.a();

                this.a(blockposition, (int) ((float) this.a.b() * 0.6F));
            }
        }

        super.E();
    }

    protected void initAttributes() {
        super.initAttributes();
        this.getAttributeInstance(GenericAttributes.maxHealth).setValue(100.0D);
        this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.25D);
    }

    protected int j(int i) {
        return i;
    }

    protected void s(Entity entity) {
        if (entity instanceof IMonster && !(entity instanceof EntityCreeper) && this.bc().nextInt(20) == 0) {
            this.setGoalTarget((EntityLiving) entity, org.bukkit.event.entity.EntityTargetLivingEntityEvent.TargetReason.COLLISION, true); // CraftBukkit - set reason
        }

        super.s(entity);
    }

    public void m() {
        super.m();
        if (this.c > 0) {
            --this.c;
        }

        if (this.bm > 0) {
            --this.bm;
        }

        if (this.motX * this.motX + this.motZ * this.motZ > 2.500000277905201E-7D && this.random.nextInt(5) == 0) {
            int i = MathHelper.floor(this.locX);
            int j = MathHelper.floor(this.locY - 0.20000000298023224D);
            int k = MathHelper.floor(this.locZ);
            IBlockData iblockdata = this.world.getType(new BlockPosition(i, j, k));
            Block block = iblockdata.getBlock();

            if (block.getMaterial() != Material.AIR) {
                this.world.addParticle(EnumParticle.BLOCK_CRACK, this.locX + ((double) this.random.nextFloat() - 0.5D) * (double) this.width, this.getBoundingBox().b + 0.1D, this.locZ + ((double) this.random.nextFloat() - 0.5D) * (double) this.width, 4.0D * ((double) this.random.nextFloat() - 0.5D), 0.5D, ((double) this.random.nextFloat() - 0.5D) * 4.0D, new int[] { Block.getCombinedId(iblockdata)});
            }
        }

    }

    public boolean a(Class<? extends EntityLiving> oclass) {
        return this.isPlayerCreated() && EntityHuman.class.isAssignableFrom(oclass) ? false : (oclass == EntityCreeper.class ? false : super.a(oclass));
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setBoolean("PlayerCreated", this.isPlayerCreated());
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.setPlayerCreated(nbttagcompound.getBoolean("PlayerCreated"));
    }

    public boolean r(Entity entity) {
        this.c = 10;
        this.world.broadcastEntityEffect(this, (byte) 4);
        boolean flag = entity.damageEntity(DamageSource.mobAttack(this), (float) (7 + this.random.nextInt(15)));

        if (flag) {
            entity.motY += 0.4000000059604645D;
            this.a((EntityLiving) this, entity);
        }

        this.makeSound("mob.irongolem.throw", 1.0F, 1.0F);
        return flag;
    }

    public Village n() {
        return this.a;
    }

    public void a(boolean flag) {
        this.bm = flag ? 400 : 0;
        this.world.broadcastEntityEffect(this, (byte) 11);
    }

    protected String bo() {
        return "mob.irongolem.hit";
    }

    protected String bp() {
        return "mob.irongolem.death";
    }

    protected void a(BlockPosition blockposition, Block block) {
        this.makeSound("mob.irongolem.walk", 1.0F, 1.0F);
    }

    protected void dropDeathLoot(boolean flag, int i) {
        int j = this.random.nextInt(3);

        int k;

        for (k = 0; k < j; ++k) {
            this.a(Item.getItemOf(Blocks.RED_FLOWER), 1, (float) BlockFlowers.EnumFlowerVarient.POPPY.b());
        }

        k = 3 + this.random.nextInt(3);

        for (int l = 0; l < k; ++l) {
            this.a(Items.IRON_INGOT, 1);
        }

    }

    public int cm() {
        return this.bm;
    }

    public boolean isPlayerCreated() {
        return (this.datawatcher.getByte(16) & 1) != 0;
    }

    public void setPlayerCreated(boolean flag) {
        byte b0 = this.datawatcher.getByte(16);

        if (flag) {
            this.datawatcher.watch(16, Byte.valueOf((byte) (b0 | 1)));
        } else {
            this.datawatcher.watch(16, Byte.valueOf((byte) (b0 & -2)));
        }

    }

    public void die(DamageSource damagesource) {
        if (!this.isPlayerCreated() && this.killer != null && this.a != null) {
            this.a.a(this.killer.getName(), -5);
        }

        super.die(damagesource);
    }

    static class PathfinderGoalNearestGolemTarget<T extends EntityLiving> extends PathfinderGoalNearestAttackableTarget<T> {

        public PathfinderGoalNearestGolemTarget(final EntityCreature entitycreature, Class<T> oclass, int i, boolean flag, boolean flag1, final Predicate<? super T> predicate) {
            super(entitycreature, oclass, i, flag, flag1, predicate);
            this.c = new Predicate() {
                public boolean a(T t0) {
                    if (predicate != null && !predicate.apply(t0)) {
                        return false;
                    } else if (t0 instanceof EntityCreeper) {
                        return false;
                    } else {
                        if (t0 instanceof EntityHuman) {
                            double d0 = PathfinderGoalNearestGolemTarget.this.f();

                            if (t0.isSneaking()) {
                                d0 *= 0.800000011920929D;
                            }

                            if (t0.isInvisible()) {
                                float f = ((EntityHuman) t0).bY();

                                if (f < 0.1F) {
                                    f = 0.1F;
                                }

                                d0 *= (double) (0.7F * f);
                            }

                            if ((double) t0.g(entitycreature) > d0) {
                                return false;
                            }
                        }

                        return PathfinderGoalNearestGolemTarget.this.a(t0, false);
                    }
                }

                public boolean apply(Object object) {
                    return this.a((T) object); // CraftBukkit - fix decompiler error
                }
            };
        }
    }
    
	public static class PathfinderGoalWalktoTile extends PathfinderGoal {
        private EntityInsentient entity;
        private PathEntity path;
        private Location loc;
        public PathfinderGoalWalktoTile(EntityInsentient entitycreature, Location loc) {
            this.entity = entitycreature;
            this.loc = loc; 
        }
        @Override
        public boolean a() {
        	if(entity.getGoalTarget() == null) {
        		Location targetLocation = loc;
                boolean flag = this.entity.getNavigation().m();
                this.entity.getNavigation();
                this.path = this.entity.getNavigation().a(targetLocation.getX() + 1, targetLocation.getY(), targetLocation.getZ() + 1);
                this.entity.getNavigation();
                if (this.path != null) {
                    this.c();
                }
        	}
        	return this.path != null;
        }
        @Override
        public void c() {
        	if(entity.getGoalTarget() == null) {
        		this.entity.getNavigation().a(this.path, 1.5D);
        	}
        }
    }
}
