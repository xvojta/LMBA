package LMBA;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import com.flowpowered.nbt.CompoundTag;
import com.flowpowered.nbt.ListTag;
import com.flowpowered.nbt.StringTag;
import com.flowpowered.nbt.Tag;
import com.flowpowered.nbt.stream.NBTInputStream;
import com.flowpowered.nbt.stream.NBTOutputStream;

public class LMBAMain {
	
	private HashMap fellowshipMembers;
	
	public LMBAMain() {
		fellowshipMembers = new HashMap();
		
	}
	
	public void loadNBTFile() throws IOException{
		  File dir = new File("C:\\Users\\artin\\Desktop\\Lotr novy test\\Lotr novy test\\world\\LOTR\\fellowships");
		  File[] directoryListing = dir.listFiles();
		  if (directoryListing != null) {
		    for (File child : directoryListing) {
				NBTInputStream ns = new 	NBTInputStream(new FileInputStream(child));
				CompoundTag master = (CompoundTag) ns.readTag();
				ListTag members = (ListTag) master.getValue().get("Members");
				StringTag name = (StringTag) master.getValue().get("Name");
				for (Object member : members.getValue()){
					String key = ((CompoundTag)member).getValue().get("Member").getValue() + " " + name.getValue();
					fellowshipMembers.put(key, null);
				}
				ns.close();
		    }
		  } else {
		    // Handle the case where dir is not really a directory.
		    // Checking dir.isDirectory() above would not be sufficient
		    // to avoid race conditions with another process that deletes
		    // directories.
		  }
		
	/*	NBTOutputStream ns2 = new NBTOutputStream(new FileOutputStream(new File("testing.mclevel")));
		ns2.writeTag(master);
		ns2.close(); */
												  
	//	ns = new NBTInputStream(new FileInputStream(new File("testing.mclevel")));
	//	master = (CompoundTag) ns.readTag();
	//	ns.close();
		
	}
	
	public static void main(String[] args) {
		LMBAMain lmb = new LMBAMain();
		try {
			lmb.loadNBTFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
