package com.sherlockmatt.gascraft.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityGasAmplifier extends TileEntity implements IInventory 
{
	private ItemStack[] items = new ItemStack[1];// Itemstack. Was field_145985_p.
	protected String containerName; //Container name. Was field_145981_s
	
	@Override
	public int getSizeInventory()
	{
		return 1;
		//return (BlockGasAmplifier)this.func_145838_q(). ; //Slots
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return items[slot]; //Stack in slot
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) //Removes items from inventory and returns them in a seperate stack
	{
		if (this.items[slot] != null)
        {
            ItemStack itemstack;

            if (this.items[slot].stackSize <= amount)
            {
                itemstack = this.items[slot];
                this.items[slot] = null;
<<<<<<< HEAD
                this.onInventoryChanged();
=======
                //this.onInventoryChanged(); might be updateEntity()?
>>>>>>> origin/dev2_mappings
                return itemstack;
            }
            else
            {
                itemstack = this.items[slot].splitStack(amount);

                if (this.items[slot].stackSize == 0)
                {
                    this.items[slot] = null;
                }

<<<<<<< HEAD
                this.onInventoryChanged();
=======
                //this.onInventoryChanged(); might be updateEntity()?
>>>>>>> origin/dev2_mappings
                return itemstack;
            }
        }
        else
        {
            return null;
        }
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) //Gets stack on closing, required. Unsure why.
    {
        if (this.items[slot] != null)
        {
            ItemStack itemstack = this.items[slot];
            this.items[slot] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }
	
	/*public int func_146019_a(ItemStack p_146019_1_)
    {
        for (int i = 0; i < this.items.length; ++i)
        {
            if (this.items[i] == null || this.items[i].getItem() == null)
            {
                this.setInventorySlotContents(i, p_146019_1_);
                return i;
            }
        }

        return -1;
    }//*///Probably Unneeded junk 

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack)
	{
		this.items[slot] = itemstack;

        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
        {
        	itemstack.stackSize = this.getInventoryStackLimit();
        }

<<<<<<< HEAD
        this.onInventoryChanged();		
	}

	@Override
	public String func_145825_b() { //Get container name. Was field_145981_s
		return this.func_145818_k_() ? this.containerName : "container.gasamplifier";
	}
	
	public void func_146018_a(String containerName) //Set Container Name
    {
        this.containerName = containerName;
    }

	@Override
	public boolean func_145818_k_() { //Check if container name is assigned. Was field_145981_s
=======
        //this.onInventoryChanged(); might be updateEntity()?
	}

	@Override
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.containerName : "container.gasamplifier"; //Used to be func_145818_k_, now hasCustomInventoryName()
	}
	
	/*public void func_146018_a(String containerName) //Don't think this is necessary
    {
        this.containerName = containerName;
    }*/

	@Override
	public boolean hasCustomInventoryName() {
>>>>>>> origin/dev2_mappings
		return this.containerName != null && this.containerName.length() > 0;
	}
	
	@Override
<<<<<<< HEAD
	public void func_145839_a(NBTTagCompound nbtdata) //Load from NBT. nbtdata was p_145839_1_
=======
	public void readFromNBT(NBTTagCompound nbtdata) //Uh, what? Pls fix.
>>>>>>> origin/dev2_mappings
    {
        super.func_145839_a(nbtdata);
        NBTTagList nbttaglist = nbtdata.func_150295_c("Items", 10);
        this.items = new ItemStack[this.getSizeInventory()];

        if (nbtdata.func_150297_b("CustomName", 8))
        {
            this.containerName = nbtdata.getString("CustomName");
        }

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.items.length)
            {
                this.items[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }
	
	@Override
<<<<<<< HEAD
	public void func_145841_b(NBTTagCompound nbtdata) //Save to NBT. nbtdata was p_145841_1_
=======
	public void writeToNBT(NBTTagCompound nbtdata) //sjdfjsnfjsnjn. I'M PANICKING!
>>>>>>> origin/dev2_mappings
    {
        super.func_145841_b(nbtdata);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.items.length; ++i)
        {
            if (this.items[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.items[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbtdata.setTag("Items", nbttaglist);

        if (this.func_145818_k_())
        {
        	nbtdata.setString("CustomName", this.containerName);
        }
    }
	
	@Override
<<<<<<< HEAD
	public int getInventoryStackLimit() { //Stack size limit
=======
	public int getInventoryStackLimit() {
>>>>>>> origin/dev2_mappings
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer EntityPlayer) {
<<<<<<< HEAD
		return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this ? false : EntityPlayer.getDistanceSq((double)this.field_145851_c + 0.5D, (double)this.field_145848_d + 0.5D, (double)this.field_145849_e + 0.5D) <= 64.0D;
	}

	public void openChest() {}

	public void closeChest() {}
=======
		return this.getWorldObj().getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	public void openInventory() {}

	public void closeInventory() {}
>>>>>>> origin/dev2_mappings

	public boolean isItemValidForSlot(int slot, ItemStack itemstack) { //No automated item swapping.
		return false;
	}	
}